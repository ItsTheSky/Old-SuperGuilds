package info.itsthesky.SuperGuilds.features;

import info.itsthesky.SuperGuilds.Utils.FileManager;
import info.itsthesky.SuperGuilds.Utils.LangManager;
import info.itsthesky.SuperGuilds.Utils.SGUtils;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import xyz.upperlevel.spigot.book.BookUtil;

import java.io.IOException;
import java.util.List;

public class EmblemEditor {

	public static void openEmblemEditor(Player player, String choosedColor) throws IOException {
		if (!FileManager.isValueSet("players/" + player.getUniqueId(), "Datas.Temp.EmblemCode")) {
			FileManager.writePrimaryFile("players/" + player.getUniqueId(), "Datas.Temp.EmblemCode", "*");
		}
		if (FileManager.getPlayerValue(player, "Temp.EmblemCode").toString().contains("*")) {
			FileManager.writePrimaryFile("players/" + player.getUniqueId(), "Datas.Temp.EmblemCode", "8;8;7;7;7;7;8;8;8;7;7;7;7;7;7;8;7;7;7;7;7;7;7;7;7;7;7;7;7;7;7;7;7;7;7;7;7;7;7;7;7;7;7;7;7;7;7;7;8;7;7;7;7;7;7;8;8;8;7;7;7;7;8;8;f");
		}
		String design = FileManager.getPlayerValue(player, "Temp.EmblemCode").toString();
		List<String> emblems = SGUtils.genEmblemBlock(design);
		if (choosedColor == null) {
			choosedColor = "7";
		}
		String[] colors = new String[] {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

		BookUtil.PageBuilder page = new BookUtil.PageBuilder()
				.add(new TextComponent(LangManager.getLangKey("GUI.Prefix") + LangManager.getLangKey("Emblem.BookName")))
				.newLine().newLine();
		int no = 0;
		int fullno = 0;
		for (String e : emblems) {
			if (no == 8) {
				page.newLine();
				no = 0;
			}
			if (no == 0) {
				page.add("     ");
			}
			page.add(BookUtil.TextBuilder.of(e)
					.onHover(BookUtil.HoverAction.showText("§" + choosedColor + LangManager.getLangKey("Emblem.ClickToPaint")))
					.onClick(BookUtil.ClickAction.runCommand("/g emblem action:paint:" + fullno + ":" + choosedColor))
					.build()
			);
			no++;
			fullno++;
		}

		page.newLine().newLine();
		no = 0;
		for (String c : colors) {
			if (no == 8) {
				page.newLine();
				no = 0;
			}
			if (no == 0) {
				page.add("     ");
			}
			page.add(BookUtil.TextBuilder.of("§" + c + "█")
					.onHover(BookUtil.HoverAction.showText("§" + c + LangManager.getLangKey("Emblem.ChangeColor")))
					.onClick(BookUtil.ClickAction.runCommand("/g emblem action:changeColor:" + c))
					.build()
			);
			no++;
		}
		page.newLine();
		String[] buttons = new String[] {"Save", "Reset", "Back"};
		String buttonStyle;
		String buttonText;
		for (String b : buttons) {
			buttonStyle = LangManager.getLangKey("Emblem.Button." + b);
			buttonText = LangManager.getLangKey("Emblem.Button." + b + "Text");
			page.add(BookUtil.TextBuilder.of(buttonStyle)
					.onHover(BookUtil.HoverAction.showText(buttonText))
					.onClick(BookUtil.ClickAction.runCommand("/g emblem action:" + b))
					.build()
			);
		}

		page.newLine().add("dsq").newLine().add("dsq").newLine().add("dqs");
		ItemStack book = BookUtil.writtenBook()
				.author("Sky")
				.title("Emblem Editor")
				.pages(page.build())
				.build();
		BookUtil.openPlayer(player, book);
	}

}
