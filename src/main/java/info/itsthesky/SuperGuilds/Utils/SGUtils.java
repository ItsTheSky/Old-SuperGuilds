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

	public static List<String> genEmblemBlock(String design) {
		design = design.replace(",", ";");
		String[] unicodes = design.split(";");
		int no = 0;
		String line;
		List<String> finaleEmblem = new ArrayList<>();
		String[] dLore = new String[] {"","","","","","","","",""};
		String msg = null;
		for (int i = 0; i < 64; i++){
			finaleEmblem.add("§" + unicodes[no] + "█");
			no++;
		}
		return finaleEmblem;
	}

	public static String join(List<String> strs, String joiner) {
		StringBuilder f = new StringBuilder();
		for (String s : strs) {
			f.append(s).append(joiner);
		}
		return f.toString();
	}

	public static String addSpace(int i, String str)
	{
		StringBuilder str1 = new StringBuilder();
		for(int j=0;j<i;j++)
		{
			str1.append(" ");
		}
		str1.append(str);
		return str1.toString();

	}
}
