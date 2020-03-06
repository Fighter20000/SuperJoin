package com.fighter.SuperJoin.Listeners;

import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import com.fighter.SuperJoin.Main.Main;
import com.fighter.SuperJoin.Manager.ConfigManager;
import com.fighter.SuperJoin.Placeholdrs.Placeholders;

public class QuitListener implements Listener {

	private Main plugin = Main.getPlugin(Main.class);
	private Placeholders pch = new Placeholders();
	private ConfigManager config = new ConfigManager();
	
	@EventHandler
	public void onLeft(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		World world = player.getWorld();

		if (!config.getPlayer().getStringList("players").contains(player.getName())) {
			
		if (plugin.getConfig().getBoolean("default-quit-message.enable")) {
			
			if (plugin.getConfig().getBoolean("default-quit-message.hide-operator-quit") && player.isOp()) {

				event.setQuitMessage(null);

			} else {

				event.setQuitMessage(pch.placeholder(String.join("\n", plugin.getConfig().getStringList("default-quit-message.message")), player,world));

			}

		} else {
			
			event.setQuitMessage(null);
		}
	} else {
		
		event.setQuitMessage(null);
		
	}
  }
}
