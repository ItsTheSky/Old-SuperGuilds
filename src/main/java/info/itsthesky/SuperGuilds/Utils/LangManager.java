package info.itsthesky.SuperGuilds.Utils;

import info.itsthesky.SuperGuilds.SuperGuilds;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class LangManager {

	public static String getLangKey(String key) {
		String langcode = SuperGuilds.getLangCode();
		File configTwo = new File("plugins/SuperGuilds/Locales/" + langcode + ".yml");
		FileConfiguration configFile = YamlConfiguration.loadConfiguration(configTwo);
		if (configFile.contains("Key." + key)) {
			return (String) configFile.get("Key." + key).toString().replace("&", "§");
		} else {
			if (key.equals("Prefix")) {
				return "§8[§6§lSG§8] ";
			}
			return "null";
		}
	}

	public static Boolean verifLangFile(String langcode) {
		File file = new File("plugins/SuperGuilds/Locales/" + langcode + ".yml");
		return file.exists();
	}

	public static String getLangCode() {
		File config = new File("plugins/SuperGuilds/config.yml");
		if (config.exists()){
			File configTwo = new File("plugins/SuperGuilds/config.yml");
			FileConfiguration configFile = YamlConfiguration.loadConfiguration(configTwo);
			if (configFile.contains("Configuration.LocaleKeyCode")) {
				return (String) configFile.get("Configuration.LocaleKeyCode");
			} else {
				return "null";
			}
		}
		return "null";
	}
}
