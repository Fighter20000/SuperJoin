package com.fighter.SuperJoin.Placeholdrs;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Player;

import me.clip.placeholderapi.PlaceholderAPI;

public class Placeholders {

	public String placeholder(String s, Player player, World world) {
		s = ChatColor.translateAlternateColorCodes('&', s);
		s = s.replace("{player-name}", player.getName());
		s = s.replace("{world}", world.getName());
		if(Bukkit.getServer().getPluginManager().isPluginEnabled("PlaceholderAPI")) {
			s = PlaceholderAPI.setPlaceholders(player, s);
		} 
			
		return s;
	}
}
