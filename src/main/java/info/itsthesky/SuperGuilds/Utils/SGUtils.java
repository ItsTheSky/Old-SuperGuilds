package info.itsthesky.SuperGuilds.Utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class SGUtils {

	public static void sendConsole(String message) {
		Bukkit.getConsoleSender().sendMessage(LangManager.getLangKey("Prefix") + message);
	}

	public static void sendPlayer(Player player, String message) {
		player.sendMessage(LangManager.getLangKey("Prefix") + message);
	}



}
