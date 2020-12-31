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

	public static String genEmblem(String design) {
		String[] unicodes = design.split(";");
		int no = 0;
		String lines;
		String[] finale = null;
		String msg = null;
		for (int i1 = 1; i1<9; i1++) {
			lines = "";
			for (int i2 = 1; i2<9; i2++) {
				no++;
				msg = "§" + unicodes[no] + "█";
				lines = lines + msg;
			}
			finale[i1] = finale[i1] + lines;
		}
		return String.join("\n", finale);
	}
}
