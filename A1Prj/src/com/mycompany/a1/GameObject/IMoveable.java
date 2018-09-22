package com.mycompany.a1.GameObject;

//The IMoveable interface contains the necessary functions to
//allow an onbject to be manipulated in the game world
public interface IMoveable {

    //Required by all moveable objects to update their location
    public void move();

    public int getSpeed();

    public void setSpeed(int speed);

    public int getHeading();

    public void setHeading(int heading);
}
