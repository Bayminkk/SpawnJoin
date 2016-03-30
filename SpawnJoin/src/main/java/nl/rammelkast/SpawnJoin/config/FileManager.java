package nl.rammelkast.SpawnJoin.config;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import nl.rammelkast.SpawnJoin.SpawnJoin;

public class FileManager {

	private SpawnJoin plugin;
	private File spawnsFile;
	private FileConfiguration spawnsConfig;
	
	public FileManager(SpawnJoin pl) {
		this.plugin = pl;
		try {
			init();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void init() throws IOException {
		spawnsFile = new File(plugin.getDataFolder(), "spawns.yml");
		if (!spawnsFile.exists())
			spawnsFile.createNewFile();
		spawnsConfig = YamlConfiguration.loadConfiguration(spawnsFile);
	}

	public FileConfiguration getSpawnsConfig() {
		return spawnsConfig;
	}

	public void shutDown() {
		try {
			this.spawnsConfig.save(spawnsFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
