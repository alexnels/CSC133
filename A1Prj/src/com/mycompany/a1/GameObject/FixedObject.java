package com.mycompany.a1.GameObject;

public abstract class FixedObject extends GameObject {

    //each fixed object needs to have its own id
    private static int id;

    //the constructor for a FixedObject sets an id variable
    public FixedObject(int id) {
        this.setId(id);
    }

    //Getter for the id variable of a FixedObject
    public static int getId() {
        return id;
    }

    //Setter for the id variable of a FixedObject
    public static void setId(int id) {
        FixedObject.id = id;
    }

    //This toString() calls its parent toString as well as adds ID information
    public String toString(){
        String superToString = super.toString();
        String id = " ID:" + this.id;
        return superToString + id;
    }
}
