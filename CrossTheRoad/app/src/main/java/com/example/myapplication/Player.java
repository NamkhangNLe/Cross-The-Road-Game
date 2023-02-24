package com.example.myapplication;

public class Player {
    private int xPos;
    private int yPos;

    public Player() {
        xPos = 5;
        yPos = 0;
    }

    public int getyPos() {
        return yPos;
    }

    public int getxPos() {
        return xPos;
    }

    public void setxPos(int newx) {
        xPos = newx;
    }
    public void setyPos(int newy) {
        yPos = newy;
    }
}
