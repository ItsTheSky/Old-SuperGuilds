package info.itsthesky.SuperGuilds.Utils;

import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.*;
import java.net.URL;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileManager {

	public FileManager() throws IOException {
	}

	public static void newFile(String path) throws IOException {
		File file = new File(path);
		if (!file.exists()) {
			try {
				file.getParentFile().mkdirs();
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void reloadFile(String path) {
		File file = new File(path);
		saveFile(path);
		FileConfiguration fileconfig = (FileConfiguration) YamlConfiguration.loadConfiguration(file);
		fileconfig.options().copyDefaults(true);
	}

	public static void saveFile(String path) {
		File file = new File(path);
		if (!file.exists()) {
			// SGUtils.sendConsole("§cThe file " + path + " §calready exist. Report this error to the Author of SuperGuilds!");
			return;
		}
		FileConfiguration fileconfig = (FileConfiguration) YamlConfiguration.loadConfiguration(file);
		try {
			fileconfig.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static FileConfiguration getConfigFile(String path) {
		File file = new File(path);
		return (FileConfiguration) YamlConfiguration.loadConfiguration(file);
	}

	public static File getFile(String path) {
		File file = new File(path);
		return file;
	}

	public static Object checkFile(String path, String node) {
		File file = new File(path);
		FileConfiguration configFile = getConfigFile(file.getPath());
		assert configFile != null;
		if (configFile.contains(node)) {
			return configFile.get(node);
		} else {
			return null;
		}
	}

	public static Object checkPrimary(String path, String node) {
		File file = new File("plugins/SuperGuilds/" +path+ ".yml");
		FileConfiguration configFile = getConfigFile(file.getPath());
		assert configFile != null;
		if (configFile.contains(node)) {
			return configFile.get(node);
		} else {
			return null;
		}
	}

	public static void writeFile(String path, String node, Object value) throws IOException {
		File file = new File(path);
		FileConfiguration configFile = getConfigFile(file.getPath());
		assert configFile != null;
		configFile.set(node, value);
		configFile.save(file);
		reloadFile(file.getPath());
	}

	public static List<String> contentFromURL(String url) throws IOException {
		BufferedReader stream = new BufferedReader(new InputStreamReader(new URL(url).openStream()));
		Stream<String> lines = stream.lines();
		return lines.collect(Collectors.toList());
	}

	public static void setContentOfFile(String path, List<String> content) throws IOException {
		File file = new File(path);
		if (!file.exists()) {
			newFile(path);
		}
		FileWriter fw = new FileWriter(path);
		for (String line : content) {
			fw.write(line + "\n");
		}
		fw.close();
	}

	public static Object getPlayerValue(Player player, String node) {
		return checkFile("plugins/SuperGuilds/players/" + player.getUniqueId() + ".yml", "Datas." + node);
	}

	public static void verifFile(String file) {
		File configFile = new File("plugins/SuperGuilds/"+file+".yml");
		if (!configFile.exists()) {
			SGUtils.sendConsole("§cThe default " + file + " file is not found. Create one for you ...");
			List<String> content = null;
			try {
				content = FileManager.contentFromURL("https://raw.githubusercontent.com/SkyCraft78/SuperGuilds-8.0/master/Files/"+file+".yml");
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				assert content != null;
				FileManager.setContentOfFile("plugins/SuperGuilds/"+file+".yml", content);
			} catch (IOException e) {
				e.printStackTrace();
			}
			SGUtils.sendConsole("§aThe default " + file + " file is has been created!");
		}
	}

}
