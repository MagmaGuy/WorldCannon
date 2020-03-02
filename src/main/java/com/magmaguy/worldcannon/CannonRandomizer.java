package com.magmaguy.worldcannon;

import org.bukkit.Location;
import org.bukkit.World;

import java.util.concurrent.ThreadLocalRandom;

public class CannonRandomizer {

    public static Location generateLocation(World world) {

        Location spawnLocation = world.getSpawnLocation();
        double worldBorderRadius = WorldCannon.plugin.getConfig().getDouble(DefaultConfig.WORLD_RADIUS);

        double xValue = ThreadLocalRandom.current().nextInt((int) (worldBorderRadius * 2)) - worldBorderRadius + spawnLocation.getX();
        double yValue = 257;
        double zValue = ThreadLocalRandom.current().nextInt((int) (worldBorderRadius * 2)) - worldBorderRadius + spawnLocation.getZ();

        Location randomizedLocation = new Location(world, xValue, yValue, zValue);

        return randomizedLocation;

    }

}
