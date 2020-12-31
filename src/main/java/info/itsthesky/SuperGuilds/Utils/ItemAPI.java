package info.itsthesky.SuperGuilds.Utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class ItemAPI {

	public ItemStack createItem(Material material, String name, List<String> lore) {
		ItemStack item = new ItemStack(material);
		ItemMeta meta = item.getItemMeta();
		if (lore != null) { meta.setLore(lore); }
		if (name != null) { meta.setDisplayName(name); }
		return item;
	}

	public ItemStack createItem(Material material, String name, List<String> lore, Integer quantity) {
		ItemStack item = new ItemStack(material, quantity);
		ItemMeta meta = item.getItemMeta();
		if (lore != null) { meta.setLore(lore); }
		if (name != null) { meta.setDisplayName(name); }
		return item;
	}

}