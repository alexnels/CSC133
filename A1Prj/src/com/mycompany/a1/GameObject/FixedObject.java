package com.mycompany.a1.GameObject;

public abstract class FixedObject extends GameObject {

    private static int id;

    public FixedObject(int id) {
        this.setId(id);
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        FixedObject.id = id;
    }

    public String toString(){
        String superToString = super.toString();
        String id = " ID:" + this.id;
        return superToString + id;
    }
}
