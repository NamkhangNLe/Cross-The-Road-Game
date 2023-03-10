package com.example.myapplication;

public abstract class Vehicle {
    //Fields
    int speed;
    int size;
    String type;

    //Determines how fast and in which direction the vehicle moves.
    abstract void move();

    //Determines what happens when the player collides with the vehicle.
    abstract void collide();
}
