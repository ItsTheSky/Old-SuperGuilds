package info.itsthesky.SuperGuilds;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class SuperGuilds extends JavaPlugin {

	@Override
	public void onEnable() {

		/* Dependencies verification */
		List<String> depend = new ArrayList<>();
		List<String> errors = new ArrayList<>();
		depend.add("HolographicDisplays");
		depend.add("WorldEdit");
		depend.add("WorldGuard");
		depend.add("Citizens");
		for (String d : depend) {
			if (!Bukkit.getServer().getPluginManager().isPluginEnabled(d)) {
				errors.add("§cCannot found the depend " + d + " §c! Maybe this plugin is not loaded correctly ?");
			}
		}


		/* Envoie des errors */
		if (errors.size() > 0) {
			for (String e : errors) {
				SGUtils.sendConsole("§4§lERROR: " + e);
			}
		}
	}
}
