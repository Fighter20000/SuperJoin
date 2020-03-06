package com.fighter.SuperJoin.Listeners;

import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import com.fighter.SuperJoin.Manager.ConfigManager;
import com.fighter.SuperJoin.Placeholdrs.Placeholders;

public class customPlayerQuit implements Listener {

	ConfigManager config = new ConfigManager();
	Placeholders pch = new Placeholders();

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onJoin(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		World world = player.getWorld();
		if (config.getPlayer().getStringList("players").contains(player.getName())) { 
			
			event.setQuitMessage(null);
			
		} else {
		if (config.getPlayer2().getBoolean("customPlayers." + player.getName() + ".enable") == true) {

				if(config.getPlayer2().getString("customPlayers." + player.getName() + ".quit-message") != null) {
					event.setQuitMessage(pch.placeholder(config.getPlayer2().getString("customPlayers." + player.getName() + ".quit-message"),player, world));
				}
		}
	  }
	}
}
