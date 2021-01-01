package info.itsthesky.SuperGuilds.gui;


import info.itsthesky.SuperGuilds.SuperGuilds;
import info.itsthesky.SuperGuilds.Utils.FileManager;
import info.itsthesky.SuperGuilds.Utils.ItemAPI;
import info.itsthesky.SuperGuilds.Utils.LangManager;
import info.itsthesky.SuperGuilds.Utils.SGUtils;
import info.itsthesky.SuperGuilds.features.Race;
import info.itsthesky.SuperGuilds.inventory.InventoryAPI;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class RaceGUI {

	public static void openRaceGUI(Player player) {
		final InventoryAPI inventory = InventoryAPI.create(SuperGuilds.class);
		String customSize = "123456789" + FileManager.checkPrimary("races", "Settings.Menu-Rows");
		customSize = customSize.replace("123456789", "");
		inventory.setSize(9 * Integer.parseInt(customSize));
		inventory.setTitle(LangManager.getLangKey("GUI.Prefix") + LangManager.getLangKey("GUI.Race"));
		inventory.setBorder(new ItemAPI().createItem(Material.GRAY_STAINED_GLASS_PANE, "§1", null));

		File race = new File("plugins/SuperGuilds/races.yml");
		FileConfiguration raceConf = YamlConfiguration.loadConfiguration(race);
		String[] races = raceConf.getConfigurationSection("Races").getKeys(false).toArray(new String[0]);
		for (String n : races) {
			String name = (String) FileManager.checkPrimary("races", "Races."+n+".Name");
			name = (String) FileManager.checkPrimary("races", "Races."+n+".Color") + name;
			name = name.replace("&", "§");

			/* Emblem Generator */
			List<String> lore = new ArrayList<>();
			lore.add("§8§o" + FileManager.checkPrimary("races", "Races." + n + ".Lore").toString());
			lore.add("§1");
			lore.add("§7Emblem:");
			lore.addAll(SGUtils.genEmblem((String) FileManager.checkPrimary("races", "Races." + n + ".Emblem")));

			ItemStack head = new ItemAPI().createItem(Material.getMaterial((String) FileManager.checkPrimary("races", "Races."+n+".Item")), name,lore);
			String slot = "123456789"+ FileManager.checkPrimary("races", "Races."+n+".Menu-Number");
			slot = slot.replace("123456789", "");
			inventory.addItem(Integer.parseInt(slot), head, true, inventoryClickEvent -> {
						try {
							Race.changeRace(player, n);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
			);
		}

		// Close Item
		inventory.addItem(
				Integer.parseInt((String) FileManager.checkPrimary("races", "Settings.Items.Close.Slot")),
				new ItemAPI().createItem(Material.valueOf((String) FileManager.checkPrimary("races", "Settings.Items.Close.Item")), FileManager.checkPrimary("races", "Settings.Items.Close.Name").toString().replace("&", "§"), null), true, inventoryClickEvent -> {
					player.closeInventory();
				}
		);

		// Information Item
		List<String> loresItemInfo = (List<String>) FileManager.checkPrimary("races", "Settings.Items.Information.Lore");
		int sizeOfLore = loresItemInfo.size();
		for (int i = 0; i<sizeOfLore; i++) {
			loresItemInfo.set(i, loresItemInfo.get(i).replace("&", "§"));
		}
		inventory.addItem(
				Integer.parseInt((String) FileManager.checkPrimary("races", "Settings.Items.Information.Slot")),
				new ItemAPI().createItem(Material.valueOf((String) FileManager.checkPrimary("races", "Settings.Items.Information.Item")), FileManager.checkPrimary("races", "Settings.Items.Information.Name").toString().replace("&", "§"), loresItemInfo), true, inventoryClickEvent -> { }
		);

		// Race Item
		inventory.addItem(
				Integer.parseInt((String) FileManager.checkPrimary("races", "Settings.Items.RaceRight.Slot")),
				new ItemAPI().createItem(Material.valueOf((String) FileManager.checkPrimary("races", "Settings.Items.RaceRight.Item")), FileManager.checkPrimary("races", "Settings.Items.RaceRight.Name").toString().replace("&", "§").replace("{RIGHTS}", FileManager.checkPrimary("players/" + player.getUniqueId(), "Datas.RaceRight").toString()), null), true, inventoryClickEvent -> { }
		);

		inventory.build(player);
	}

}
