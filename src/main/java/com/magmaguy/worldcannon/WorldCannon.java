package com.magmaguy.worldcannon;

import com.magmaguy.worldcannon.commands.CommandHandler;
import com.magmaguy.worldcannon.utils.RotationCacher;
import com.magmaguy.worldcannon.utils.VisualEffectProperties;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class WorldCannon extends JavaPlugin {

    public static Plugin plugin = null;
    public static Location cannonLocation = null;
    public static boolean worldGuard = false;

    @Override
    public void onEnable() {

        plugin = this;
        DefaultConfig.initializeConfig(this);
        cannonLocation = CannonLocation.findCannonLocation();

        if (cannonLocation != null) {
            RotationCacher.initializeVectorCache();
            VisualEffectProperties.launchVisualEffect();
            new CannonTask().runTaskTimer(this, 5, 5);
            Bukkit.getLogger().info("Found valid cannon!");
        }

        this.getCommand("worldcannon").setExecutor(new CommandHandler());

        if (Bukkit.getServer().getPluginManager().getPlugin("WorldGuard") != null){
            worldGuard = true;
        }

    }

    @Override
    public void onDisable() {

    }

}
