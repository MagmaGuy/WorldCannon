package com.magmaguy.worldcannon;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.HashSet;

public class CannonTask extends BukkitRunnable {

    private static HashSet<Player> players = new HashSet();

    @Override
    public void run() {

        for (Entity entity : WorldCannon.cannonLocation.getWorld().getNearbyEntities(WorldCannon.cannonLocation, 1, 1, 1)) {

            if (!(entity instanceof Player)) continue;
            if (players.contains(entity)) continue;
            players.add((Player) entity);
            levitatePlayer((Player) entity);

        }

    }

    private static void levitatePlayer(Player player) {

        if (player.hasPotionEffect(PotionEffectType.LEVITATION))
            player.removePotionEffect(PotionEffectType.LEVITATION);

        player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 6 * 20, 3));
        player.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 6 * 20, 1));

        new BukkitRunnable() {

            int counter = 0;

            @Override
            public void run() {

                if (counter == 0)
                    player.sendTitle("Initializing World Cannon", "Keep your arms and legs inside of the ride at all times");

                if (counter > 3 * 3 && counter < 3 * 7) {
                    Location location = CannonRandomizer.generateLocation(WorldCannon.cannonLocation.getWorld());
                    String locationString = ChatColor.translateAlternateColorCodes(
                            '&', "&c" + location.getX() + "&f, &c" + location.getY() + "&f, &c" + location.getZ());
                    player.sendTitle("Picking final location...", locationString, 0, 5, 0);
                }

                if (counter == 3 * 7) {
                    launchPlayer(player);
                    players.remove(player);
                    cancel();
                }


                counter++;

            }

        }.runTaskTimer(WorldCannon.plugin, 0, 3);

    }

    private static void launchPlayer(Player player) {

        Location finalLocation = CannonRandomizer.generateLocation(WorldCannon.cannonLocation.getWorld());

        new BukkitRunnable() {
            int counter = 0;

            @Override
            public void run() {
                if (counter == 0) {
                    String locationString = finalLocation.getX() + ", " + finalLocation.getY() + ", " + finalLocation.getZ();
                    player.sendTitle(ChatColor.translateAlternateColorCodes('&', "&aFinal Destination:"),
                            ChatColor.translateAlternateColorCodes('&', "&a" + locationString));
                }

                if (counter > 2 * 20 && counter < 5 * 20)
                    player.setVelocity(new Vector(0, 2, 0));

                if (counter == 5 * 20) {
                    player.teleport(finalLocation);
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 20 * 30, 1));
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            player.sendTitle("", "Thank you for flying WorldCannon.");
                        }
                    }.runTaskLater(WorldCannon.plugin, 20);
                    cancel();
                }

                counter++;
            }
        }.runTaskTimer(WorldCannon.plugin, 0, 1);

    }

}
