package nl.rammelkast.SpawnJoin.events;

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
	
	@EventHandler(priority=EventPriority.MONITOR, ignoreCancelled = true)
	public void onJoin(PlayerJoinEvent e) {
		
	}

}
