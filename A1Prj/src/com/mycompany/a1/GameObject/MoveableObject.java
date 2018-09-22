package com.mycompany.a1.GameObject;
import com.codename1.ui.geom.Point2D;

public abstract class MoveableObject extends GameObject implements IMoveable {

    private int speed, heading;

    public MoveableObject(int heading) {
        this.heading = heading;
    }

    public void move(){

        //get location change information based on current heading and speed
        double newX = Math.cos(Math.toRadians(90 - this.getHeading())) * this.getSpeed();
        double newY = Math.sin(Math.toRadians(90 - this.getHeading())) * this.getSpeed();

        //get 'old' (current) location through Point2D
        Point2D oldLoc = super.getLocation();
        double oldXLoc = oldLoc.getX();
        double oldYLoc = oldLoc.getX();

        //add these two together and change the current location
        this.setLocation(new Point2D(oldXLoc + newX, oldYLoc + newY));
    }

    public void setHeading(int degreeChange){
        if (degreeChange > 360) {
            this.heading = degreeChange - 360;
        }
        else if (degreeChange < 0) {
            this.heading = 360 + degreeChange;
        }
        else {
            this.heading = degreeChange;
        }
    }

    public int getSpeed(){
        return speed;
    }

    public void setSpeed(int speed){
        this.speed = speed;
    }

    public int getHeading(){
        return heading;
    }

    public String toString()
    {
        String superToString = super.toString();
        String speed = " Speed:" + this.speed;
        String heading = " Heading:" + this.heading;
        return superToString + speed + heading;
    }
}
