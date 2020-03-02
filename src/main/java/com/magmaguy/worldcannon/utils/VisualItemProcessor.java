package com.magmaguy.worldcannon.utils;

import com.magmaguy.worldcannon.WorldCannon;
import org.bukkit.Particle;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class VisualItemProcessor {

    VisualItemProcessor(Object[][] multiDimensionalTrailTracker, Vector[][] cachedVectorPositions,
                        int pointsPerRotation) {

        new BukkitRunnable() {

            int counter = 0;

            @Override
            public void run() {

                for (int i = 0; i < multiDimensionalTrailTracker.length; i++) {
                    int sectionCounter = 0;
                    for (int j = 0; j < multiDimensionalTrailTracker[i].length; j++) {

                        int adjustedEffectPositionInRotation = adjustTrackPosition(
                                pointsPerRotation,
                                multiDimensionalTrailTracker[i].length,
                                sectionCounter,
                                counter);

                        Vector vector = cachedVectorPositions[i][adjustedEffectPositionInRotation];

                        if (multiDimensionalTrailTracker[i][j] instanceof Particle)
                            rotateParticle(multiDimensionalTrailTracker[i][j], vector);

                        sectionCounter++;
                        if (sectionCounter >= pointsPerRotation)
                            sectionCounter = 0;

                    }


                }

                counter++;
                if (counter >= pointsPerRotation)
                    counter = 0;


            }

        }.runTaskTimerAsynchronously(WorldCannon.plugin, 0, 2);

    }

    /*
    Adjusts the position in each item track based on the amount of powers currently in that track
     */
    public static int adjustTrackPosition(double pointsPerRotation, int totalEffectQuantity,
                                          int sectionCounter, int globalCounter) {
        int location = (int) (pointsPerRotation / totalEffectQuantity * sectionCounter + globalCounter);
        if (location >= 30)
            location -= 30;
        return location;
    }

    private void rotateParticle(Object particleObject, Vector vector) {
        Particle particle = (Particle) particleObject;
        WorldCannon.cannonLocation.getWorld().spawnParticle(particle, WorldCannon.cannonLocation.clone().add(0, 1, 0).add(vector),
                1, 0, 0, 0, 0.01);
    }

}
