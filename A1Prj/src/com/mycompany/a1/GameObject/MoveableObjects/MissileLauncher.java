package com.mycompany.a1.GameObject.MoveableObjects;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.geom.Point2D;
import com.mycompany.a1.GameObject.MoveableObject;

public class MissileLauncher extends MoveableObject {


    public MissileLauncher(Point2D location, int heading, int speed) {
        super(heading);

        this.setLocation(location);
        this.setColor(ColorUtil.rgb(0,0,0));
        super.setSpeed(speed);
    }

    public String toString() {

        String superToString = super.toString();
        String name = "MissileLauncher: ";

        return name + superToString;
    }
}
