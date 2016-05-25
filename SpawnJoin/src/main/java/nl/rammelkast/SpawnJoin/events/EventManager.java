package nl.rammelkast.SpawnJoin.events;

import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import nl.rammelkast.SpawnJoin.SpawnJoin;

public class EventManager implements Listener {

	private SpawnJoin plugin;
	
	public EventManager(SpawnJoin spawnJoin) {
		this.plugin = spawnJoin;
	}
	
	@EventHandler(priority=EventPriority.HIGHEST, ignoreCancelled = true)
	public void onJoin(PlayerJoinEvent e) {
		ConfigurationSection section =  plugin.getFileManager().getSpawnsConfig().getConfigurationSection(e.getPlayer().getWorld().getName());
		if (section == null)
			return;
		try {
			e.getPlayer().teleport(new Location(e.getPlayer().getWorld(), (double)section.getDouble("X"), (double)section.getDouble("Y"), (double)section.getDouble("Z"), (float)section.getDouble("Yaw"), (float)section.getDouble("Pitch")));
		}catch(Exception ex) {
			if (e.getPlayer().isOp())
				e.getPlayer().sendMessage("§cAn error occured with your SpawnJoin config!");
		}
	}

}
