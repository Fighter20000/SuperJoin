package com.bungee.superjoin.Main;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

import com.bungee.superjoin.Commands.BungeeCommands;
import com.bungee.superjoin.Listeners.ProxyPlayerJoin;
import com.bungee.superjoin.Listeners.ProxyPlayerQuit;
import com.bungee.superjoin.metrics.BungeeMetrics;

import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

public class BungeeMain extends Plugin {

	private static File file;
	private static Configuration configuration;
	
	@SuppressWarnings("deprecation")
	@Override
	public void onEnable() {
		
		this.getProxy().getConsole().sendMessage("§6[SuperJoin-BungeeCord] §aPlugin enabled!");
		registerMethods();
		registerConfig();
		
	}



	public static void reloadConfig() {
		try {
			configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			ConfigurationProvider.getProvider(YamlConfiguration.class).save(configuration, file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static Configuration getConfig() {	
		return configuration;
	}
	
	private void registerConfig() {
		
		file = new File(getDataFolder(),"bconfig.yml");
		if(!this.getDataFolder().exists()) {
			this.getDataFolder().mkdir();
		}
		
		if(!file.exists()) {
			try (InputStream in = getResourceAsStream("bconfig.yml")) {
                Files.copy(in, file.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
		}
		
		
		try {
			
			configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);
		
			
			ConfigurationProvider.getProvider(YamlConfiguration.class).save(configuration, file);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onDisable() {
		this.getProxy().getConsole().sendMessage("§6[SuperJoin-BungeeCord] §cPlugin disabled!");
	}
	
	private void registerMethods() {
		new BungeeMetrics(this, 6708);
		BungeeCord.getInstance().pluginManager.registerListener(this, new ProxyPlayerJoin());
		BungeeCord.getInstance().pluginManager.registerListener(this, new ProxyPlayerQuit());
		BungeeCord.getInstance().getPluginManager().registerCommand(this, new BungeeCommands());
		
	}
}
