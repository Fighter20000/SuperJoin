package com.bungee.superjoin.Listeners;

import com.bungee.superjoin.Main.BungeeMain;

import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ServerDisconnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class ProxyPlayerQuit implements Listener {

private static BungeeCord plugin = BungeeCord.getInstance();
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onDefaultJoin(ServerDisconnectEvent event) {
		ProxiedPlayer player = event.getPlayer();
		for (ProxiedPlayer onlinePlayer : BungeeCord.getInstance().getPlayers()) {
			if(event.getPlayer().getServer() != null && event.getTarget() == null) {
				if (BungeeMain.getConfig().getBoolean("network-quit-message.enable")) {
					onlinePlayer.sendMessage(placeholders(String.join("/n", BungeeMain.getConfig().getStringList("network-quit-message.message")),player)
							.replace("{target-server}", event.getTarget().getName()));
				}
			} else if(event.getPlayer().getServer().getInfo().getName().equals(event.getTarget().getName())) {
				if (BungeeMain.getConfig().getBoolean("network-quit-message.enable")) {
					onlinePlayer.sendMessage(placeholders(String.join("/n", BungeeMain.getConfig().getStringList("network-quit-message.message")),player)
							.replace("{target-server}", event.getTarget().getName()));
				}
			} else if(event.getTarget() != null && player.getServer() != null) {
				if (BungeeMain.getConfig().getBoolean("server-quit-message.enable")) {
					onlinePlayer.sendMessage(placeholders(String.join("/n", BungeeMain.getConfig().getStringList("server-quit-message.message")),player)
							.replace("{target-server}", event.getTarget().getName())
							.replace("{server}", player.getServer().getInfo().getName()));
				}
			}
			
		}
	}

	private String placeholders(String string, ProxiedPlayer player) {
		string = string.replace("{player-name}", player.getName());
		string = ChatColor.translateAlternateColorCodes('&', string);
		String players = Integer.toString(plugin.getPlayers().size());
		string = string.replace("{online-players}", players);
		return string;
	}
}
