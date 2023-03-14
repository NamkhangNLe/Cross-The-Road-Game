package com.example.myapplication;

public class Player {
    private String name;
    private int lives;
    private int xPos;
    private int yPos;
    private int score;
    private boolean[] rowHasBeenTraveledOn = new boolean[12];
    private boolean alive;


    public Player(int lives, String name) {
        this.lives = lives;
        this.name = name;
        this.score = 0;
        xPos = 5;
        yPos = 0;
        rowHasBeenTraveledOn[0] = true;
        this.alive = true;
    }

    public Player() {
        xPos = 5;
        yPos = 0;
        rowHasBeenTraveledOn[0] = true;
    }
    public void moveUp() {
        if (yPos != 11) {
            yPos = yPos + 1;
        }
    }
    public void moveDown() {
        if (yPos != 0) {
            yPos = yPos - 1;
        }
    }
    public void moveLeft() {
        if (xPos != 0) {
            xPos = xPos - 1;
        }
    }
    public void moveRight() {
        if (xPos != 9) {
            xPos = xPos + 1;
        }
    }
    public int getyPos() {
        return yPos;
    }

    public int getxPos() {
        return xPos;
    }

    public void setxPos(int newX) {
        xPos = newX;
    }
    public void setyPos(int newY) {
        yPos = newY;
    }

    public int getLives() {
        return lives;
    }
    public void setLives(int newLives) {
        this.lives = newLives;
    }
    public String getName() {
        return name;
    }
    public void setName(String newName) {
        this.name = newName;
    }

    public void removeLife(){
        lives--;
        if (lives == 0) {
            alive = false;
        }
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean[] getRowHasBeenTraveledOn() {
        return rowHasBeenTraveledOn;
    }

    public void setRowHasBeenTraveledOn(boolean hasBeenTraveledOn) {
        this.rowHasBeenTraveledOn = rowHasBeenTraveledOn;
    }

    public void setAlive(boolean alive) { this.alive = alive; }
    public boolean getAlive() { return this.alive; }

}
