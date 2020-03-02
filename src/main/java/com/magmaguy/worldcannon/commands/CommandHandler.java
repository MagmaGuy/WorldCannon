package com.magmaguy.worldcannon.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandHandler implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player && args.length == 1 && args[0].equalsIgnoreCase("set"))
            SetCannonCommand.setCannonLocation((Player) sender);

        sender.sendMessage("Command syntax: /wc set");
        return true;

    }

}
