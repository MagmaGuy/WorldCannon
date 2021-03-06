package com.magmaguy.worldcannon.utils;

import org.bukkit.util.Vector;

import static java.lang.Math.cos;
import static java.lang.Math.pow;
import static java.lang.Math.sin;

public class GenericRotationMatrixMath {

    /*
        Logic: Rotation matrix makes something rotate counter-clockwise by theta. The result of this calc is a rotated location.
        The a b c values define the vector in question. The theta defines the angle of the rotation. The x y z define a
        point that will be rotated counter-clockwise around the aforementioned vector by theta degrees. The radius of the
        rotation is defined by the distance of the values x y z from 0, 0, 0.

        In the case of Minor Powers, the vector is 0, 1, 0 so locations can rotate around the vertical axis. The initial
        point is 0.5, 0, 0 so the radius of the location is 0.5 away. Subsequent points are calculated by getting the
        previous point and applying the same rotation (using the counter). It doesn't ever change the vector, as the
        vertical location is changed differently elsewhere to attain a cylindrical effect instead of concentric circles.

        In the case of Major Powers , the vector is 1, 0, 0 so the locations rotate counter-clockwise around an horizontal
        vector. The initial point is 0, 1, 0 so the radius of the rotated location is 1 unit away vertically from the entity.
        The vector gets switched around in the x and z axis so that the effect pivots in the horizontal axis while still
        keeping a concentric nature and the same point of origin.

        The rotation matrix multiplication with coordinates is as follows:
        M(v, theta) = |cos(theta) + (1 - cos(theta)) * a^2    (1 - cos(theta)) * a * b + (sin(theta)) * c    (1 - cos(theta)) * a * c - (sin(theta)) * b| |x|
                      |(1 - cos(theta)) * b * a - (sin (theta)) * c    cos(theta) + (1 - cos(theta)) * b^2    (1 - cos(theta) * b * c + (sin(theta)) * a| |y|
                      |(1 - cos(theta)) * c * a + (sin(theta)) * b    (1 - cos(theta)) * c * b - (sin(theta)) * a    cos(theta) + (1 - cos(theta)) * c^2| |z|

        newX = x * (cos(theta) + (1 - cos(theta)) * a^2) + y * ((1 - cos(theta)) * a * b + (sin(theta))) * c) + z * ((1 - cos(theta)) * a * c - (sin(theta)) * b)
        newY = x * ((1 - cos(theta)) * b * a - (sin (theta)) * c) + y * (cos(theta) + (1 - cos(theta)) * b^2) + z * ((1 - cos(theta) * b * c + (sin(theta)) * a)
        newZ = x * ((1 - cos(theta)) * c * a + (sin(theta)) * b) + y * ((1 - cos(theta)) * c * b - (sin(theta)) * a) + z * (cos(theta) + (1 - cos(theta)) * c^2)

        Quick tweak list:
        a, b, c: Total should be 1. Changes the angle of vector around which the items rotate.
        Theta: Changes the speed at which a location will rotate around the reference point.
        x, y, z: Changes the radius and initial location of the location to be rotated around the vector.
        counter: increments so there can be full rotations. Can be used to predict future or past locations (to add more
        elements per track for example).

        Instead of dealing with theta directly, by doing 360/theta you can pick how many points a single rotation has making
        it easier to predict where things are supposed to be.
    */

    //TODO: register points in hashmap <int, List<Location>>
    private static Vector newVector;
    private static double newX, newY, newZ;

    public static Vector applyRotation(double a, double b, double c, double numberOfPointsPerFullRotation, double x, double y, double z, int counter) {

        double theta;
        //Adapt numberOfPointsPerFullRotation to 360 degrees
        //FUCKING RADIAN (2*pi is 360 degrees for a radius of 1)
        theta = 2 * Math.PI / numberOfPointsPerFullRotation;
        //Get the next rotation
        theta = theta + theta * counter;

        newX = x * (cos(theta) + (1 - cos(theta)) * pow(a, 2)) + y * ((1 - cos(theta)) * a * b + (sin(theta)) * c) + z * ((1 - cos(theta)) * a * c - (sin(theta)) * b);
        newY = x * ((1 - cos(theta)) * b * a - (sin(theta)) * c) + y * (cos(theta) + (1 - cos(theta)) * pow(b, 2)) + z * ((1 - cos(theta)) * b * c + (sin(theta)) * a);
        newZ = x * ((1 - cos(theta)) * c * a + (sin(theta)) * b) + y * ((1 - cos(theta)) * c * b - (sin(theta)) * a) + z * (cos(theta) + (1 - cos(theta)) * pow(c, 2));

        //adjust rotated point
        newVector = new Vector(newX, newY, newZ);

        return newVector;

    }

}