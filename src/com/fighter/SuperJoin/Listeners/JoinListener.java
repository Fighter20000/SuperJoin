package com.fighter.SuperJoin.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Builder;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.World;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.meta.FireworkMeta;

import com.fighter.SuperJoin.Main.Main;
import com.fighter.SuperJoin.Manager.ConfigManager;
import com.fighter.SuperJoin.Manager.TitleManager;
import com.fighter.SuperJoin.Placeholdrs.Placeholders;

public class JoinListener implements Listener {

	private Main plugin = Main.getPlugin(Main.class);
	private Placeholders pch = new Placeholders();
	private ConfigManager config = new ConfigManager();

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		World world = player.getWorld();
		if (!player.hasPlayedBefore()) {
			event.setJoinMessage(
					pch.placeholder(String.join("\n", plugin.getConfig().getStringList("default-join-message.message")),
							player, world));
			if (plugin.getConfig().getBoolean("first-join-motd.enable")) {
				player.sendMessage(pch.placeholder(
						String.join("\n", plugin.getConfig().getStringList("first-join-motd.message")), player, world));
			}
			if (plugin.getConfig().getBoolean("first-join-message.enable")) {

				Bukkit.broadcastMessage(pch.placeholder(
						String.join("\n", plugin.getConfig().getStringList("first-join-message.message")), player,
						world));
			}
			if (plugin.getConfig().getBoolean("first-join-title.enable")) {

				String title = String.join("\n", plugin.getConfig().getStringList("first-join-title.title.message"));
				String subtitle = String.join("\n",
						plugin.getConfig().getStringList("first-join-title.sub-title.message"));
				Integer fadein = plugin.getConfig().getInt("first-join-title.fadein");
				Integer stay = plugin.getConfig().getInt("first-join-title.stay");
				Integer fadeout = plugin.getConfig().getInt("first-join-title.fadeout");

				TitleManager.sendTitle(player, fadein, stay, fadeout, pch.placeholder(title, player, world),
						pch.placeholder(subtitle, player, world));
			}
		}
		if (!config.getPlayer().getStringList("players").contains(player.getName())) {

			if (plugin.getConfig().getBoolean("default-join-message.hide-operator-join") && player.isOp()) {

				event.setJoinMessage(null);

			} else {

				if (plugin.getConfig().getBoolean("join-motd.enable")) {
					if (player.hasPlayedBefore()) {
						String motd = String.join("\n", plugin.getConfig().getStringList("join-motd.message"));
						player.sendMessage(pch.placeholder(motd, player, world));
					}
				}

				if (plugin.getConfig().getBoolean("default-join-message.enable")) {

					if (player.hasPlayedBefore()) {
						event.setJoinMessage(pch.placeholder(
								String.join("\n", plugin.getConfig().getStringList("default-join-message.message")),
								player, world));
						if(plugin.getConfig().getBoolean("join-firework.enable")) {
						
				Firework fw = player.getWorld().spawn(player.getLocation(), Firework.class);
				
				FireworkMeta fwm = fw.getFireworkMeta();
				
				Builder builder = FireworkEffect.builder();

				fwm.addEffect(builder.withColor(Color.fromRGB(plugin.getConfig().getInt("join-firework.color.R"), plugin.getConfig().getInt("join-firework.color.G"),plugin.getConfig().getInt("join-firework.color.B"))).flicker(plugin.getConfig().getBoolean("join-firework.flicker")).with(Type.valueOf(plugin.getConfig().getString("join-firework.type"))).trail(plugin.getConfig().getBoolean("join-firework.trail")).build());
				
				fwm.addEffect(builder.withColor(Color.fromRGB(plugin.getConfig().getInt("join-firework.color2.R"), plugin.getConfig().getInt("join-firework.color2.G"),plugin.getConfig().getInt("join-firework.color2.B"))).flicker(plugin.getConfig().getBoolean("join-firework.flicker")).with(Type.valueOf(plugin.getConfig().getString("join-firework.type"))).trail(plugin.getConfig().getBoolean("join-firework.trail")).build());
				
				fwm.addEffect(builder.withColor(Color.fromRGB(plugin.getConfig().getInt("join-firework.color3.R"), plugin.getConfig().getInt("join-firework.color3.G"),plugin.getConfig().getInt("join-firework.color3.B"))).flicker(plugin.getConfig().getBoolean("join-firework.flicker")).with(Type.valueOf(plugin.getConfig().getString("join-firework.type"))).trail(plugin.getConfig().getBoolean("join-firework.trail")).build());
				
				fwm.setPower((int)1);
				
				fw.setFireworkMeta(fwm);
						
						}
					}

				} else {

					event.setJoinMessage(null);
				}

				if (plugin.getConfig().getBoolean("join-title.enable")) {

					if (player.hasPlayedBefore()) {
						
						String title = String.join("\n", plugin.getConfig().getStringList("join-title.title.message"));
						String subtitle = String.join("\n",
								plugin.getConfig().getStringList("join-title.sub-title.message"));
						Integer fadein = plugin.getConfig().getInt("join-title.fadein");
						Integer stay = plugin.getConfig().getInt("join-title.stay");
						Integer fadeout = plugin.getConfig().getInt("join-title.fadeout");

						TitleManager.sendTitle(player, fadein, stay, fadeout, pch.placeholder(title, player, world),
								pch.placeholder(subtitle, player, world));
					}
				}
			}
		} else {

			event.setJoinMessage(null);
		}

	}
}
