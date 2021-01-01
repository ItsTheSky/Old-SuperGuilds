package info.itsthesky.SuperGuilds.features;

import info.itsthesky.SuperGuilds.Utils.FileManager;
import info.itsthesky.SuperGuilds.Utils.LangManager;
import info.itsthesky.SuperGuilds.Utils.SGUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Race implements Listener {

	public static void changeRace(Player player, String race) throws IOException {
		int raceRights = Integer.parseInt((String) FileManager.checkPrimary("players/" + player.getUniqueId(), "Datas.RaceRight"));
		if (raceRights == 0) {
			SGUtils.sendPlayer(player, LangManager.getLangKey("Race.NoRightsRemaining"));
			return;
		}
		player.closeInventory();
		SGUtils.sendPlayer(player, LangManager.getLangKey("Race.RaceChanged").replace("{RACE}", race));
		raceRights = raceRights-1;
		FileManager.writePrimaryFile("players/" + player.getUniqueId(), "Datas.RaceRight", String.valueOf(raceRights));
		FileManager.writePrimaryFile("players/" + player.getUniqueId(), "Datas.Race", race);
		for (PotionEffect effect : player.getActivePotionEffects())
			player.removePotionEffect(effect.getType());
		loadRaceEffect(player);
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		if (!FileManager.getPlayerValue(event.getPlayer(), "Race").toString().contains("*")) {
			loadRaceEffect(event.getPlayer());
		}
	}

	public static void loadRaceEffect(Player player) {
		String race = (String) FileManager.getPlayerValue(player, "Race");
		ArrayList<String> effects = (ArrayList<String>) FileManager.checkPrimary("races", "Races." + race + ".Effects");
		for (String e : effects) {
			String[] effectslist = e.split("/");
			String eff = effectslist[0];
			String level = effectslist[1];
			player.addPotionEffect(new PotionEffect(Objects.requireNonNull(PotionEffectType.getByName(eff)), 9999999, Integer.parseInt(level)));
		}
	}
}
