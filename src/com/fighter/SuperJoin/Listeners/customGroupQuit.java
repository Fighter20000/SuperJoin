package com.fighter.SuperJoin.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.RegisteredServiceProvider;

import com.fighter.SuperJoin.Manager.ConfigManager;
import com.fighter.SuperJoin.Placeholdrs.Placeholders;

import net.milkbowl.vault.permission.Permission;

public class customGroupQuit implements Listener {

	ConfigManager config = new ConfigManager();
	Placeholders pch = new Placeholders();

	@EventHandler(priority = EventPriority.HIGH)
	public void onJoin(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		World world = player.getWorld();
		if (config.getPlayer().getStringList("players").contains(player.getName())) { 
			
			event.setQuitMessage(null);
			
		} else {
			
		if (Bukkit.getPluginManager().getPlugin("Vault") != null) {
			Permission perm = null;
			if (config.getPlayer2().getString("Groups") != null) {
				for (String group : config.getPlayer2().getConfigurationSection("Groups").getKeys(false)) {

					RegisteredServiceProvider<Permission> rsp = Bukkit.getServer().getServicesManager()
							.getRegistration(Permission.class);

					perm = rsp.getProvider();

					if (config.getPlayer2().getString("Groups." + group).contains(perm.getPrimaryGroup(player))) {

							if (config.getPlayer2().getBoolean("Groups." + group + ".enable")) {
								if(config.getPlayer2().getString("Groups." + group + ".quit-message") != null) {
									event.setQuitMessage(pch.placeholder(config.getPlayer2().getString("Groups." + group + ".quit-message"), player,world));
								}
							}
					}
				}
			}
		}
	  }
	}
}
