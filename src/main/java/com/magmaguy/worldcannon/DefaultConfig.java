package com.magmaguy.worldcannon;

import org.bukkit.configuration.Configuration;
import org.bukkit.plugin.Plugin;

public class DefaultConfig {

    public static final String WORLD_RADIUS = "World radius";
    private static final String CANNON_LOCATION = "Cannon location.";
    public static final String X_LOCATION = CANNON_LOCATION + "X";
    public static final String Y_LOCATION = CANNON_LOCATION + "Y";
    public static final String Z_LOCATION = CANNON_LOCATION + "Z";
    public static final String WORLD_NAME = CANNON_LOCATION + "World name";

    public static void initializeConfig(Plugin plugin) {

        Configuration configuration = plugin.getConfig();

        configuration.addDefault(WORLD_RADIUS, 5000);

        plugin.saveConfig();
        plugin.saveDefaultConfig();

    }

}
