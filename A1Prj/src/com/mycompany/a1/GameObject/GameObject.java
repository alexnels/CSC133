package com.mycompany.a1.GameObject;

import com.codename1.ui.geom.Point2D;
import com.codename1.charts.util.ColorUtil;

public abstract class GameObject {

    private int color;
    private Point2D location;

    public int getColor(){
        return color;
    }

    public void setColor(int color){
        this.color = color;
    }

    public Point2D getLocation(){
        return location;
    }

    public void setLocation(Point2D location){
        this.location = location;
    }

    public String toString(){
        String location = "Loc:" + Math.round(this.getLocation().getX()) + "," + Math.round(this.getLocation().getY());
        String color    = " Color:" +  "["
                + ColorUtil.red(this.getColor()) + ","
                + ColorUtil.green(this.getColor()) + ","
                + ColorUtil.blue(this.getColor()) + "]";

        return location + color;
    }
}
