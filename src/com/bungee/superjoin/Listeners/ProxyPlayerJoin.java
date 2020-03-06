package com.bungee.superjoin.Listeners;

import com.bungee.superjoin.Main.BungeeMain;

import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ServerConnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class ProxyPlayerJoin implements Listener {

	private static BungeeCord plugin = BungeeCord.getInstance();

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onDefaultJoin(ServerConnectEvent event) {
		ProxiedPlayer player = event.getPlayer();
		for (ProxiedPlayer onlinePlayer : BungeeCord.getInstance().getPlayers()) {

			if (event.getPlayer().getServer() == null && event.getTarget() != null) {
				if (BungeeMain.getConfig().getBoolean("network-join-message.enable")) {

					onlinePlayer.sendMessage(placeholders(
							String.join("/n", BungeeMain.getConfig().getStringList("network-join-message.message")),
							player).replace("{target-server}", event.getTarget().getName()));
				}
			} else if (event.getPlayer().getServer() != null && event.getTarget() != null) {
				if (event.getTarget().getName() == player.getServer().getInfo().getName()) {
					
				} else {
					if (BungeeMain.getConfig().getBoolean("server-join-message.enable")) {

						onlinePlayer.sendMessage(placeholders(
						String.join("/n", BungeeMain.getConfig().getStringList("server-join-message.message")),
						player).replace("{target-server}", event.getTarget().getName())
								.replace("{server}", player.getServer().getInfo().getName()));
					}
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
