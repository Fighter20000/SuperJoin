package com.fighter.SuperJoin.Listeners;

import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.fighter.SuperJoin.Manager.ConfigManager;
import com.fighter.SuperJoin.Manager.TitleManager;
import com.fighter.SuperJoin.Placeholdrs.Placeholders;

public class customPlayerJoin implements Listener {

	ConfigManager config = new ConfigManager();
	Placeholders pch = new Placeholders();

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		World world = player.getWorld();
		if (config.getPlayer().getStringList("players").contains(player.getName())) { 
			
			event.setJoinMessage(null);
			
		} else {
			
		if (config.getPlayer2().getBoolean("customPlayers." + player.getName() + ".enable") == true) {
			
			if (config.getPlayer2().getBoolean("customPlayers." + player.getName() + ".enable")) {
				
				event.setJoinMessage(pch.placeholder(config.getPlayer2().getString("customPlayers." + player.getName() + ".join-message"), player,world));	

				String title = String.join("\n",
						config.getPlayer2().getString("customPlayers." + player.getName() + ".customtitle.title"));
				String subtitle = String.join("\n",
						config.getPlayer2().getString("customPlayers." + player.getName() + ".customtitle.sub-title"));
				Integer fadein = config.getPlayer2()
						.getInt("customPlayers." + player.getName() + ".customtitle.fadein");
				Integer stay = config.getPlayer2().getInt("customPlayers." + player.getName() + ".customtitle.stay");
				Integer fadeout = config.getPlayer2()
						.getInt("customPlayers." + player.getName() + ".customtitle.fadeout");

				if(config.getPlayer2().getString("customPlayers." + player.getName() + ".customtitle.title") != null 
						&& config.getPlayer2().getString("customPlayers." + player.getName() + ".customtitle.sub-title") != null 
						&& config.getPlayer2().getString("customPlayers." + player.getName() + ".customtitle.fadein") != null 
						&& config.getPlayer2().getString("customPlayers." + player.getName() + ".customtitle.stay") != null 
						&& config.getPlayer2().getString("customPlayers." + player.getName() + ".customtitle.fadeout") != null)  {
							
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
