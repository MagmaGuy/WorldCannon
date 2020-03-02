package com.magmaguy.worldcannon;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;

public class CannonLocation {

    public static Location findCannonLocation() {

        FileConfiguration configuration = WorldCannon.plugin.getConfig();

        if (!configuration.getKeys(true).contains(DefaultConfig.WORLD_NAME)) return null;

        World world = Bukkit.getWorld(configuration.getString(DefaultConfig.WORLD_NAME));
        double xValue = configuration.getDouble(DefaultConfig.X_LOCATION);
        double yValue = configuration.getDouble(DefaultConfig.Y_LOCATION);
        double zValue = configuration.getDouble(DefaultConfig.Z_LOCATION);

        Location cannonLocation = new Location(world, xValue, yValue, zValue);

        return cannonLocation;

    }

}
