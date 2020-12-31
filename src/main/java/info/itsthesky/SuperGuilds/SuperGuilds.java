package info.itsthesky.SuperGuilds;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class SuperGuilds extends JavaPlugin {

	@Override
	public void onEnable() {

		List<String> depend = new ArrayList<>();
		depend.add("HolographicDisplays");
		depend.add("WorldEdit");
		depend.add("WorldGuard");
		depend.add("Citizens");
		for (String d : depend) {
			
		}

	}
}
