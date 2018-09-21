package com.mycompany.a1.GameObject.FixedObjects;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.geom.Point2D;
import com.mycompany.a1.GameObject.FixedObject;

public class SpaceStation extends FixedObject {

    private int blinkRate;

    public SpaceStation(int id, int blinkRate, Point2D location) {
        super(id);

        this.blinkRate = blinkRate;
        this.setLocation(location);
        this.setColor(ColorUtil.rgb(0,0,0));
    }

    public int getBlinkRate() {
        return blinkRate;
    }

    public void setBlinkRate(int blinkRate) {
        this.blinkRate = blinkRate;
    }

    public void toggleLight(){}

    public String toString(){
        String superToString = super.toString();
        String name = "SpaceStation: ";
        String blinkRate = " BlinkRate:" + this.blinkRate;
        return name + superToString + blinkRate;
    }
}
