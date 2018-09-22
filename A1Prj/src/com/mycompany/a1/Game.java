package com.mycompany.a1;

import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Point2D;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;


public class Game extends Form{

    private GameWorld gw;

    private int size;
    private Point2D location;
    private int color;


    public Game() {
        gw = new GameWorld();
        gw.init();
        play();
    }

    public void play(){
        Label myLabel = new Label("Enter a Command: ");
        this.addComponent(myLabel);

        final  TextField myTextField = new TextField();

        this.addComponent(myTextField);
        this.show();

        myTextField.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                String sCommand = myTextField.getText().toString();
                myTextField.clear();

                if(sCommand.isEmpty()){
                    System.err.println("ERROR: Invalid Input");
                    return;
                }

                switch (sCommand.charAt(0))
                {
                    case 'a':
                        gw.addNewAsteroid();
                        break;

                    case 'y':
                        gw.addNonPlayerShip();
                        break;

                    case 'b':
                        gw.addSpaceStation();
                        break;

                    case 's':
                        gw.addPlayerShip();
                        break;

                    case 'i':
                        gw.increasePSSpeed();
                        break;

                    case 'd':
                        gw.decreasePSSpeed();
                        break;

                    case 'l':
                        gw.turnPSLeft();
                        break;

                    case 'r':
                        gw.turnPSRight();
                        break;

                    case '<':
                        gw.turnMissileLauncher();
                        break;

                    case 'f':
                        gw.firePSMissile();
                        break;

                    case 'L':
                        gw.launchNPSMissile();
                        break;

                    case 'j':
                        gw.jumpHyperspace();
                        break;

                    case 'n':
                        gw.reloadPSMissiles();
                        break;

                    case 'k':
                        gw.asteroidHit();
                        break;

                    case 'e':
                        gw.nonPSHit();
                        break;

                    case 'E':
                        gw.playerShipHit();
                        break;

                    case 'c':
                        gw.playerShipCrashed();
                        break;

                    case 'h':
                        gw.playerShipHitNPS();
                        break;

                    case 'x':
                        gw.asteroidsCollide();
                        break;

                    case 'I':
                        gw.asteroidHitNPS();
                        break;

                    case 't':
                        gw.gameClockTick();
                        break;

                    case 'p':
                        gw.printDisplay();
                        break;

                    case 'm':
                        gw.printMap();
                        break;

                    case 'q':
                        System.out.println("Are you sure you would like to quit?\n\tType 'Y' to quit");
                        break;

                    case 'Y':
                        gw.quitGame();
                        break;

                    default:
                        System.err.println("ERROR: Invalid Input");
                }
            }
        });

    }
}
