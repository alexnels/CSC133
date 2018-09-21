package com.mycompany.a1.GameObject.MoveableObjects;

import com.mycompany.a1.GameObject.MoveableObject;

public abstract class Ship extends MoveableObject {

    private int missileCount;

    public Ship(int heading, int missileCount) {
        super(heading);
        this.missileCount = missileCount;
    }

    public int getMissileCount() {
        return missileCount;
    }

    public void setMissileCount(int missileCount) {
        this.missileCount = missileCount;
    }

    public String toString(){
        String superToString = super.toString();
        String missileCount = " MissileCount:" + this.getMissileCount();
        return superToString + missileCount;
    }
}
