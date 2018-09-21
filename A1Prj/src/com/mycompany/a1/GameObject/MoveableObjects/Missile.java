package com.mycompany.a1.GameObject.MoveableObjects;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.geom.Point2D;
import com.mycompany.a1.GameObject.MoveableObject;

public class Missile extends MoveableObject {

    private int fuelLevel;

    public Missile(Point2D location, int heading, int speed, int fuelLevel) {
        super(heading);

        this.setLocation(location);
        this.fuelLevel = fuelLevel;
        this.setColor(ColorUtil.rgb(255,0,255));
        this.setSpeed(speed);
    }

    public int getFuelLevel() {
        return fuelLevel;
    }

    public void setFuelLevel(int fuelLevel) {
        this.fuelLevel = fuelLevel;
    }

    @Override
    public String toString() {

        String superToString = super.toString();
        String name = "Missile: ";
        String fuelLevel = " Fuel: " + this.getFuelLevel();

        return name + superToString + fuelLevel;
    }
}
