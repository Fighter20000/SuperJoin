package com.fighter.SuperJoin.Manager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import com.fighter.SuperJoin.Main.Main;

public class ConfigManager {

	private static Main plugin = Main.getPlugin(Main.class);
	
	//players.yml configuration
	private static FileConfiguration playercfg;
	private static File players;
	
	public FileConfiguration getPlayer() {
		return playercfg;
	}
	public static void playerConfiguration() {
		players = new File(plugin.getDataFolder(), "players.yml");
		if(!players.exists()) {
			players.getParentFile().mkdirs();
			plugin.saveResource("players.yml", false);
		}
		playercfg = new YamlConfiguration();
		try {
			playercfg.load(players);
		} catch (IOException | InvalidConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void reloadPlayers() {
		playercfg = new YamlConfiguration();
		try {
			playercfg.load(players);
		} catch (FileNotFoundException e) {
			Bukkit.getConsoleSender().sendMessage("§c[SuperJoin] §c'players.yml' not found, creating one");
			players.getParentFile().mkdirs();
			plugin.saveResource("players.yml", false);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidConfigurationException e) {
			Bukkit.getConsoleSender().sendMessage("§c[SuperJoin] §cYour configuration is wrong!");
			e.printStackTrace();
		}
	}
	
	
	//custom.yml configuration
		private static FileConfiguration playerConfig;
		private static File players2;
		
		public FileConfiguration getPlayer2() {
			return playerConfig;
		}
		public static void playerConfiguration2() {
			players2 = new File(plugin.getDataFolder(), "custom.yml");
			if(!players2.exists()) {
				players2.getParentFile().mkdirs();
				plugin.saveResource("custom.yml", false);
			}
			playerConfig = new YamlConfiguration();
			try {
				playerConfig.load(players2);
			} catch (IOException | InvalidConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		public void reloadPlayers2() {
			playerConfig = new YamlConfiguration();
			try {
				playerConfig.load(players2);
			} catch (FileNotFoundException e) {
				Bukkit.getConsoleSender().sendMessage("§c[SuperJoin] §c'custom.yml' not found, creating one");
				players2.getParentFile().mkdirs();
				plugin.saveResource("custom.yml", false);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidConfigurationException e) {
				Bukkit.getConsoleSender().sendMessage("§c[SuperJoin] §cYour configuration is wrong!");
				e.printStackTrace();
			}
		}
	
	//messages.yml configuration
	private static FileConfiguration messagesCFG;
	private static File messages;
	
	public FileConfiguration getMessage() {
		return messagesCFG;
	}
	public static void messageConfiguration() {
		messages = new File(plugin.getDataFolder(), "messages.yml");
		if(!messages.exists()) {
			messages.getParentFile().mkdirs();
			plugin.saveResource("messages.yml", false);
		}
		messagesCFG = new YamlConfiguration();
		try {
			messagesCFG.load(messages);
		} catch (IOException | InvalidConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void reloadMessages() {
		messagesCFG = new YamlConfiguration();
		try {
			messagesCFG.load(messages);
		} catch (FileNotFoundException e) {
			Bukkit.getConsoleSender().sendMessage("§c[SuperJoin] §c'Messages.yml' not found, creating one");
			ConfigManager.messageConfiguration();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidConfigurationException e) {
			Bukkit.getConsoleSender().sendMessage("§c[SuperJoin] §cYour configuration is wrong!");
			e.printStackTrace();
		}
	}
	
	public void saveMessages() {
		
	}
	public void savePlayers() {
		
		try {
			playercfg.save(players);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void savePlayers2() {
		try {
			playerConfig.save(players2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
