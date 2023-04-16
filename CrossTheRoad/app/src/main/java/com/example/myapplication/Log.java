package com.example.myapplication;

public class Log {
    private int xPos;
    private int yPos;
    private int speed;

    public Log(int xPos, int yPos, int speed) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.speed = speed;
    }

    public int getXPos() {
        return xPos;
    }

    public void setXPos(int xPos) {
        this.xPos = xPos;
    }

    public int getYPos() {
        return yPos;
    }

    public void setYPos(int yPos) {
        this.yPos = yPos;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}