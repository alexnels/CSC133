package com.mycompany.a1.GameObject.MoveableObjects;

//The ISteerable interface is implemented by objects who are steerable
public interface ISteerable {

    //Classes who implement this interface must create their own changeHeading functions
    public void changeHeading(int degreeChange);
}
