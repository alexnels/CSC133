package com.mycompany.a1.GameObject.MoveableObjects.Ships;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.geom.Point2D;
import com.mycompany.a1.GameObject.MoveableObjects.ISteerable;
import com.mycompany.a1.GameObject.MoveableObjects.Ship;

public class NonPlayerShip extends Ship implements ISteerable{

    private int size;

    public NonPlayerShip(int size, Point2D location, int heading, int speed, int missileCount) {
        super(heading, missileCount);

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

    public void changeSpeed(int speedChange) {

    }
    public void changeHeading(int degreeChange) {
        this.setHeading(this.getHeading() + degreeChange);

    }

    public String toString() {

        String superToString = super.toString();
        String name = "Non-Player Ship: ";
        String size = " Size:" + this.getSize();

        return name + superToString + size;
    }
}
