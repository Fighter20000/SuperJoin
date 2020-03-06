package com.fighter.SuperJoin.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.RegisteredServiceProvider;

import com.fighter.SuperJoin.Manager.ConfigManager;
import com.fighter.SuperJoin.Manager.TitleManager;
import com.fighter.SuperJoin.Placeholdrs.Placeholders;

import net.milkbowl.vault.permission.Permission;

public class customGroupJoin implements Listener {

	ConfigManager config = new ConfigManager();
	Placeholders pch = new Placeholders();

	@EventHandler(priority = EventPriority.HIGH)
	public void onJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		World world = player.getWorld();
		if (config.getPlayer().getStringList("players").contains(player.getName())) { 
			
			event.setJoinMessage(null);
			
		} else {
		if (Bukkit.getPluginManager().getPlugin("Vault") != null) {
			Permission perm = null;
			if (config.getPlayer2().getString("Groups") != null) {
				for (String group : config.getPlayer2().getConfigurationSection("Groups").getKeys(false)) {

					RegisteredServiceProvider<Permission> rsp = Bukkit.getServer().getServicesManager()
							.getRegistration(Permission.class);

					perm = rsp.getProvider();

					if (config.getPlayer2().getString("Groups." + group).contains(perm.getPrimaryGroup(player))) {

						if (config.getPlayer2().getBoolean("Groups." + group + ".enable") == true) {
							if (config.getPlayer2().getBoolean("Groups." + group + ".enable")) {

								if(config.getPlayer2().getString("Groups." + group + ".join-message") != null) {
								event.setJoinMessage(pch.placeholder(config.getPlayer2().getString("Groups." + group + ".join-message"), player,world));
								}
								String title = String.join("\n",
										config.getPlayer2().getString("Groups." + group + ".customtitle.title"));
								String subtitle = String.join("\n",
										config.getPlayer2().getString("Groups." + group + ".customtitle.sub-title"));
								Integer fadein = config.getPlayer2().getInt("Groups." + group + ".customtitle.fadein");
								Integer stay = config.getPlayer2().getInt("Groups." + group + ".customtitle.stay");
								Integer fadeout = config.getPlayer2()
										.getInt("Groups." + group + ".customtitle.fadeout");

								if(config.getPlayer2().getString("Groups." + group + ".customtitle.title") != null 
								&& config.getPlayer2().getString("Groups." + group + ".customtitle.sub-title") != null 
								&& config.getPlayer2().getString("Groups." + group + ".customtitle.fadein") != null 
								&& config.getPlayer2().getString("Groups." + group + ".customtitle.stay") != null 
								&& config.getPlayer2().getString("Groups." + group + ".customtitle.fadeout") != null)  {
									
								TitleManager.sendTitle(player, fadein, stay, fadeout,
										pch.placeholder(title, player, world),
										pch.placeholder(subtitle, player, world));
								} else  {
									
								}
							}

						} else {
							return;
						}
					}
				}
			}
		}
	  }
	}
}
