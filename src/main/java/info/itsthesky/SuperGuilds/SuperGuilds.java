package info.itsthesky.SuperGuilds;

import info.itsthesky.SuperGuilds.Utils.FileManager;
import info.itsthesky.SuperGuilds.Utils.LangManager;
import info.itsthesky.SuperGuilds.Utils.SGUtils;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SuperGuilds extends JavaPlugin {

	private static String langcode = null;

	public static String getLangCode() {
		return langcode;
	}
	@Override
	public void onEnable() {


		/* Files */
		File configFile = new File("plugins/SuperGuids/config.yml");
		if (!configFile.exists()) {


			SGUtils.sendConsole("§cThe default Configuration file of SuperGuilds isn't found! Create one for you ...");
			List<String> content = null;


			try {
				content = FileManager.contentFromURL("https://raw.githubusercontent.com/SkyCraft78/SuperGuilds-8.0/master/Files/config.yml");
			} catch (IOException e) {
				e.printStackTrace();
			}

			try {
				assert content != null;
				FileManager.setContentOfFile("plugins/SuperGuilds/config.yml", content);
			} catch (IOException e) {
				e.printStackTrace();
			}


			SGUtils.sendConsole("§aThe default configuration file has been created!");
		}
		FileManager.reloadFile("plugins/SuperGuilds/config.yml");
		if (!LangManager.verifLangFile(langcode)) {
			SGUtils.sendConsole("§cCannot found the right locale file with the key code " + langcode + " ! The locale file used is now en_US by default !");
			langcode = "en_US";
			if (!LangManager.verifLangFile("en_US")) {
				List<String> content = null;

				SGUtils.sendConsole("§cCannot found the default (en_US) locale file! Downloading ...");
				try {
					content = FileManager.contentFromURL("https://raw.githubusercontent.com/SkyCraft78/SuperGuilds-8.0/master/Locales/en_US.yml");
				} catch (IOException e) {
					e.printStackTrace();
				}

				try {
					assert content != null;
					FileManager.setContentOfFile("plugins/SuperGuilds/Locales/en_US.yml", content);
					SGUtils.sendConsole("§aThe default locale file (en_US) is now loaded correclty !");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		FileManager.reloadFile("plugins/SuperGuilds/Locales/" + langcode + ".yml");

		SGUtils.sendConsole("§eSuperGuilds §5version §d" + getDescription().getVersion() + " §5made by §d" + getDescription().getAuthors() + " §5is loading ...");
		/* Dependencies verification */
		List<String> depend = new ArrayList<>();
		List<String> errors = new ArrayList<>();
		depend.add("HolographicDisplays");
		depend.add("WorldEdit");
		depend.add("WorldGuard");
		depend.add("Citizens");
		for (String d : depend) {
			if (!Bukkit.getServer().getPluginManager().isPluginEnabled(d)) {
				errors.add("§cCannot found the depend " + d + " §c! Maybe this plugin is not loaded correctly ?");
			}
		}

		/* Envoie des errors */
		if (errors.size() > 0) {
			for (String e : errors) {
				SGUtils.sendConsole("§4§lERROR: " + e);
				SGUtils.sendConsole("§4§lSince one or more hard depend of SuperGuilds isn't installed, SuperGuilds will disabling ...");
				this.setEnabled(false);
			}
		} else {
			SGUtils.sendConsole("§eSuperGuilds §aloaded without internal or revelant errors!");
		}

		/* Commands */
		this.getCommand("superguilds").setExecutor(new SGCommand());
	}
}
