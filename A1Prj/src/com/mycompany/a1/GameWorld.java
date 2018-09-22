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

    //All private final data that the game needs in order to run
    private final int MAX_NPS_MISSILE = 2;
    private final int MAX_PS_MISSILE = 10;
    private final int MAX_PS_LIVES = 3;
    private final int MAX_MISSILE_FUEL = 10;
    private final int ASTEROID_HIT_SCORE = 10;
    private final int NPS_HIT_SCORE = 30;
    private final Point2D INITIAL_SHIP_LOC = new Point2D(512.0,384.0);
    private int gameTicks;
    private int playerScore;
    private boolean exitConfirmed = false;

    //The vector of GameObjects to handle all of the game objects created and added to the game
    private Vector<GameObject> gameObjects = new Vector<>();

    //Intitializing the game, will expand this further in future assignments as needed
    public void init(){
        gameTicks = 0;
        playerScore = 0;

    }

    //This function returns a random number within the bounds it is passed (inclusive)
    private static int randomIntInRange(int lowerBound, int upperBound){
        Random rando = new Random();
        int randomInt = rando.nextInt((upperBound - lowerBound) + 1) + lowerBound;

        return randomInt;
    }

    //Generates a random heading between 0 and 359 degrees
    private int randomHeading(){
        return randomIntInRange(0,359);
    }

    //Generates a random speed between 1 and 10
    private int randomSpeed(){
        return randomIntInRange(1,10);
    }

    //Generates a size of small(10) or large(20) randomly
    private int randomSize(){
        return (randomIntInRange(1,2))*10;
    }

    //Generates a random blink rate between 1 and 4, for Space Stations
    private int randomBlinkRate(){
        return randomIntInRange(1,4);
    }

    //Generates a random location on the map, within the map bounds
    private Point2D randomLocation(){

        Point2D loc = new Point2D(randomIntInRange(0, 1024), randomIntInRange(0,768));
        return loc;
    }

    /*
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
    */

    //Decrements the player ships lives by 1
    //If the ship only has one life left, the game will end
    private boolean decPShipLives(){
        boolean quitGame = false;
        int numOfLives;
        if(playerShipExists()) {
            for (GameObject pShip : gameObjects) {
                if (pShip instanceof PlayerShip) {
                    numOfLives = ((PlayerShip) pShip).getLives();
                    if (numOfLives > 1) {
                        ((PlayerShip) pShip).setLives(numOfLives - 1);
                        return true;
                    } else {
                        ((PlayerShip) pShip).setLives(numOfLives - 1);
                        quitGame = true;
                    }
                }
            }
        }
        //Game exits if the ship is out of lives
        if(quitGame)
            gameOver();
        return false;
    }

    //Removes a Non-Player Ship from the game
    private boolean removeNPS(){
        for(GameObject nps: gameObjects) {
            if(nps instanceof NonPlayerShip) {
                gameObjects.remove(nps);
                removeNonPlayerShipMissileLauncher();
                System.out.println("Removed NON-PLAYER SHIP");
                return true;
            }
        }
        System.out.println("Could not remove NON-PLAYER SHIP");
        return false;
    }

    //Removes an Asteroid from the game
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

    //Removes a Missile from the game
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

    /*
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
*/
    //Removes a Non-Player Ship Missile Launcher from the game
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

    //Returns true if a Player Ship exists within the game
    //Returns false if no Player Ship exists
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

    //Checks to see if a Missile exists withing the game
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

    //Checks to see if a Non-Player Ship exists within the game
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

    //Checks to see if an Asteroid exists within the game
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

    //This function is called when the player runs out of lives
    //It gives them the option to quit, and removes all the old game objects from the game world
    private void gameOver(){
        System.out.println("===========================================");
        System.err.println("GAME OVER");
        System.out.println("The player ship ran out of lives");
        System.out.println("\tIf you would like to quit type 'Y'");
        gameObjects.removeAllElements();
    }

    //This function checks if a Player Ship exists,
    // if it does, the Player Ships index will be returned
    private int getPlayerShipIndex(){
        int pSLocation = 0;
        for (GameObject pShip : gameObjects) {
            if (pShip instanceof PlayerShip) {
                pSLocation = gameObjects.indexOf(pShip);
            }
        }
        return pSLocation;
    }

    //Returns the index of the non-player ship if one exists
    private int getNonPlayerShipIndex(){
        int nonPSLocation = 0;
        for (GameObject nonPShip : gameObjects) {
            if (nonPShip instanceof NonPlayerShip) {
                nonPSLocation = gameObjects.indexOf(nonPShip);
            }
        }
        return nonPSLocation;
    }
    /*

    private int getAsteroidIndex(){
        int asteroidIndex = 0;
        for (GameObject asteroid : gameObjects) {
            if (asteroid instanceof Asteroid) {
                asteroidIndex = gameObjects.indexOf(asteroid);
            }
        }
        return asteroidIndex;
    }
    */

    //Returns the index of the steerable missile launcher
    private int getMissileIndex(){
        int missileIndex = 0;
        for (GameObject missile : gameObjects) {
            if (missile instanceof Missile) {
                missileIndex = gameObjects.indexOf(missile);
            }
        }
        return missileIndex;
    }


    //================================================================================================================
    //
    // This begins the section of required functions to support user commands
    //
    //================================================================================================================

    //Adds a new asteroid to the game world
    //a
    public void addNewAsteroid(){
        Asteroid asteroid = new Asteroid(10, randomLocation(), randomHeading(), randomSpeed());
        gameObjects.add(asteroid);
        System.out.println("A new ASTEROID has been created");
    }

    //Adds a Non-Player Ship to the game world
    //y
    public void addNonPlayerShip(){
        NonPlayerShip nps = new NonPlayerShip(randomSize(), randomLocation(), randomHeading(), randomSpeed(), MAX_NPS_MISSILE);
        MissileLauncher nonPSLauncher = nps.getNonPShipMissileLauncher();
        gameObjects.add(nps);
        gameObjects.add(nonPSLauncher);
        System.out.println("A new NON-PLAYER SHIP has been created");
    }

    //Adds a Space Station to the game  world
    //b
    public void addSpaceStation(){
        SpaceStation spaceStation = new SpaceStation(001, randomBlinkRate(), randomLocation());
        gameObjects.add(spaceStation);
        System.out.println("A new SPACE STATION has been created");
    }

    //Checks to see if a Player Ship already exists within the game world
    //If no Player Ship exists, then it will create one
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

    //Increases the Player Ship speed, up to 10
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

    //Decrease the player ship speed, as low as 0
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

    //Turns the player ships heading by -15 degrees (left)
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

    //Turns the Player Ships heading by 15 degrees (right)
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

    //Turns the Steerable Missile Launcher counter clockwise by 15 degrees (left)
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

    //Fires a Player Ship Missile, after checking that it has enough missiles,
    //adds the Missile to the game world, and decrements the missileCount by 1
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

    //Launches a Non-Player Ship missile and decrements the missileCount of that NPS
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

    //Navigates the PS and SteerableMissileLauncher to the Origin coordinates
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

    //Sets the Player Ship MissileCount to 10
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

    //Removes an asteroid and a missile when they collide
    //k
    public void asteroidHit(){
        if( asteroidExists() & missileExists()) {
            removeAsteroid();
            removeMissile();
            playerScore += ASTEROID_HIT_SCORE;
            System.out.println("Asteroid was HIT by PS MISSILE");
        }
        else
            System.err.println("Could not hit Asteroid with a Player Ship Missile");
    }

    //Removes a Missile and Non-Player Ship when they collide
    //e
    public void nonPSHit(){
        if(nonPShipExists() & missileExists()) {
            removeNPS();
            removeMissile();
            playerScore += NPS_HIT_SCORE;
            System.out.println("NON-PLAYER SHIP was hit by PS MISSILE");
        }
        else
            System.err.println("Could not hit a Non-Player Ship with a Player Ship Missile");
    }

    //Decrements a life from the player ship and removes a missile when they collide
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

    //Removes a life from the player ship when it crashes into an asteroid
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

    //Removes a player ship life and a non-player ship when they collide
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

    //Removes two asteroids when they collide
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

    //Removes a non-player ship and an asteroid when they collide
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

    //Moves all objects that are able to be moved, when the game clock is ticked
    private void moveObjects(){
        boolean moveable = false;
        for(GameObject moveableObj: gameObjects){
            if(moveableObj instanceof IMoveable){
                ((IMoveable) moveableObj).move();
                moveable = true;
            }
        }
        if(moveable) {
            //This insures that the player ship and its missile launcher stay at the same location after they have moved
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

    //updates the fuel levels in the missiles that exist in the game world
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

    //Calls the Space Station function to blink its light depending on its generated peroid
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

    //Calls functions necessary to update the game after a tick has passed
    //t
    public void gameClockTick(){
        System.out.println("Ticking the game clock");
        System.out.println("====================================================================");
        moveObjects();
        updateFuelLevels();
        blinkSpaceStation();
        gameTicks++;
    }

    //Prints a display of the game information: points, missiles, and time
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
                         + "=======  Points: " + playerScore + "  =======  Missiles: " + missileCount + "  =======  Time: " + gameTicks + "  =======\n"
                         + "====================================================================");
    }

    //Prints all of the toString() objects that exist in the game world
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

    //Quits the game and removes all game elements
    //q
    public void quitGame(){
        System.out.println("Exiting the game");
        gameObjects.removeAllElements();
        System.exit(0);
    }

}
