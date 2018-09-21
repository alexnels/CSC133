package com.mycompany.a1;

import com.mycompany.a1.GameObject.FixedObjects.SpaceStation;
import com.mycompany.a1.GameObject.GameObject;
import com.mycompany.a1.GameObject.IMoveable;
import com.mycompany.a1.GameObject.MoveableObjects.Asteroid;
import com.mycompany.a1.GameObject.MoveableObjects.Missile;
import com.mycompany.a1.GameObject.MoveableObjects.MissileLauncher;
import com.mycompany.a1.GameObject.MoveableObjects.Ships.NonPlayerShip;
import com.mycompany.a1.GameObject.MoveableObjects.Ships.PlayerShip;
import com.codename1.ui.geom.Point2D;
import com.mycompany.a1.GameObject.MoveableObjects.SteerableMissileLauncher;

import java.util.Random;
import java.util.Vector;

public class GameWorld {

    private final int MAX_NPS_MISSILE = 2;
    private final int MAX_PS_MISSILE = 10;
    private final int MAX_PS_LIVES = 3;
    private final int MAX_MISSILE_FUEL = 10;
    private final int ASTEROID_HIT_SCORE = 10;
    private final int NPS_HIT_SCORE = 30;
    private final Point2D INITIAL_SHIP_LOC = new Point2D(512.0,384.0);

    private int PLAYER_SCORE = 0;

    Vector<GameObject> gameObjects = new Vector<>();

    public void init(){

    }

    public void printErrorStatement(){
        System.err.println("ERROR: Invalid input");
    }

    private static int randomIntInRange(int lowerBound, int upperBound){
        Random rando = new Random();
        int randomInt = rando.nextInt((upperBound - lowerBound) + 1) + lowerBound;

        return randomInt;
    }

    private int randomHeading(){
        return randomIntInRange(0,359);
    }

    private int randomSpeed(){
        return randomIntInRange(1,10);
    }

    //Generates a size of small(10) or large(20) randomly
    private int randomSize(){
        return (randomIntInRange(1,2))*10;
    }

    private int randomBlinkRate(){
        return randomIntInRange(1,4);
    }

    private Point2D randomLocation(){

        Point2D loc = new Point2D(randomIntInRange(0, 1024), randomIntInRange(0,768));
        return loc;
    }

    private boolean removePS(){
        for(GameObject pShip: gameObjects) {
            if(pShip instanceof PlayerShip) {
                gameObjects.remove(pShip);
                System.out.println("Removed PLAYER SHIP");
                return true;
            }
        }
        System.out.println("Could not remove PLAYER SHIP");
        return false;
    }

    private int getPShipLives(){
        int numOfLives;
        for(GameObject pShip: gameObjects) {
            if(pShip instanceof PlayerShip) {
                numOfLives = ((PlayerShip) pShip).getLives();
                return numOfLives;
            }
        }
        System.out.println("Player Ship does not exist");
        return 0;
    }

    private boolean decPShipLives(){
        int numOfLives;
        for(GameObject pShip: gameObjects) {
            if(pShip instanceof PlayerShip) {
                numOfLives = ((PlayerShip) pShip).getLives();
                if (numOfLives > 1) {
                    ((PlayerShip) pShip).setLives(numOfLives - 1);
                    return true;
                }
                else{
                    ((PlayerShip) pShip).setLives(numOfLives - 1);
                    gameOver();
                }
            }
        }
        System.out.println("Player Ship does not exist");
        return false;
    }

    private boolean removeNPS(){
        for(GameObject nps: gameObjects) {
            if(nps instanceof NonPlayerShip) {
                gameObjects.remove(nps);
                System.out.println("Removed NON-PLAYER SHIP");
                return true;
            }
        }
        System.out.println("Could not remove NON-PLAYER SHIP");
        return false;
    }

    private boolean removeAsteroid(){
        for(GameObject asteroid: gameObjects) {
            if(asteroid instanceof Asteroid) {
                gameObjects.remove(asteroid);
                System.out.println("Removed ASTEROID");
                return true;
            }
        }
        System.out.println("Could not remove ASTEROID");
        return false;
    }

    private boolean removeMissile(){
        for(GameObject missile: gameObjects) {
            if(missile instanceof Missile) {
                gameObjects.remove(missile);
                System.out.println("Removed MISSILE");
                return true;
            }
        }
        System.out.println("Could not remove MISSILE");
        return false;
    }

    private boolean removePlayerShipMissileLauncher(){
        for(GameObject psMissileLauncher: gameObjects) {
            if(psMissileLauncher instanceof SteerableMissileLauncher) {
                gameObjects.remove(psMissileLauncher);
                System.out.println("Removed Player Ship Missile Launcher");
                return true;
            }
        }
        System.out.println("Could not remove Player Ship Missile Launcher");
        return false;
    }

    private boolean removeNonPlayerShipMissileLauncher(){
        for(GameObject nonPSMissileLauncher: gameObjects) {
            if(nonPSMissileLauncher instanceof MissileLauncher) {
                gameObjects.remove(nonPSMissileLauncher);
                System.out.println("Removed NON-Player Ship Missile Launcher");
                return true;
            }
        }
        System.out.println("Could not remove NON-Player Ship Missile Launcher");
        return false;
    }

    private boolean playerShipExists()
    {
        boolean exists = false;
        for(GameObject pShip: gameObjects) {
            if(pShip instanceof PlayerShip) {
                exists = true;
            }
        }
        if(!exists)
            System.out.println("Player Ship does not exist.");
        return exists;
    }

    private boolean missileExists()
    {
        boolean exists = false;
        for(GameObject missile: gameObjects) {
            if(missile instanceof Missile) {
                exists = true;
            }
        }
        if(!exists)
            System.out.println("A Missile does not exist.");
        return exists;
    }

    private boolean nonPShipExists()
    {
        boolean exists = false;
        for(GameObject nonPS: gameObjects) {
            if(nonPS instanceof NonPlayerShip) {
                exists = true;
            }
        }
        if(!exists)
            System.out.println("No Non-Player Ships exist.");
        return exists;
    }

    private boolean asteroidExists()
    {
        boolean exists = false;
        for(GameObject asteroid: gameObjects) {
            if(asteroid instanceof Asteroid) {
                exists = true;
            }
        }
        if(!exists)
            System.out.println("No Asteroids exist.");
        return exists;
    }


    private void gameOver(){
        System.out.println("GAME OVER");
        gameObjects.removeAllElements();
    }





    //a
    public void addNewAsteroid(){
        Asteroid asteroid = new Asteroid(10, randomLocation(), randomHeading(), randomSpeed());
        gameObjects.add(asteroid);
        System.out.println("A new ASTEROID has been created");
    }

    //y
    public void addNonPlayerShip(){
        NonPlayerShip nps = new NonPlayerShip(randomSize(), randomLocation(), randomHeading(), randomSpeed(), MAX_NPS_MISSILE);
        gameObjects.add(nps);
        System.out.println("A new NON-PLAYER SHIP has been created");
    }

    //b
    public void addSpaceStation(){
        SpaceStation spaceStation = new SpaceStation(001, randomBlinkRate(), randomLocation());
        gameObjects.add(spaceStation);
        System.out.println("A new SPACE STATION has been created");
    }

    //s
    public void addPlayerShip(){
        PlayerShip playerShip = new PlayerShip(INITIAL_SHIP_LOC, 0, 0, MAX_PS_MISSILE, MAX_PS_LIVES);
        SteerableMissileLauncher smLauncher = playerShip.getPlayerShipMissileLauncher();
        gameObjects.add(playerShip);
        gameObjects.add(smLauncher);
        System.out.println("A new PLAYER SHIP has been created");
    }

    //i
    public void increasePSSpeed(){
        for(GameObject pShip: gameObjects) {
            if(pShip instanceof PlayerShip) {
                int currSpeed = ((PlayerShip) pShip).getSpeed();

                if(currSpeed > 8) {
                    System.out.println("Cannot increase Player Ship speed.");
                    return;
                }
                else {
                    ((PlayerShip) pShip).setSpeed(currSpeed + 2);
                    System.out.println("PLAYER SHIP speed has been INCREASED");
                    return;
                }
            }
        }
        System.out.println("No PLAYER SHIP has yet been created");
    }

    //d
    public void decreasePSSpeed(){
        for(GameObject pShip: gameObjects) {
            if(pShip instanceof PlayerShip) {
                int currSpeed = ((PlayerShip) pShip).getSpeed();

                if(currSpeed < 2) {
                    System.out.println("Cannot decrease Player Ship speed.");
                    return;
                }
                else {
                    ((PlayerShip) pShip).setSpeed(currSpeed - 2);
                    System.out.println("PLAYER SHIP speed has been DECREASED");
                    return;
                }
            }
        }
        System.out.println("No PLAYER SHIP has yet been created");
    }

    //l
    public void turnPSLeft(){
        for(GameObject pShip: gameObjects) {
            if(pShip instanceof PlayerShip) {
                ((PlayerShip) pShip).changeHeading(-15);
                System.out.println("Turn player ship LEFT");
                return;
            }
        }
        System.out.println("No PLAYER SHIP has yet been created");
    }

    //r
    public void turnPSRight(){
        for(GameObject pShip: gameObjects) {
            if(pShip instanceof PlayerShip) {
                ((PlayerShip) pShip).changeHeading(15);
                System.out.println("Turn player ship RIGHT");
                return;
            }
        }
        System.out.println("No PLAYER SHIP has yet been created");
    }

    //<
    public void turnMissileLauncher(){
        if(playerShipExists()) {
            for (GameObject pShip : gameObjects) {
                if (pShip instanceof PlayerShip) {
                    SteerableMissileLauncher psMissileLauncher = ((PlayerShip) pShip).getPlayerShipMissileLauncher();
                    psMissileLauncher.changeHeading(-15);
                    System.out.println("MISSILE LAUNCHER turned counter-clockwise");
                    return;
                }
            }
        }
        System.out.println("No Player Ship or Steerable Missile Launcher exist");
    }

    //f
    public void firePSMissile(){
        if(playerShipExists()) {
            for (GameObject pShip : gameObjects) {
                if (pShip instanceof PlayerShip) {
                    int numOfMissiles = ((PlayerShip) pShip).getMissileCount();
                    if (numOfMissiles < 1) {
                        System.out.println("Player Ship is out of missiles");
                    } else {
                        ((PlayerShip) pShip).setMissileCount(numOfMissiles - 1);
                        SteerableMissileLauncher  pSMissileLauncher = ((PlayerShip) pShip).getPlayerShipMissileLauncher();
                        Missile firedMissile = new Missile(pSMissileLauncher.getLocation(), (pSMissileLauncher).getHeading(),(pSMissileLauncher).getSpeed(), MAX_MISSILE_FUEL);
                        gameObjects.add(firedMissile);
                        System.out.println("A PLAYER SHIP missile has been FIRED");
                        return;
                    }

                }
            }
        }
        System.err.println("Could not fire a Player Ship Missile");
    }

    //L
    public void launchNPSMissile(){
        if(nonPShipExists()) {
            for (GameObject nonPShip : gameObjects) {
                if (nonPShip instanceof NonPlayerShip) {
                    int numOfMissiles = ((NonPlayerShip) nonPShip).getMissileCount();
                    if (numOfMissiles < 1) {
                        System.out.println("ERROR: Non-Player Ship is out of missiles");
                    } else {
                        ((NonPlayerShip) nonPShip).setMissileCount(numOfMissiles - 1);
                        MissileLauncher nonPSMissileLauncher = ((NonPlayerShip) nonPShip).getNonPShipMissileLauncher();
                        Missile firedMissile = new Missile(nonPSMissileLauncher.getLocation(), nonPSMissileLauncher.getHeading(), nonPSMissileLauncher.getSpeed(), MAX_MISSILE_FUEL);
                        gameObjects.add(firedMissile);
                        System.out.println("A NON-PLAYER SHIP missile has been Launched");
                        return;
                    }

                }
            }
        }
        System.err.println("Could not fire a Non-Player Ship Missile");
    }

    //j
    public void jumpHyperspace(){
        if(playerShipExists()) {
            for(GameObject pShip: gameObjects) {
                if(pShip instanceof PlayerShip) {
                    pShip.setLocation(INITIAL_SHIP_LOC);
                    System.out.println("Player Ship has JUMPED through hyperspace");
                    return;
                }
            }
        }
        System.err.println("Could not JUMP through hyperspace");
    }

    //n
    public void reloadPSMissiles(){
        if( playerShipExists()) {
            for (GameObject pShip : gameObjects) {
                if (pShip instanceof PlayerShip) {
                    ((PlayerShip) pShip).setMissileCount(MAX_PS_MISSILE);
                    System.out.println("PLAYER SHIPS missiles were reloaded");
                }
            }
        }
        System.err.println("Could not reload Player Ship missiles");
    }

    //k
    public void asteroidHit(){
        if( asteroidExists() & missileExists()) {
            removeAsteroid();
            removeMissile();
            PLAYER_SCORE += ASTEROID_HIT_SCORE;
            System.out.println("Asteroid was HIT by PS MISSILE");
        }
    }

    //e
    public void nonPSHit(){
        if(nonPShipExists() & missileExists()) {
            removeNPS();
            removeMissile();
            PLAYER_SCORE += NPS_HIT_SCORE;
            System.out.println("NON-PLAYER SHIP was hit by PS MISSILE");
        }
    }

    //E
    public void playerShipHit(){
        if(playerShipExists() & missileExists()) {
            removeMissile();
            if (decPShipLives())
                System.out.println("NON-PLAYER SHIP MISSILE hit PLAYER SHIP");
        }
    }

    //c
    public void playerShipCrashed(){
        if( asteroidExists() & playerShipExists()) {
            removeAsteroid();
            if(decPShipLives())
                System.out.println("PLAYER SHIP hit an ASTEROID");
        }
    }

    //h
    public void playerShipHitNPS(){
        if( nonPShipExists() & playerShipExists()) {
            removeNPS();
            if(decPShipLives())
                System.out.println("PLAYER SHIP hit an ASTEROID");
        }
        System.out.println("PLAYER SHIP hit a NON-PLAYER SHIP");
    }

    //x
    public void asteroidsCollide(){
        if(asteroidExists()){
            removeAsteroid();
            if(asteroidExists())
            {
                removeAsteroid();
                System.out.println("Two ASTEROIDs collided");
            }
            System.out.println("Only one ASTEROID was removed");
        }
    }

    //I
    public void asteroidHitNPS(){
        if( nonPShipExists() & asteroidExists()) {
            removeNPS();
            removeAsteroid();
            System.out.println("PLAYER SHIP hit a NON-PLAYER SHIP");
            System.out.println("ASTEROID impacted a NON-PLAYER SHIP");
        }
    }

    private void moveObjects(){
        boolean moveable = false;
        for(GameObject moveableObj: gameObjects){
            if(moveableObj instanceof IMoveable){
                ((IMoveable) moveableObj).move();
                moveable = true;
            }
        }
        if(moveable)
            System.out.println("Moved all moveable objects");
        else
            System.out.println("No moveable objects exist");
    }

    private void updateFuelLevels(){
        if(missileExists()) {
            for (GameObject missile : gameObjects) {
                if (missile instanceof Missile) {
                    int fuelLevel = ((Missile) missile).getFuelLevel();
                    if (fuelLevel < 1) {
                        gameObjects.remove(missile);
                        System.out.println("Removed a missile that ran out of fuel");
                    } else
                        ((Missile) missile).setFuelLevel(fuelLevel - 1);
                }
            }
        }
        else
            System.out.println("No missiles exist");
    }

    private void blinkSpaceStation(){
        boolean sSExists = false;
        for(GameObject spaceStation: gameObjects) {
            if (spaceStation instanceof SpaceStation) {
                ((SpaceStation) spaceStation).toggleLight();
                sSExists = true;
            }
        }
        if (sSExists)
            System.out.println("Space station light was toggled");
        else
            System.out.println("No space station exists");
    }

    //t
    public void gameClockTick(){
        moveObjects();
        updateFuelLevels();
        blinkSpaceStation();
        System.out.println("Tick the game clock");
    }

    //p
    public void printDisplay(){
        System.out.println("Print a display of current game states");

    }

    //m
    public void printMap(){
        System.out.println("====================================================================\n"
                         + "=======================  Printing Game Map  ========================\n"
                         + "====================================================================");
        for(GameObject gObj: gameObjects) {
            System.out.println(gObj.toString());
        }
    }

    //q
    public void quitGame(){
        System.out.println("Exit the game");
        //gw.exit();
    }

}
