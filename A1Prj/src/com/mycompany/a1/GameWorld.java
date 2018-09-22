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
    private int gameTicks = 0;

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
        if(!playerShipExists()) {
            PlayerShip playerShip = new PlayerShip(INITIAL_SHIP_LOC, 0, 0, MAX_PS_MISSILE, MAX_PS_LIVES);
            SteerableMissileLauncher smLauncher = playerShip.getPlayerShipMissileLauncher();
            gameObjects.add(playerShip);
            gameObjects.add(smLauncher);
            System.out.println("A new PLAYER SHIP has been created");
        }
        else
            System.err.println("A Player Ship already exists");
    }

    //i
    public void increasePSSpeed(){
        if(playerShipExists()) {
            for (GameObject pShip : gameObjects) {
                if (pShip instanceof PlayerShip) {
                    int currSpeed = ((PlayerShip) pShip).getSpeed();
                    if (currSpeed > 8) {
                        System.out.println("Cannot increase Player Ship speed.");
                    } else {
                        ((PlayerShip) pShip).setSpeed(currSpeed + 2);
                        System.out.println("PLAYER SHIP speed has been INCREASED");
                    }
                }
            }
        }
        else
            System.err.println("Could not increase Player Ship speed");
    }

    //d
    public void decreasePSSpeed(){
        if(playerShipExists()) {
            for (GameObject pShip : gameObjects) {
                if (pShip instanceof PlayerShip) {
                    int currSpeed = ((PlayerShip) pShip).getSpeed();

                    if (currSpeed < 2) {
                        System.out.println("Cannot decrease Player Ship speed.");
                    } else {
                        ((PlayerShip) pShip).setSpeed(currSpeed - 2);
                        System.out.println("PLAYER SHIP speed has been DECREASED");
                    }
                }
            }
        }
        else
            System.err.println("Could not decrease Player Ship speed");
    }

    //l
    public void turnPSLeft(){
        if(playerShipExists()) {
            for (GameObject pShip : gameObjects) {
                if (pShip instanceof PlayerShip) {
                    ((PlayerShip) pShip).changeHeading(-15);
                    System.out.println("Turn player ship LEFT");
                }
            }
        }
        else
            System.err.println("Could not turn Player Ship Left");
    }

    //r
    public void turnPSRight() {
        if (playerShipExists()) {
            for (GameObject pShip : gameObjects) {
                if (pShip instanceof PlayerShip) {
                    ((PlayerShip) pShip).changeHeading(15);
                    System.out.println("Turn player ship RIGHT");
                    return;
                }
            }
        } else
            System.err.println("Could not turn Player Ship right");
    }

    //<
    public void turnMissileLauncher(){
        if(playerShipExists()) {
            for (GameObject pShip : gameObjects) {
                if (pShip instanceof PlayerShip) {
                    SteerableMissileLauncher psMissileLauncher = ((PlayerShip) pShip).getPlayerShipMissileLauncher();
                    psMissileLauncher.changeHeading(-15);
                    System.out.println("MISSILE LAUNCHER turned counter-clockwise");
                }
            }
        }
        else
            System.err.println("Could not turn the Player Missile Launcher");
    }

    private int getPlayerShipIndex(){
        int pSLocation = 0;
        for (GameObject pShip : gameObjects) {
            if (pShip instanceof PlayerShip) {
                pSLocation = gameObjects.indexOf(pShip);
            }
        }
        return pSLocation;
    }

    private int getNonPlayerShipIndex(){
        int nonPSLocation = 0;
        for (GameObject nonPShip : gameObjects) {
            if (nonPShip instanceof NonPlayerShip) {
                nonPSLocation = gameObjects.indexOf(nonPShip);
            }
        }
        return nonPSLocation;
    }

    private int getAsteroidIndex(){
        int asteroidIndex = 0;
        for (GameObject asteroid : gameObjects) {
            if (asteroid instanceof Asteroid) {
                asteroidIndex = gameObjects.indexOf(asteroid);
            }
        }
        return asteroidIndex;
    }

    private int getMissileIndex(){
        int missileIndex = 0;
        for (GameObject missile : gameObjects) {
            if (missile instanceof Missile) {
                missileIndex = gameObjects.indexOf(missile);
            }
        }
        return missileIndex;
    }

    //f
    public void firePSMissile(){
        if(playerShipExists()) {
            int psIndex = getPlayerShipIndex();
            PlayerShip pShip = (PlayerShip) gameObjects.get(psIndex);
            int numOfMissiles = pShip.getMissileCount();
            if (numOfMissiles < 1)
                System.err.println("Player Ship is out of missiles, could not fire");
            else {
                pShip.setMissileCount(numOfMissiles - 1);
                SteerableMissileLauncher  pSMissileLauncher = pShip.getPlayerShipMissileLauncher();
                Missile firedMissile = new Missile(pSMissileLauncher.getLocation(), (pSMissileLauncher).getHeading(),(pSMissileLauncher).getSpeed(), MAX_MISSILE_FUEL);
                gameObjects.add(firedMissile);
                System.out.println("A PLAYER SHIP missile has been FIRED");
            }
        }
        else
            System.err.println("Could not fire a Player Ship Missile");
    }

    //L
    public void launchNPSMissile(){
        if(nonPShipExists()) {
            int nonPSIndex = getNonPlayerShipIndex();
            NonPlayerShip nonPShip = (NonPlayerShip) gameObjects.get(nonPSIndex);
            int numOfMissiles = nonPShip.getMissileCount();
            if (numOfMissiles < 1)
                System.err.println("Non-Player Ship is out of missiles, could not fire");
            else {
                nonPShip.setMissileCount(numOfMissiles - 1);
                MissileLauncher nonPSMissileLauncher = nonPShip.getNonPShipMissileLauncher();
                Missile firedMissile = new Missile(nonPSMissileLauncher.getLocation(), nonPSMissileLauncher.getHeading(), nonPSMissileLauncher.getSpeed(), MAX_MISSILE_FUEL);
                gameObjects.add(firedMissile);
                System.out.println("A NON-PLAYER SHIP missile has been Launched");
            }
        }
        else
            System.err.println("Could not fire a Non-Player Ship Missile");
    }

    //j
    public void jumpHyperspace(){
        if(playerShipExists()) {
            for(GameObject pShip: gameObjects) {
                if(pShip instanceof PlayerShip) {
                    pShip.setLocation(INITIAL_SHIP_LOC);
                    SteerableMissileLauncher  pSMissileLauncher = ((PlayerShip) pShip).getPlayerShipMissileLauncher();
                    pSMissileLauncher.setLocation(INITIAL_SHIP_LOC);
                    System.out.println("Player Ship has JUMPED through hyperspace");
                }
            }
        }
        else
            System.err.println("Could not JUMP through hyperspace");
    }

    //n
    public void reloadPSMissiles(){
        if(playerShipExists()) {
            for (GameObject pShip : gameObjects) {
                if (pShip instanceof PlayerShip) {
                    ((PlayerShip) pShip).setMissileCount(MAX_PS_MISSILE);
                    System.out.println("PLAYER SHIPS missiles were reloaded");
                }
            }
        }
        else
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
        else
            System.err.println("Could not hit Asteroid with a Player Ship Missile");
    }

    //e
    public void nonPSHit(){
        if(nonPShipExists() & missileExists()) {
            removeNPS();
            removeMissile();
            PLAYER_SCORE += NPS_HIT_SCORE;
            System.out.println("NON-PLAYER SHIP was hit by PS MISSILE");
        }
        else
            System.err.println("Could not hit a Non-Player Ship with a Player Ship Missile");
    }

    //E
    public void playerShipHit(){
        if(playerShipExists() & missileExists()) {
            removeMissile();
            if (decPShipLives())
                System.out.println("NON-PLAYER SHIP MISSILE hit PLAYER SHIP");
        }
        else
            System.err.println("Could not hit a Player Ship with a Non-Player Ship Missile");
    }

    //c
    public void playerShipCrashed(){
        if( asteroidExists() & playerShipExists()) {
            removeAsteroid();
            if(decPShipLives())
                System.out.println("PLAYER SHIP hit an ASTEROID");
        }
        else
            System.err.println("Could not hit a Player Ship with an Asteroid");
    }

    //h
    public void playerShipHitNPS(){
        if( nonPShipExists() & playerShipExists()) {
            removeNPS();
            if(decPShipLives())
                System.out.println("PLAYER SHIP hit a NON-PLAYER SHIP");
        }
        else
            System.err.println("Could not hit a Player Ship with a Non-Player Ship");

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
            else
                System.err.println("Only one ASTEROID was removed");
        }
        else
            System.err.println("Could crash two Asteroids together");
    }

    //I
    public void asteroidHitNPS(){
        if( nonPShipExists() & asteroidExists()) {
            removeNPS();
            removeAsteroid();
            System.out.println("ASTEROID impacted a NON-PLAYER SHIP");
        }
        else
            System.err.println("Could not hit a Non-Player Ship with an Asteroid");
    }

    private void moveObjects(){
        boolean moveable = false;
        for(GameObject moveableObj: gameObjects){
            if(moveableObj instanceof IMoveable){
                ((IMoveable) moveableObj).move();
                moveable = true;
            }
        }
        if(moveable) {
            if(playerShipExists()){
                for (GameObject pShip : gameObjects) {
                    if (pShip instanceof PlayerShip) {
                        SteerableMissileLauncher psMissileLauncher = ((PlayerShip) pShip).getPlayerShipMissileLauncher();
                        psMissileLauncher.setLocation(pShip.getLocation());
                    }
                }
            }
            System.out.println("Moved all moveable objects");
        }
        else
            System.out.println("No moveable objects exist");
    }

    private void updateFuelLevels(){
        if(missileExists()){
            Missile missile = (Missile) gameObjects.get(getMissileIndex());
            int fuelLevel = missile.getFuelLevel();
            if (fuelLevel <= 1) {
                gameObjects.remove(missile);
                System.out.println("\tRemoved a missile that ran out of fuel");
            } else
                missile.setFuelLevel(fuelLevel - 1);
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
        System.out.println("Ticking the game clock");
        System.out.println("====================================================================");
        moveObjects();
        updateFuelLevels();
        blinkSpaceStation();
        gameTicks++;
    }

    //p
    public void printDisplay(){
        int psIndex, missileCount = -1;
        if (playerShipExists()) {
            psIndex = getPlayerShipIndex();
            PlayerShip pShip = (PlayerShip) gameObjects.get(psIndex);
            missileCount = pShip.getMissileCount();
        }
        System.out.println("====================================================================\n"
                         + "======================= Current Game States: =======================\n"
                         + "=======  Points: " + PLAYER_SCORE + "  =======  Missiles: " + missileCount + "  =======  Time: " + gameTicks + "  =======\n"
                         + "====================================================================");
    }

    //m
    public void printMap(){
        System.out.println("====================================================================\n"
                         + "=======================  Printing Game Map  ========================\n"
                         + "====================================================================");
        for(GameObject gObj: gameObjects) {
            System.out.println(gObj.toString());
        }
        System.out.println("====================================================================\n");
    }

    //q
    public void quitGame(){
        System.out.println("Exit the game");
        //gw.exit();
    }

}
