package com.mycompany.a1.GameObject.MoveableObjects.Ships;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.geom.Point2D;
import com.mycompany.a1.GameObject.MoveableObjects.ISteerable;
import com.mycompany.a1.GameObject.MoveableObjects.Ship;
import com.mycompany.a1.GameObject.MoveableObjects.SteerableMissileLauncher;


public class PlayerShip extends Ship implements ISteerable {

    //A PlayerShip creates its own copy of a steerableMissileLauncher to allow for easier composition
    //A lives variable is added to track how many lives a PlayerShip has
    private int lives;
    private SteerableMissileLauncher playerShipMissileLauncher;

    //A player ship is constructed with a location, heading, speed, missilecount, lives
    //as well as a private steerableMissileLauncher with the same steerable and location information
    public PlayerShip(Point2D location, int heading, int speed, int missileCount, int lives) {
        super(heading, missileCount);

        this.setLives(lives);
        this.setLocation(location);
        this.setColor(ColorUtil.rgb(0,255,0));
        super.setSpeed(speed);

        playerShipMissileLauncher = new SteerableMissileLauncher(location, heading, speed);
    }

    //Getter for the lives variable of a PlayerShip
    public int getLives() {
        return lives;
    }

    //Setter for the lives variable of a PlayerShip
    public void setLives(int lives) {
        this.lives = lives;
    }

    //Getter for the steerableMissileLauncher of a PlayerShip
    public SteerableMissileLauncher getPlayerShipMissileLauncher() {
        return playerShipMissileLauncher;
    }

    //The setSpeed() function is overwritten to automatically update the speed of a steerableMissileLauncher
    @Override
    public void setSpeed(int speed) {
        super.setSpeed(speed);
        playerShipMissileLauncher.setSpeed(speed);
    }

    //The changeHeading is implemented from ISteerable and changes the heading using the super function
    @Override
    public void changeHeading(int degreeChange) {
        this.setHeading(this.getHeading() + degreeChange);
    }

    //toString() adds lives information, a name, and the super toString() information
    public String toString() {

        String superToString = super.toString();
        String name = "Player Ship: ";
        String size = " Lives:" + this.getLives();

        return name + superToString + size;
    }
}
