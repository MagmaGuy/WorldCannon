package com.magmaguy.worldcannon.utils;

import org.bukkit.Particle;

import java.util.ArrayList;
import java.util.Arrays;

public class VisualEffectProperties {

    public static int trackAmount = 2;
    public static int individualEffectsPerTrack = 2;

    //Secondary effect item processing
    public static void launchVisualEffect() {

        Object[][] multiDimensionalTrailTracker = new Object[trackAmount][individualEffectsPerTrack];

        for (int i = 0; i < multiDimensionalTrailTracker.length; i++) {
            ArrayList<Object> localObjects = new ArrayList<>();
            for (int a = 0; a < multiDimensionalTrailTracker.length; a++)
                localObjects.addAll(addObfuscatedEffects());
            for (int j = 0; j < multiDimensionalTrailTracker[0].length; j++)
                if (localObjects.get(j) != null)
                    multiDimensionalTrailTracker[i][j] = localObjects.get(j);

        }

        VisualItemProcessor visualItemProcessor = new VisualItemProcessor(multiDimensionalTrailTracker,
                RotationCacher.cachedVectors, RotationCacher.NUMBER_OF_POINTS_PER_FULL_ROTATION);

        return;


    }

    private static ArrayList<Object> addObfuscatedEffects() {
        return new ArrayList<>(Arrays.asList(Particle.FIREWORKS_SPARK));
    }

}
