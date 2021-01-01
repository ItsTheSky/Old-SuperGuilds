package info.itsthesky.SuperGuilds.gui;

import info.itsthesky.SuperGuilds.SuperGuilds;
import info.itsthesky.SuperGuilds.Utils.FileManager;
import info.itsthesky.SuperGuilds.inventory.InventoryAPI;
import org.bukkit.entity.Player;

public class MainGUI {

	public static void openMainGUI(Player player) {

		if (FileManager.getPlayerValue(player, "Race").toString().contains("*")) {
			RaceGUI.openRaceGUI(player);
			return;
		}

		final InventoryAPI inventory = InventoryAPI.create(SuperGuilds.class);
		inventory.setSize(9 * 6);
		inventory.setTitle("Mon premier inventaire");
		inventory.build(player);
	}

}
