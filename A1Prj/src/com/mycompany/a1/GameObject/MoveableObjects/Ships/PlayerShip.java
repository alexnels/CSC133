package com.mycompany.a1.GameObject.MoveableObjects.Ships;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.geom.Point2D;
import com.mycompany.a1.GameObject.MoveableObjects.ISteerable;
import com.mycompany.a1.GameObject.MoveableObjects.Ship;


public class PlayerShip extends Ship implements ISteerable {

    private int lives;

    public PlayerShip(Point2D location, int heading, int speed, int missileCount, int lives) {
        super(heading, missileCount);

        this.setLives(lives);
        this.setLocation(location);
        this.setColor(ColorUtil.rgb(0,0,0));
        super.setSpeed(speed);
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    @Override
    public void changeHeading(int degreeChange) {
        this.setHeading(this.getHeading() + degreeChange);
    }

    public String toString() {

        String superToString = super.toString();
        String name = "Player Ship: ";
        String size = " Lives:" + this.getLives();

        return name + superToString + size;
    }
}
