package com.magmaguy.worldcannon.utils;

import org.bukkit.util.Vector;

public class RotationCacher {

    public final static int NUMBER_OF_POINTS_PER_FULL_ROTATION = 30;
    private final static double X = 1;
    private static double Y = 0;
    private final static double Z = 0;
    private static double a, b, c;
    public static Vector[][] cachedVectors = new Vector[2][NUMBER_OF_POINTS_PER_FULL_ROTATION];

    public static void initializeVectorCache() {

        for (int i = 0; i < 2; i++) {
            //45 degree angle between tracks
            if (i == 0) {
                a = -0.6;
                Y = 0.5;
            } else {
                a = 0.6;
                Y = -0.5;
            }

            b = 1;
            c = 0;

            for (int j = 0; j < NUMBER_OF_POINTS_PER_FULL_ROTATION; j++) {

                Vector rotationVector = GenericRotationMatrixMath.applyRotation(a, b, c,
                        NUMBER_OF_POINTS_PER_FULL_ROTATION, X, Y, Z, j);

                cachedVectors[i][j] = rotationVector;

            }

        }

    }

}
