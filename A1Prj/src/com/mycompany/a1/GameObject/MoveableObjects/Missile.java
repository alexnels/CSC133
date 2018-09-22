package com.mycompany.a1.GameObject.MoveableObjects;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.geom.Point2D;
import com.mycompany.a1.GameObject.MoveableObject;

public class Missile extends MoveableObject {

    private int fuelLevel;

    //A Missile is constructed with a location, heading, speed, and fuel level, as well as a color
    public Missile(Point2D location, int heading, int speed, int fuelLevel) {
        super(heading);

        this.setLocation(location);
        this.fuelLevel = fuelLevel;
        this.setColor(ColorUtil.rgb(255,255,255));
        if (speed == 0)
            this.setSpeed(4);
        else if(speed > 0)
            this.setSpeed(speed * 2);
    }

    //Getter for the fuelLevel variable of a Missile
    public int getFuelLevel() {
        return fuelLevel;
    }

    //Setter for the fuelLevel variable of a Missile
    public void setFuelLevel(int fuelLevel) {
        this.fuelLevel = fuelLevel;
    }

    //The toString() method calls its parent and adds the name as well as the fuel level of a Missile
    @Override
    public String toString() {

        String superToString = super.toString();
        String name = "Missile: ";
        String fuelLevel = " Fuel: " + this.getFuelLevel();

        return name + superToString + fuelLevel;
    }
}
