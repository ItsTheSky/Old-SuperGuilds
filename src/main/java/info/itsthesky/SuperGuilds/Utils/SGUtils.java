package info.itsthesky.SuperGuilds.Utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class SGUtils {

	public static void sendConsole(String message) {
		Bukkit.getConsoleSender().sendMessage(LangManager.getLangKey("Prefix") + message);
	}

	public static void sendPlayer(Player player, String message) {
		player.sendMessage(LangManager.getLangKey("Prefix") + message);
	}

	public static List<String> genEmblem(String design) {
		design = design.replace(",", ";");
		String[] unicodes = design.split(";");
		int no = 0;
		String line;
		List<String> finaleEmblem = new ArrayList<>();
		String[] dLore = new String[] {"","","","","","","","",""};
		String msg = null;
		for (int i = 0; i < 8; i++){
			line = "";
			for (int i2 = 0; i2 < 8; i2++){
				msg = "§" + unicodes[no] + "█";
				line = line + msg;
				no++;
			}
			finaleEmblem.add(line);
		}
		return finaleEmblem;
	}
}
