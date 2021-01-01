package info.itsthesky.SuperGuilds.features;

import info.itsthesky.SuperGuilds.Utils.FileManager;
import info.itsthesky.SuperGuilds.Utils.LangManager;
import info.itsthesky.SuperGuilds.Utils.SGUtils;
import net.md_5.bungee.api.chat.*;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import xyz.upperlevel.spigot.book.BookUtil;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class EmblemEditor {

	public static void openEmblemEditor(Player player) throws IOException {
		if (!FileManager.isValueSet("players/" + player.getUniqueId(), "Datas.Temp.EmblemCode")) {
			FileManager.writePrimaryFile("players/" + player.getUniqueId(), "Datas.Temp.EmblemCode", "*");
		}
		if (FileManager.getPlayerValue(player, "Temp.EmblemCode").toString().contains("*")) {
			FileManager.writePrimaryFile("players/" + player.getUniqueId(), "Datas.Temp.EmblemCode", "b;b;6;a;2;a;b;b;b;6;a;0;a;a;a;a;b;b;f;f;a;a;a;a;b;6;f;f;a;a;a;a;b;b;c;7;f;f;f;b;2;6;2;2;7;7;2;b;b;2;a;2;7;7;2;2;b;b;4;4;b;5;5;b;7");
		}
		String design = FileManager.getPlayerValue(player, "Temp.EmblemCode").toString();
		String[] emblems = SGUtils.genEmblem(design).toArray(new String[0]);
		String emblem = String.join("\n", emblems);
		ItemStack book = BookUtil.writtenBook()
				.author("Sky")
				.title("Emblem Editor")
				.pages(
						new BookUtil.PageBuilder()
								.add(new TextComponent(LangManager.getLangKey("GUI.Prefix") + LangManager.getLangKey("Emblem.BookName")))
								.newLine()
								.add(new TextComponent(emblem))
								.build()
				)
				.build();
		BookUtil.openPlayer(player, book);
	}

}
