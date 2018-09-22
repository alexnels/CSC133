package com.mycompany.a1.GameObject.MoveableObjects;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.geom.Point2D;
import com.mycompany.a1.GameObject.MoveableObject;

public class MissileLauncher extends MoveableObject {

    //A MissileLauncher is constructed with a location, heading, and speed, as well as a color.
    public MissileLauncher(Point2D location, int heading, int speed) {
        super(heading);

        this.setLocation(location);
        this.setColor(ColorUtil.rgb(0,0,0));
        super.setSpeed(speed);
    }

    //the toString method adds the missile launcher name and calls its parent toString()
    public String toString() {

        String superToString = super.toString();
        String name = "MissileLauncher: ";

        return name + superToString;
    }
}
