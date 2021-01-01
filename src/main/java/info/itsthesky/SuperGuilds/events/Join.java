package info.itsthesky.SuperGuilds.events;

import info.itsthesky.SuperGuilds.Utils.FileManager;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Join implements Listener {

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) throws IOException {

		String path = "plugins/SuperGuilds/players/" + event.getPlayer().getUniqueId() + ".yml";
		File playerFile = new File(path);
		if (!playerFile.exists()) {
			FileManager.newFile(path);
			FileConfiguration playerConfig = FileManager.getConfigFile(path);
			List<String> yaml = new ArrayList<>();
			yaml.add("Name;" + event.getPlayer().getDisplayName());
			yaml.add("Guild;*");
			yaml.add("Race;*");
			yaml.add("RaceRight;" + FileManager.checkPrimary("config", "Configuration.DefaultRaceRight"));
			for (String n : yaml) {
				String[] values = n.split(";");
				if (!playerConfig.contains("Datas." + values[0])) {
					FileManager.writeFile(path, "Datas." + values[0], values[1]);
				}
			}
		}
	}
}
