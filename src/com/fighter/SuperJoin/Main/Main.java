package com.fighter.SuperJoin.Main;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import com.fighter.SuperJoin.Commands.Commands;
import com.fighter.SuperJoin.Listeners.JoinListener;
import com.fighter.SuperJoin.Listeners.JoinSound;
import com.fighter.SuperJoin.Listeners.QuitListener;
import com.fighter.SuperJoin.Listeners.QuitSound;
import com.fighter.SuperJoin.Listeners.customGroupJoin;
import com.fighter.SuperJoin.Listeners.customGroupQuit;
import com.fighter.SuperJoin.Listeners.customPlayerJoin;
import com.fighter.SuperJoin.Listeners.customPlayerQuit;
import com.fighter.SuperJoin.Manager.ConfigManager;

public class Main extends JavaPlugin {

	
	public void onEnable() {
		Bukkit.getConsoleSender().sendMessage("§6[SuperJoin] §aPlugin enabled!");
		if(Bukkit.getPluginManager().getPlugin("Vault") != null) {
			Bukkit.getConsoleSender().sendMessage("§6[SuperJoin] §aVault Hooked successful!");
		} else {
			Bukkit.getConsoleSender().sendMessage("§6[SuperJoin] §cGroups are doesn't work without Vault!");
		}
		if(Bukkit.getServer().getPluginManager().getPlugin("PlaceholderAPI") != null) {
			Bukkit.getConsoleSender().sendMessage("§6[SuperJoin] §aPlaceholderAPI Hooked successful!");
		} else {
		Bukkit.getConsoleSender().sendMessage("§6[SuperJoin] §cPlaceholderAPI placeholders are doesn't work without PlaceholderAPI");	
		}
		registerMethods();
	}

	private void registerMethods() {
	
		@SuppressWarnings("unused")
		Metrics metrics = new Metrics(this, 6611);
		registerEvents();
		registerCommands();
		saveDefaultConfig();
		ConfigManager.messageConfiguration();
		ConfigManager.playerConfiguration();
		ConfigManager.playerConfiguration2();
	}
	private void registerCommands() {
		this.getCommand("superjoin").setExecutor(new Commands());
		
	}

	private void registerEvents() {
		Bukkit.getServer().getPluginManager().registerEvents(new QuitSound(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new JoinSound(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new customPlayerJoin(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new customPlayerQuit(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new customGroupQuit(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new customGroupJoin(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new JoinListener(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new QuitListener(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new Metrics(this, 0), this);
		
	}

	public void onDisable() {
		Bukkit.getConsoleSender().sendMessage("[SuperJoin] §cPlugin disabled!");
	}

}
