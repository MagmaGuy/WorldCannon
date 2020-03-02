package com.magmaguy.worldcannon.commands;

import com.magmaguy.worldcannon.DefaultConfig;
import com.magmaguy.worldcannon.WorldCannon;
import org.bukkit.Location;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;

public class SetCannonCommand {

    public static void setCannonLocation(Player player) {

        Location playerLocation = player.getLocation();

        Configuration configuration = WorldCannon.plugin.getConfig();
        configuration.set(DefaultConfig.X_LOCATION, playerLocation.getX());
        configuration.set(DefaultConfig.Y_LOCATION, playerLocation.getY());
        configuration.set(DefaultConfig.Z_LOCATION, playerLocation.getZ());
        configuration.set(DefaultConfig.WORLD_NAME, playerLocation.getWorld().getName());
        WorldCannon.plugin.saveDefaultConfig();
        WorldCannon.plugin.saveConfig();

    }

}
