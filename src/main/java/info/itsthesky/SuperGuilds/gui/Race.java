package info.itsthesky.SuperGuilds.gui;

import de.tr7zw.nbtapi.NBTCompound;
import de.tr7zw.nbtapi.NBTItem;
import info.itsthesky.SuperGuilds.SuperGuilds;
import info.itsthesky.SuperGuilds.Utils.FileManager;
import info.itsthesky.SuperGuilds.Utils.ItemAPI;
import info.itsthesky.SuperGuilds.Utils.LangManager;
import info.itsthesky.SuperGuilds.Utils.SGUtils;
import info.itsthesky.SuperGuilds.inventory.InventoryAPI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.util.Collections;
import java.util.Objects;

public class Race {

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
			String emblem = SGUtils.genEmblem((String) FileManager.checkPrimary("races", "Races."+n+".Emblem"));

			ItemStack head = new ItemAPI().createItem(Material.getMaterial((String) FileManager.checkPrimary("races", "Races."+n+".Item")), name, Collections.singletonList(emblem));
			String slot = "123456789"+ FileManager.checkPrimary("races", "Races."+n+".Menu-Number");
			slot = slot.replace("123456789", "");
			inventory.addItem(Integer.parseInt(slot), head);
		}
		inventory.build(player);
	}

}
