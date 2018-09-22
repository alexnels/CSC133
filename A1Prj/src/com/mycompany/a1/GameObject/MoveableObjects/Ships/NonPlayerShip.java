package com.mycompany.a1.GameObject.MoveableObjects.Ships;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.geom.Point2D;
import com.mycompany.a1.GameObject.MoveableObjects.MissileLauncher;
import com.mycompany.a1.GameObject.MoveableObjects.Ship;

public class NonPlayerShip extends Ship{

    //A NonPlayerShip creates its own copy of a MissileLauncher to allow for easier composition
    //A size variable determines the size od a nonPlayerShip
    private int size;
    private MissileLauncher nonPShipMissileLauncher;

    //A non-player ship is constructed with a size, location, heading, speed, missilecount
    //as well as a private MissileLauncher with the same steerable and location information
    public NonPlayerShip(int size, Point2D location, int heading, int speed, int missileCount) {
        super(heading, missileCount);

        this.setSize(size);
        this.setLocation(location);
        this.setColor(ColorUtil.rgb(255,0,0));
        super.setSpeed(speed);

        nonPShipMissileLauncher = new MissileLauncher(location, heading, speed);
    }

    //Getter for the private MissileLauncher that nonPlayetShips own
    public MissileLauncher getNonPShipMissileLauncher() {
        return nonPShipMissileLauncher;
    }

    //Getter for the size variable of a NonPlayerShip
    public int getSize() {
        return size;
    }

    //Setter for the size variable of a NonPlayerShip
    public void setSize(int size) {
        this.size = size;
    }

    //Setter for the speed variable of a NonPlayerShip, automatically updates the MissileLauncher speed as well
    @Override
    public void setSpeed(int speed) {
        super.setSpeed(speed);
        nonPShipMissileLauncher.setSpeed(speed);
    }

    //ToString() adds the name and size information and calls its parent toString()
    public String toString() {

        String superToString = super.toString();
        String name = "Non-Player Ship: ";
        String size = " Size:" + this.getSize();

        return name + superToString + size;
    }
}
