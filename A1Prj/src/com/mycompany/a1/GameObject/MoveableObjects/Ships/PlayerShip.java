package com.mycompany.a1.GameObject.MoveableObjects.Ships;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.geom.Point2D;
import com.mycompany.a1.GameObject.MoveableObjects.ISteerable;
import com.mycompany.a1.GameObject.MoveableObjects.Ship;
import com.mycompany.a1.GameObject.MoveableObjects.SteerableMissileLauncher;


public class PlayerShip extends Ship implements ISteerable {

    private int lives;
    private SteerableMissileLauncher playerShipMissileLauncher;

    public PlayerShip(Point2D location, int heading, int speed, int missileCount, int lives) {
        super(heading, missileCount);

        this.setLives(lives);
        this.setLocation(location);
        this.setColor(ColorUtil.rgb(0,0,0));
        super.setSpeed(speed);

        playerShipMissileLauncher = new SteerableMissileLauncher(location, heading, speed);
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public SteerableMissileLauncher getPlayerShipMissileLauncher() {
        return playerShipMissileLauncher;
    }

    public void setPlayerShipMissileLauncher(SteerableMissileLauncher playerShipMissileLauncher) {
        this.playerShipMissileLauncher = playerShipMissileLauncher;
    }

    @Override
    public void setSpeed(int speed) {
        super.setSpeed(speed);
        playerShipMissileLauncher.setSpeed(speed);
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
