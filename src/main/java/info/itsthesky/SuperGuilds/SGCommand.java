package info.itsthesky.SuperGuilds;

import info.itsthesky.SuperGuilds.Utils.FileManager;
import info.itsthesky.SuperGuilds.Utils.LangManager;
import info.itsthesky.SuperGuilds.Utils.SGUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SGCommand implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			SGUtils.sendConsole(LangManager.getLangKey("NoConsole"));
			return false;
		}
		Player player = (Player) sender;

		if (args.length == 0) {
			int sizeOfArg = 2;
			SGUtils.sendPlayer(player, LangManager.getLangKey("HelpMessage.Header"));
			for (int i = 1; i<sizeOfArg+1; i++) {
				SGUtils.sendPlayer(player, LangManager.getLangKey("HelpMessage.Line" + i));
			}
			SGUtils.sendPlayer(player, LangManager.getLangKey("HelpMessage.Footer"));
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
				SGUtils.sendPlayer(player, LangManager.getLangKey("ReloadMessage.ReloadFinished"));
			}
			return true;
		}

		return false;
	}
}
