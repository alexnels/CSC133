package com.mycompany.a1.GameObject;

import com.codename1.ui.geom.Point2D;
import com.codename1.charts.util.ColorUtil;

public abstract class GameObject {

    private int color;
    private Point2D location;

    //Getter for the color variable of a GameObject
    public int getColor(){
        return color;
    }

    //Setter for the color variable of a GameObject
    public void setColor(int color){
        this.color = color;
    }

    //Getter for the Location variable of a GameObject
    public Point2D getLocation(){
        return location;
    }

    //Setter for the Location variable of a GameObject
    public void setLocation(Point2D location){
        this.location = location;
    }

    //This is the highest 'parent' toString which contains information about
    //the objects location and color
    public String toString(){
        String location = "Loc:" + Math.round(this.getLocation().getX()) + "," + Math.round(this.getLocation().getY());
        String color    = " Color:" +  "["
                + ColorUtil.red(this.getColor()) + ","
                + ColorUtil.green(this.getColor()) + ","
                + ColorUtil.blue(this.getColor()) + "]";

        return location + color;
    }
}
