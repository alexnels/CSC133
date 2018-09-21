package com.mycompany.a1.GameObject.MoveableObjects.Ships;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.geom.Point2D;
import com.mycompany.a1.GameObject.MoveableObjects.ISteerable;
import com.mycompany.a1.GameObject.MoveableObjects.MissileLauncher;
import com.mycompany.a1.GameObject.MoveableObjects.Ship;

public class NonPlayerShip extends Ship implements ISteerable{

    private int size;
    private MissileLauncher nonPShipMissileLauncher;

    public NonPlayerShip(int size, Point2D location, int heading, int speed, int missileCount) {
        super(heading, missileCount);

        this.setSize(size);
        this.setLocation(location);
        this.setColor(ColorUtil.rgb(0,0,0));
        super.setSpeed(speed);

        nonPShipMissileLauncher = new MissileLauncher(location, heading, speed);
    }

    public MissileLauncher getNonPShipMissileLauncher() {
        return nonPShipMissileLauncher;
    }

    public void setNonPShipMissileLauncher(MissileLauncher nonPShipMissileLauncher) {
        this.nonPShipMissileLauncher = nonPShipMissileLauncher;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void changeHeading(int degreeChange) {
        this.setHeading(this.getHeading() + degreeChange);

    }

    @Override
    public void setSpeed(int speed) {
        super.setSpeed(speed);
        nonPShipMissileLauncher.setSpeed(speed);
    }

    public String toString() {

        String superToString = super.toString();
        String name = "Non-Player Ship: ";
        String size = " Size:" + this.getSize();

        return name + superToString + size;
    }
}
