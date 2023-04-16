package com.example.myapplication;
import android.widget.ImageView;

public class Vehicle {
    //Fields
    private int speed;
    private String type;
    private boolean direction;
    private ImageView sprite;
    private int id;
    private int xPos;
    private int yPos;

    public Vehicle(int speed, int xPos, int yPos) {
        this.speed = speed;
        this.xPos = xPos;
        this.yPos = yPos;
    }

    //Determines how fast and in which direction the vehicle moves.
    public void move() {


    }

    //Determines what happens when the player collides with the vehicle.
    public void collide() {

    }

    public int getSpeed() {
        return speed;
    }

    public String getType() {
        return type;
    }

    public boolean getDirection() {
        return direction;
    }

    public ImageView getSprite() {
        return sprite;
    }

    public int getId() {
        return id;
    }
}
