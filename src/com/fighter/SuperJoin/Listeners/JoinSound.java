package com.fighter.SuperJoin.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.RegisteredServiceProvider;

import com.fighter.SuperJoin.Main.Main;
import com.fighter.SuperJoin.Manager.ConfigManager;
import com.fighter.SuperJoin.Placeholdrs.Placeholders;

import net.milkbowl.vault.permission.Permission;

public class JoinSound implements Listener {

	private Main plugin = Main.getPlugin(Main.class);
	@SuppressWarnings("unused")
	private Placeholders pch = new Placeholders();
	private ConfigManager config = new ConfigManager();
	
	@EventHandler
	public void onSoundQ(PlayerJoinEvent event) {
		
		Player player = event.getPlayer();
		World world = player.getWorld();
		if (config.getPlayer().getStringList("players").contains(player.getName())) { 
			
			event.setJoinMessage(null);
			
		} else {
			
		for (Player online : Bukkit.getOnlinePlayers()) {

			Location location = online.getLocation();

			double x = location.getBlockX();
			double y = location.getBlockY();
			double z = location.getBlockZ();
			world = location.getWorld();

			Location loc = new Location(world, x, y, z);
			
			if(Bukkit.getServer().getPluginManager().getPlugin("Vault") != null) {
				if(config.getPlayer2().getString("Groups") != null) {
					for(String group:config.getPlayer2().getConfigurationSection("Groups").getKeys(false)) {
							Permission perm = null;
							
							RegisteredServiceProvider<Permission> rsp = Bukkit.getServer().getServicesManager().getRegistration(Permission.class);
							 
						    perm = rsp.getProvider();
						    	if(config.getPlayer2().getString("customPlayers."+player.getName()) != null) {
						    if(config.getPlayer2().getString("customPlayers."+player.getName()).contains(player.getName())) {
					    		
	    					if(config.getPlayer2().getBoolean("customPlayers."+player.getName()+".enable")) {
					    		if(config.getPlayer2().getString("customPlayers."+player.getName()+".join-sound") != null) {
					    			
					    			online.playSound(loc, Sound.valueOf(config.getPlayer2().getString("customPlayers."+player.getName()+".join-sound")), 1, 1);
					    			}
					    		}
					    		
					    	} else {
					    	
					    		if(plugin.getConfig().getBoolean("default-join-message.sound-enable")) {
									online.playSound(loc, Sound.valueOf(plugin.getConfig().getString("default-join-message.join-sound")), 1, 1);
								}
					    	  }
						   }
					    if(config.getPlayer2().getString("Groups."+group).contains(perm.getPrimaryGroup(player))) {
					    	if(config.getPlayer2().getBoolean("Groups."+group+".enable")) {
					    		if(config.getPlayer2().getString("Groups."+group+".join-sound") != null) {
					    		online.playSound(loc,Sound.valueOf(config.getPlayer2().getString("Groups."+group+".join-sound")), 1, 1);
					    		}
					    	}
					    	
					    } else {

					    	if(config.getPlayer2().getString("customPlayers."+player.getName()).contains(player.getName())) {
					    		
					    		if(config.getPlayer2().getBoolean("customPlayers."+player.getName()+".enable")) {
					    		if(config.getPlayer2().getString("customPlayers."+player.getName()+".join-sound") != null) {
					    		online.playSound(loc, Sound.valueOf(config.getPlayer2().getString("customPlayers."+player.getName()+".join-sound")), 1, 1);
					    		}
					    		}
					    		
					    	} else {
					    	
					    		if(plugin.getConfig().getBoolean("default-join-message.sound-enable")) {
									online.playSound(loc, Sound.valueOf(plugin.getConfig().getString("default-join-message.join-sound")), 1, 1);
									}
					    	}
					    }
				  }
				} else {
					if(config.getPlayer2().getString("customPlayers") != null) {
						  if(config.getPlayer2().getString("customPlayers."+player.getName()) != null) {
							if(config.getPlayer2().getString("customPlayers."+player.getName()).contains(player.getName())) {
								if(config.getPlayer2().getBoolean("customPlayers."+player.getName()+".enable")) {
									if(config.getPlayer2().getString("customPlayers."+player.getName()+".join-sound") != null) {	
										online.playSound(loc, Sound.valueOf(config.getPlayer2().getString("customPlayers."+player.getName()+".join-sound")), 1, 1);
									}
								}
							
						} else {
							if(plugin.getConfig().getBoolean("default-join-message.sound-enable")) {
							online.playSound(loc, Sound.valueOf(plugin.getConfig().getString("default-join-message.join-sound")), 1, 1);
							}
						}
					  } else {
						  if(plugin.getConfig().getBoolean("default-join-message.sound-enable")) {
								online.playSound(loc, Sound.valueOf(plugin.getConfig().getString("default-join-message.join-sound")), 1, 1);
								}
					  }
					} else {
						if(plugin.getConfig().getBoolean("default-join-message.sound-enable")) {
							online.playSound(loc, Sound.valueOf(plugin.getConfig().getString("default-join-message.join-sound")), 1, 1);
							}
					}
			}
				
		  } else {
			  if(config.getPlayer2().getString("customPlayers") != null) {
				  if(config.getPlayer2().getString("customPlayers."+player.getName()) != null) {
					if(config.getPlayer2().getString("customPlayers."+player.getName()).contains(player.getName())) {
						if(config.getPlayer2().getBoolean("customPlayers."+player.getName()+".enable")) {
							if(config.getPlayer2().getString("customPlayers."+player.getName()+".join-sound") != null) {	
						online.playSound(loc, Sound.valueOf(config.getPlayer2().getString("customPlayers."+player.getName()+".join-sound")), 1, 1);
							}
						}	
					
				} else {
					
					if(plugin.getConfig().getBoolean("default-join-message.sound-enable")) {
						online.playSound(loc, Sound.valueOf(plugin.getConfig().getString("default-join-message.join-sound")), 1, 1);
						}
					
				}
			  } else {
				  if(plugin.getConfig().getBoolean("default-join-message.sound-enable")) {
						online.playSound(loc, Sound.valueOf(plugin.getConfig().getString("default-join-message.join-sound")), 1, 1);
						} 
			  }
			} else {
				if(plugin.getConfig().getBoolean("default-join-message.sound-enable")) {
					online.playSound(loc, Sound.valueOf(plugin.getConfig().getString("default-join-message.join-sound")), 1, 1);
					}
			}
		  }
		}
	  }
	}
}
