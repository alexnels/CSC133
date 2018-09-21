package com.mycompany.a1.GameObject.MoveableObjects;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.geom.Point2D;
import com.mycompany.a1.GameObject.MoveableObject;

public class Asteroid extends MoveableObject {

    private int size;

    public Asteroid(int size, Point2D location, int heading, int speed) {
        super(heading);

        this.setSize(size);
        this.setLocation(location);
        this.setColor(ColorUtil.rgb(0,0,0));
        super.setSpeed(speed);
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {

        String superToString = super.toString();
        String name = "Asteroid: ";
        String size = " Size: " + this.getSize();

        return name + superToString + size;
    }
}
