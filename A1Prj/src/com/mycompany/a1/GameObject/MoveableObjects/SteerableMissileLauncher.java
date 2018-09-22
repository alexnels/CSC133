package com.mycompany.a1.GameObject.MoveableObjects;
import com.codename1.ui.geom.Point2D;

public class SteerableMissileLauncher extends MissileLauncher implements ISteerable {

    //A steerableMissileLauncher is created with a location, heading, and speed
    public SteerableMissileLauncher(Point2D location, int heading, int speed) {
        super(location, heading, 0);
    }

    //Because steerableMissileLauncher extends ISteerable it must include the changeHeading() function
    //This changes the heading the functions from moveableObject
    public void changeHeading(int degreeChange) {
        this.setHeading(this.getHeading() + degreeChange);
    }

    //This concrete class adds the name of steerable to the toString() while calling its parent
    public String toString() {
        String superToString = super.toString();
        String name = "Steerable";

        return name + superToString;
    }

}
