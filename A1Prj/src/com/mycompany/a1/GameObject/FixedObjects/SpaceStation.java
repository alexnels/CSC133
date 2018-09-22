package com.mycompany.a1.GameObject.FixedObjects;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.geom.Point2D;
import com.mycompany.a1.GameObject.FixedObject;

public class SpaceStation extends FixedObject {

    //A tickCounter variable is created to help track when the light should toggle on/off
    private int blinkRate;
    private int tickCounter = 0;
    private boolean lightOn;

    //Space station is constructed with an id, blinkRate, coloe,  and location information
    public SpaceStation(int id, int blinkRate, Point2D location) {
        super(id);

        this.blinkRate = blinkRate;
        this.setLocation(location);
        this.setColor(ColorUtil.rgb(0,0,255));
    }

    //Getter for the blinkRate variable of a SpaceStation
    public int getBlinkRate() {
        return blinkRate;
    }

    //Setter for the BlinkRate variable of a SpaceStation
    public void setBlinkRate(int blinkRate) {
        this.blinkRate = blinkRate;
    }

    //Toggles the light depending on the blink period
    public void toggleLight(){
        tickCounter ++;
        if((tickCounter % this.getBlinkRate()) == 0)
            lightOn = !lightOn;

    }

    //the toString() method adds the name and blinkrate information as well as the parent toString()
    public String toString(){
        String superToString = super.toString();
        String name = "SpaceStation: ";
        String blinkRate = " BlinkRate:" + this.blinkRate;
        String lightStatus = " IsLightOn:" + this.lightOn;
        return name + superToString + blinkRate + lightStatus;
    }
}
