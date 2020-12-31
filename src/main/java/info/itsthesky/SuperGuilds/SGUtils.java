package info.itsthesky.SuperGuilds;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class SGUtils {

	private static final String prefix = "§8[§6SuperGuilds§8] ";

	public static void sendConsole(String message) {
		Bukkit.getConsoleSender().sendMessage(prefix + message);
	}

	public static void sendPlayer(Player player, String message) {
		player.sendMessage(prefix + message);
	}



}
