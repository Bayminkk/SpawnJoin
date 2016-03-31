package nl.rammelkast.SpawnJoin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import nl.rammelkast.SpawnJoin.config.FileManager;
import nl.rammelkast.SpawnJoin.events.EventManager;

public class SpawnJoin extends JavaPlugin {

	private FileManager fileManager;
	private final String VERSION = "2.0.0";
	
	@Override
	public void onEnable() {
		this.fileManager = new FileManager(this);
		Bukkit.getPluginManager().registerEvents(new EventManager(this), this);
	}
	
	@Override
	public void onDisable() {
		this.fileManager.shutDown();
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String cmdLabel, String[] args) {
		if (cmd.getName().equalsIgnoreCase("spawnjoin")) {
			if (args.length == 0) {
				showIndex(sender);
			}else {
				if (args[0].equalsIgnoreCase("set")) {
					ConfigurationSection section =  this.fileManager.getSpawnsConfig().createSection(((Player)sender).getWorld().getName());
					section.set("X", ((Player)sender).getLocation().getX());
					section.set("Y", ((Player)sender).getLocation().getY());
					section.set("Z", ((Player)sender).getLocation().getZ());
					section.set("Yaw", ((Player)sender).getLocation().getYaw());
					section.set("Pitch", ((Player)sender).getLocation().getPitch());
				}else if (args[0].equalsIgnoreCase("tp")) {
					ConfigurationSection section =  getFileManager().getSpawnsConfig().getConfigurationSection(((Player)sender).getWorld().getName());
					if (section == null)
						return true;
					((Player)sender).teleport(new Location(((Player)sender).getWorld(), (double)section.get("X"), (double)section.get("Y"), (double)section.get("Z"), (float)section.get("Yaw"), (float)section.get("Pitch")));
				}else if (args[0].equalsIgnoreCase("rl")) {
					try {
						this.getFileManager().shutDown();
						this.getFileManager().init();
						sender.sendMessage(ChatColor.RED + "Reloaded " + ChatColor.GRAY + "SpawnJoin " + ChatColor.RED + "config!");
					}catch (Exception e) {
						sender.sendMessage(ChatColor.RED + "Could not reload " + ChatColor.GRAY + "SpawnJoin " + ChatColor.RED + "config!");
					}
				}
			}
			return true;
		}
		return false;
	}

	private void showIndex(CommandSender sender) {
		sender.sendMessage(ChatColor.RED + "---" + ChatColor.GRAY + " SpawnJoin v" + VERSION + " " + ChatColor.RED + "---");
		sender.sendMessage(ChatColor.RED + "/spawnjoin " + ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "This command");
		sender.sendMessage(ChatColor.RED + "/spawnjoin rl" + ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "Reload the spawns and config");
		sender.sendMessage(ChatColor.RED + "/spawnjoin set" + ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "Set spawn for current world");
		sender.sendMessage(ChatColor.RED + "/spawnjoin tp [world]" + ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "TP to the spawn of the provided world");
	}

	public FileManager getFileManager() {
		return fileManager;
	}
	
}
