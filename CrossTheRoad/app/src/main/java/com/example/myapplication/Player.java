package com.example.myapplication;

public class Player {
    private String name;
    private int lives;
    private int xPos;
    private int yPos;

    public Player(int lives, String name) {
        xPos = 5;
        yPos = 0;
    }

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
    }

}
