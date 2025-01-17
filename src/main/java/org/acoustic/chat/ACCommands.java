package org.acoustic.chat;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class ACCommands implements CommandExecutor{
    
	private static AcousticChat plugin;
	
	public ACCommands(AcousticChat plugin) {
		ACCommands.plugin = plugin;
	}
	
	@Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(((sender instanceof Player) && ((Player)sender).isOp()) || sender instanceof ConsoleCommandSender)) {
			sender.sendMessage(ChatColor.RED + "Permission denied" + ChatColor.RESET);
			return true;
		}
		if (args[0].equals("reload")) {
			plugin.reloadConfig();
			sender.sendMessage("org.acoustic.chat.AcousticChat reloaded");
			return true;
		} else if (args[0].equals("noise")) {
			if (args.length < 3) return false;
			StringBuilder text = new StringBuilder();
			for (int i = 2;i < args.length;i++) {
				text.append(args[i]);
				if (i != args.length - 1) text.append(" ");
			}
			sender.sendMessage(Math.addNoise(text.toString(), Double.parseDouble(args[1])));
			return true;
		}//might be better to have else and put return false in that
		return false;
    }
}
