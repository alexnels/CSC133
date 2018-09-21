package com.mycompany.a1.GameObject.MoveableObjects;

import com.codename1.ui.geom.Point2D;
import com.mycompany.a1.GameObject.MoveableObject;

public class SteerableMissileLauncher extends MissileLauncher implements ISteerable {

    public SteerableMissileLauncher(Point2D location, int heading, int speed) {
        super(location, heading, speed);
    }

    public void changeHeading(int degreeChange) {
        this.setHeading(this.getHeading() + degreeChange);
    }

    public String toString() {

        String superToString = super.toString();
        String name = "Steerable";

        return name + superToString;
    }

}
