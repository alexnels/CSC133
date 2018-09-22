package com.mycompany.a1.GameObject.MoveableObjects;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.geom.Point2D;
import com.mycompany.a1.GameObject.MoveableObject;

public class Asteroid extends MoveableObject {

    private int size;

    //An Asteroid is constructed with a size, location, heading, speed and color
    public Asteroid(int size, Point2D location, int heading, int speed) {
        super(heading);

        this.setSize(size);
        this.setLocation(location);
        this.setColor(ColorUtil.rgb(0,255,255));
        super.setSpeed(speed);
    }

    //Getter for the sie variable of an Asteroid
    public int getSize() {
        return size;
    }

    //Setter for the sie variable of an Asteroid
    public void setSize(int size) {
        this.size = size;
    }

    //The toString() function of an Asteroid adds its name and size variable while calling its parent toString()
    @Override
    public String toString() {

        String superToString = super.toString();
        String name = "Asteroid: ";
        String size = " Size: " + this.getSize();

        return name + superToString + size;
    }
}
