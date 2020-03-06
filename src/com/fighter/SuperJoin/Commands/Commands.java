package com.fighter.SuperJoin.Commands;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import com.fighter.SuperJoin.Main.Main;
import com.fighter.SuperJoin.Manager.ConfigManager;
import com.fighter.SuperJoin.Placeholdrs.Placeholders;

public class Commands implements CommandExecutor {

	private Main plugin = Main.getPlugin(Main.class);
	Placeholders pch = new Placeholders();
	ConfigManager config = new ConfigManager();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String cmdlabel, String[] args) {
		if (cmd.getName().equalsIgnoreCase("superjoin")) {
			if (args.length == 0) {

				sender.sendMessage(
						ChatColor.translateAlternateColorCodes('&', config.getMessage().getString("usage-message")));
				return true;
			}
			if (args.length >= 1) {
				if (args[0].equalsIgnoreCase("help")) {
					if (sender.hasPermission("superjoin.help") || sender.isOp()
							|| sender.hasPermission("superjoin.*")) {

						String elso = config.getMessage().getString("color-code-1");
						String mas = config.getMessage().getString("color-code-2");
						String harm = config.getMessage().getString("color-code-3");
						String negy = config.getMessage().getString("color-code-4");

						sender.sendMessage(ChatColor.translateAlternateColorCodes('&',config.getMessage().getString("help-message")));
						sender.sendMessage(ChatColor.translateAlternateColorCodes('&', ""));
						sender.sendMessage(ChatColor.translateAlternateColorCodes('&', elso + "# " + mas+ "/sj help " + harm + "- " + negy + "Current page of this plugin"));
						sender.sendMessage(ChatColor.translateAlternateColorCodes('&', elso + "# " + mas+ "/sj reload " + harm + "- " + negy + "Reload the plugin configuration"));
						sender.sendMessage(ChatColor.translateAlternateColorCodes('&', elso + "# " + mas+ "/sj disable <player> " + harm + "- " + negy + "Disable join & quit for specific player"));
						sender.sendMessage(ChatColor.translateAlternateColorCodes('&', elso + "# " + mas+ "/sj enable <player> " + harm + "- " + negy + "Enable join & quit for specific player"));
						sender.sendMessage(ChatColor.translateAlternateColorCodes('&', elso + "# " + mas+ "/sj create player/group <player/group> " + harm + "- " + negy + "Create a custom player/group"));
						sender.sendMessage(ChatColor.translateAlternateColorCodes('&', elso + "# " + mas+ "/sj remove player/group <player/group> " + harm + "- " + negy + "Remove a custom player/group"));

						sender.sendMessage(ChatColor.translateAlternateColorCodes('&', ""));
						sender.sendMessage(ChatColor.translateAlternateColorCodes('&',config.getMessage().getString("help-message")));
						return true;
					}

					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getMessage().getString("no-permission")));
					return true;

				}
				if(args[0].equalsIgnoreCase("reload")) {
					if (sender.hasPermission("superjoin.reload") || sender.isOp()
							|| sender.hasPermission("superjoin.*")) {
						
							config.reloadMessages();
							config.reloadPlayers();
							config.reloadPlayers2();
							plugin.reloadConfig();
							
							sender.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getMessage().getString("reload-message")));
							return true;
						
					}
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getMessage().getString("no-permission")));
					return true;
				}
				if(args[0].equalsIgnoreCase("disable")) {
					if (sender.hasPermission("superjoin.disable") || sender.isOp()|| sender.hasPermission("superjoin.*")) {
						if(args.length <= 1) {
							sender.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getMessage().getString("player-disable-usage")));
							return true;
						}
						if(Bukkit.getPlayer(args[1]) != null) {
								List<String> list = config.getPlayer().getStringList("players");
	
								if(config.getPlayer().getStringList("players").contains(args[1])) {
									
									sender.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getMessage().getString("player-already-exists")));
									return true;
								
								} else {
									
									list.add(args[1]);
									config.getPlayer().set("players", list);
									config.savePlayers();
									sender.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getMessage().getString("player-disable-message")));
									return true;	
								}
								
							} 
							sender.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getMessage().getString("not-online-player")));
							return true;	
						}
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getMessage().getString("no-permission")));
					return true;
					}
				
				if(args[0].equalsIgnoreCase("enable")) { 
					if (sender.hasPermission("superjoin.enable") || sender.isOp()|| sender.hasPermission("superjoin.*")) {
						if(args.length <= 1) {
							sender.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getMessage().getString("player-enable-usage")));
							return true;
						}
						if(Bukkit.getPlayer(args[1]) != null) {
							List<String> list = config.getPlayer().getStringList("players");
							if(config.getPlayer().getStringList("players").contains(args[1])) {
								
								list.remove(args[1]);
								config.getPlayer().set("players", list);
								config.savePlayers();
								sender.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getMessage().getString("player-enable-message")));
								return true;	
								
							} else {
								
								sender.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getMessage().getString("player-not-found")));
								return true;	
							}
							
						}
						sender.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getMessage().getString("not-online-player")));
						return true;
					}
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getMessage().getString("no-permission")));
					return true;
				}
				if(args[0].equalsIgnoreCase("create")) {
					if (sender.hasPermission("superjoin.create") || sender.isOp()|| sender.hasPermission("superjoin.*")) {
						
						if(args.length <= 1) {
						
							sender.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getMessage().getString("player-create-usage")));
							return true;
						}
						if(args[1].equalsIgnoreCase("player")) {
							
							if(args.length <= 2) {
								
								sender.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getMessage().getString("player-create-usage")));
								return true;
							}
								if(config.getPlayer2().getString("customPlayers."+args[2]) == null) {
									
									config.getPlayer2().set("customPlayers."+args[2]+".enable", true);
									config.getPlayer2().set("customPlayers."+args[2]+".join-message", "&e{player-name} &6joined the game");
									config.getPlayer2().set("customPlayers."+args[2]+".quit-message", "&e{player-name} &6left the game");
									config.getPlayer2().set("customPlayers."+args[2]+".join-sound", "BLOCK_NOTE_PLING");
									config.getPlayer2().set("customPlayers."+args[2]+".quit-sound", "BLOCK_ANVIL_DESTROY");
									config.getPlayer2().set("customPlayers."+args[2]+".customtitle.title", "&e&oWelcome {player-name}");
									config.getPlayer2().set("customPlayers."+args[2]+".customtitle.sub-title", "&6&oCustom subtitle");
									config.getPlayer2().set("customPlayers."+args[2]+".customtitle.fadein", 50);
									config.getPlayer2().set("customPlayers."+args[2]+".customtitle.stay", 80);
									config.getPlayer2().set("customPlayers."+args[2]+".customtitle.fadeout", 50);
									config.savePlayers2();
								
									sender.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getMessage().getString("player-create-message")));
									return true;
									
							} 
								
							sender.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getMessage().getString("player-already-exists")));
							return true;
							
						} else if(args[1].equalsIgnoreCase("group")) {
							
							if(args.length <= 2) {
								
								sender.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getMessage().getString("group-create-usage")));
								return true;
							}
							
							if(config.getPlayer2().getString("Groups."+args[2]) == null) {
								
								config.getPlayer2().set("Groups."+args[2]+".enable", true);
								config.getPlayer2().set("Groups."+args[2]+".join-message", "&e{player-name} &6joined the game");
								config.getPlayer2().set("Groups."+args[2]+".quit-message", "&e{player-name} &6left the game");
								config.getPlayer2().set("Groups."+args[2]+".join-sound", "BLOCK_NOTE_PLING");
								config.getPlayer2().set("Groups."+args[2]+".quit-sound", "BLOCK_ANVIL_DESTROY");
								config.getPlayer2().set("Groups."+args[2]+".customtitle.title", "&e&oWelcome {player-name}");
								config.getPlayer2().set("Groups."+args[2]+".customtitle.sub-title", "&6&oCustom subtitle");
								config.getPlayer2().set("Groups."+args[2]+".customtitle.fadein", 50);
								config.getPlayer2().set("Groups."+args[2]+".customtitle.stay", 80);
								config.getPlayer2().set("Groups."+args[2]+".customtitle.fadeout", 50);
								config.savePlayers2();
							
								sender.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getMessage().getString("group-create-message")));
								return true;
								
							} 
							sender.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getMessage().getString("group-already-exists")));
							return true;
							
						}
						sender.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getMessage().getString("unknown-subcommand")));
						return true;

					}
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getMessage().getString("no-permission")));
					return true;
				}
				if(args[0].equalsIgnoreCase("remove")) {
					if (sender.hasPermission("superjoin.remove") || sender.isOp()|| sender.hasPermission("superjoin.*")) {
					
						if(args.length <=1) {
							sender.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getMessage().getString("player-remove-usage")));
							return true;
						}
						if(args[1].equalsIgnoreCase("player")) {
							if(args.length <= 2) {
								sender.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getMessage().getString("player-remove-usage")));
								return true;
							}
							if(config.getPlayer2().getString("customPlayers."+args[2]) != null) {
								
							config.getPlayer2().set("customPlayers."+args[2]+".enable", null);
							config.getPlayer2().set("customPlayers."+args[2]+".join-message", null);
							config.getPlayer2().set("customPlayers."+args[2]+".quit-message", null);
							config.getPlayer2().set("customPlayers."+args[2]+".customtitle.title", null);
							config.getPlayer2().set("customPlayers."+args[2]+".customtitle.sub-title", null);
							config.getPlayer2().set("customPlayers."+args[2]+".customtitle.fadein", null);
							config.getPlayer2().set("customPlayers."+args[2]+".customtitle.stay", null);
							config.getPlayer2().set("customPlayers."+args[2]+".customtitle.fadeout", null);
							config.getPlayer2().set("customPlayers."+args[2], null);
							config.savePlayers2();
							
							sender.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getMessage().getString("player-remove-message")));
							return true;
							
							}
							sender.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getMessage().getString("player-not-found")));
							return true;
							
						}
						if(args[1].equalsIgnoreCase("group")) {
							if(args.length <= 2) {
								sender.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getMessage().getString("group-remove-usage")));
								return true;
							}
							if(config.getPlayer2().getString("Groups."+args[2]) != null) {
								
							config.getPlayer2().set("Groups."+args[2]+".enable", null);
							config.getPlayer2().set("Groups."+args[2]+".join-message", null);
							config.getPlayer2().set("Groups."+args[2]+".quit-message", null);
							config.getPlayer2().set("Groups."+args[2]+".customtitle.title", null);
							config.getPlayer2().set("Groups."+args[2]+".customtitle.sub-title", null);
							config.getPlayer2().set("Groups."+args[2]+".customtitle.fadein", null);
							config.getPlayer2().set("Groups."+args[2]+".customtitle.stay", null);
							config.getPlayer2().set("Groups."+args[2]+".customtitle.fadeout", null);
							config.getPlayer2().set("Groups."+args[2], null);
							config.savePlayers2();
							
							sender.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getMessage().getString("group-remove-message")));
							return true;
							
							}
							sender.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getMessage().getString("group-not-found")));
							return true;
							
						}
						sender.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getMessage().getString("unknown-subcommand")));
						return true;
					}
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getMessage().getString("no-permission")));
					return true;
					
				}
				
			}
		}
		sender.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getMessage().getString("unknown-subcommand")));
		return true;
	}

}
