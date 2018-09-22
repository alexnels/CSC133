package com.mycompany.a1.GameObject.MoveableObjects;

import com.mycompany.a1.GameObject.MoveableObject;

public abstract class Ship extends MoveableObject {

    //Each Ship contains the missileCount information which tracks how many missiles are left for each ship object
    private int missileCount;

    //THe ship is constructed with a heading and missilecount and calls its super constructor
    public Ship(int heading, int missileCount) {
        super(heading);
        this.missileCount = missileCount;
    }

    //Getter for the missileCount variable of a Ship object
    public int getMissileCount() {
        return missileCount;
    }

    //Setter for the missileCount variable of a Ship object
    public void setMissileCount(int missileCount) {
        this.missileCount = missileCount;
    }

    //The toString() function calls its parent and adds the missileCount information
    public String toString(){
        String superToString = super.toString();
        String missileCount = " MissileCount: " + this.getMissileCount();
        return superToString + missileCount;
    }
}
