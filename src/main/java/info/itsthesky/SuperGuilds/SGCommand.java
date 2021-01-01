package info.itsthesky.SuperGuilds;

import info.itsthesky.SuperGuilds.Utils.FileManager;
import info.itsthesky.SuperGuilds.Utils.LangManager;
import info.itsthesky.SuperGuilds.Utils.SGUtils;
import info.itsthesky.SuperGuilds.features.EmblemEditor;
import info.itsthesky.SuperGuilds.gui.MainGUI;
import info.itsthesky.SuperGuilds.gui.RaceGUI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class SGCommand implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			SGUtils.sendConsole(LangManager.getLangKey("NoConsole"));
			return false;
		}
		Player player = (Player) sender;

		if (args.length == 0) {
			MainGUI.openMainGUI(player);
			return true;
		}

		if (args[0].equalsIgnoreCase("help")) {
			int sizeOfArg = 2;
			SGUtils.sendPlayer(player, LangManager.getLangKey("HelpMessage.Header"));
			for (int i = 1; i<sizeOfArg+1; i++) {
				SGUtils.sendPlayer(player, LangManager.getLangKey("HelpMessage.Line" + i));
			}
			SGUtils.sendPlayer(player, LangManager.getLangKey("HelpMessage.Footer"));
			return true;
		}

		if (args[0].equalsIgnoreCase("race")) {
			RaceGUI.openRaceGUI(player);
			return true;
		}

		if (args[0].equalsIgnoreCase("emblem")) {
			if (args.length == 1) {
				try {
					EmblemEditor.openEmblemEditor(player, "7");
				} catch (IOException e) {
					e.printStackTrace();
				}
				return true;
			}
			if (args[1].contains("action:")) {
				String[] options = args[1].split(":");
				if (options[1].equalsIgnoreCase("paint")) {
					String design = FileManager.getPlayerValue(player, "Temp.EmblemCode").toString();
					List<String> codes = Arrays.asList(design.split(";"));
					codes.set(Integer.parseInt(options[2]), options[3]);
					try {
						FileManager.writePrimaryFile("players/" + player.getUniqueId(), "Datas.Temp.EmblemCode", SGUtils.join(codes, ";"));
						EmblemEditor.openEmblemEditor(player, FileManager.getPlayerValue(player, "Temp.EmblemColor").toString());
					} catch (IOException e) {
						e.printStackTrace();
					}
					return true;
				}

				if (options[1].equalsIgnoreCase("changeColor")) {
					try {
						FileManager.writePrimaryFile("players/" + player.getUniqueId(), "Datas.Temp.EmblemColor", options[2]);
						EmblemEditor.openEmblemEditor(player, options[2]);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

				if (args[1].split(":")[1].equalsIgnoreCase("back")) {
					MainGUI.openMainGUI(player);
				} else if (args[1].split(":")[1].equalsIgnoreCase("save")) {
					SGUtils.sendPlayer(player, "&6Soon !");
				} else if (args[1].split(":")[1].equalsIgnoreCase("Reset")) {
					try {
						FileManager.writePrimaryFile("players/" + player.getUniqueId(), "Datas.Temp.EmblemCode", "8;8;7;7;7;7;8;8;8;7;7;7;7;7;7;8;7;7;7;7;7;7;7;7;7;7;7;7;7;7;7;7;7;7;7;7;7;7;7;7;7;7;7;7;7;7;7;7;8;7;7;7;7;7;7;8;8;8;7;7;7;7;8;8;f");
						FileManager.saveFile("plugins/SuperGuilds/players/" + player.getUniqueId() + "yml");
					} catch (IOException e) {
						e.printStackTrace();
					}
					try {
						EmblemEditor.openEmblemEditor(player, "f");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				return true;
			} else {
				try {
					String cc = "7";
					if (FileManager.isValueSet("plugins/SuperGuilds/players/" + player.getUniqueId() + ".yml", "Datas.Temp.EmblemColor")) {
						cc = (String) FileManager.getPlayerValue(player, "Temp.EmblemColor");
					}
					EmblemEditor.openEmblemEditor(player, cc);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return true;
		}

		if (args[0].equalsIgnoreCase("reload")) {
			if (args.length == 1) {
				int sizeOfRl = 3;
				SGUtils.sendPlayer(player, LangManager.getLangKey("ReloadMessage.Header"));
				for (int i = 1; i<sizeOfRl+1; i++) {
					SGUtils.sendPlayer(player, LangManager.getLangKey("ReloadMessage.Line" + i));
				}
				SGUtils.sendPlayer(player, LangManager.getLangKey("ReloadMessage.Footer"));
				return true;
			}
			if (args[1].equalsIgnoreCase("locale")) {
				SGUtils.sendPlayer(player, LangManager.getLangKey("ReloadMessage.ReloadLocaleWaiting"));
				FileManager.reloadFile("plugins/SuperGuilds/Locales/" + LangManager.getLangCode() + ".yml");
				SGUtils.sendPlayer(player, LangManager.getLangKey("ReloadMessage.ReloadFinished"));
			} else if (args[1].equalsIgnoreCase("config")) {
				SGUtils.sendPlayer(player, LangManager.getLangKey("ReloadMessage.ReloadConfigWaiting"));
				FileManager.reloadFile("plugins/SuperGuilds/config.yml");
				SGUtils.sendPlayer(player, LangManager.getLangKey("ReloadMessage.ReloadFinished"));
			} else if (args[1].equalsIgnoreCase("all")) {
				SGUtils.sendPlayer(player, LangManager.getLangKey("ReloadMessage.ReloadAllWaiting"));
				FileManager.reloadFile("plugins/SuperGuilds/Locales/" + LangManager.getLangCode() + ".yml");
				FileManager.reloadFile("plugins/SuperGuilds/config.yml");
				FileManager.reloadFile("plugins/SuperGuilds/races.yml");
				SGUtils.sendPlayer(player, LangManager.getLangKey("ReloadMessage.ReloadFinished"));
			}
			return true;
		}

		return false;
	}
}
