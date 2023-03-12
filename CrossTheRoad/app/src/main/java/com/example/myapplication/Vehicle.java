package com.example.myapplication;

import android.content.Context;
import android.media.Image;
import android.view.ViewGroup;
import android.widget.ImageView;

public class Vehicle{
    //Fields
    int speed;
    String type;
    boolean direction;
    ImageView sprite;
    int id;

    public Vehicle(int speed, String type, boolean direction, Context mcontext) {
        this.speed = speed;
        this.type = type;
        this.direction = direction;
    }

    //Determines how fast and in which direction the vehicle moves.
    public void move() {

    }

    //Determines what happens when the player collides with the vehicle.
    public void collide() {

    }
}
