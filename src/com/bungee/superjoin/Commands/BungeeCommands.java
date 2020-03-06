package com.bungee.superjoin.Commands;

import com.bungee.superjoin.Main.BungeeMain;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Command;

public class BungeeCommands extends Command {



	public BungeeCommands() {
		super("superjoin");
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		if(args.length == 0) {
			if(sender.hasPermission("superjoin.bungee.admin")) {
			TextComponent message = new TextComponent("Reload the plugin configuration! Click Here!");
			message.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/superjoin reload"));
			message.setColor(ChatColor.GREEN);
			sender.sendMessage(message);
			} else {
				TextComponent message = new TextComponent("[SuperJoin-Bungee] ");
				message.setColor(ChatColor.GOLD);
				TextComponent error = new TextComponent("You don't have enough permission for this command");
				error.setColor(ChatColor.DARK_RED);
				sender.sendMessage(new TextComponent(message,error));
			}
		}
		if(args.length == 1) {
			if(args[0].equalsIgnoreCase("reload")) {
				if(sender.hasPermission("superjoin.bungee.admin")) {
				BungeeMain.reloadConfig();
				TextComponent message = new TextComponent("[SuperJoin-Bungee] ");
				message.setColor(ChatColor.GOLD);
				TextComponent string = new TextComponent("Successfully reloaded the plugin configuration!");
				string.setColor(ChatColor.GREEN);
				sender.sendMessage(new TextComponent(message,string));
				} else {
					TextComponent message = new TextComponent("[SuperJoin-Bungee] ");
					message.setColor(ChatColor.GOLD);
					TextComponent error = new TextComponent("You don't have enough permission for this command");
					error.setColor(ChatColor.DARK_RED);
					sender.sendMessage(new TextComponent(message,error));
				}
			}
			
		}

	}

}
