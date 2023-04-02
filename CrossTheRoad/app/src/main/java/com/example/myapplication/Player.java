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

        this.updateScore();
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

    public void removeLife() {
        lives--;
        if (lives == 0) {
            alive = false;
        }
    }

    public void resetPosition() {
        this.xPos = 5;
        this.yPos = 0;
    }

    public void resetScoreIncrement() {
        for (int i = 1; i < 12; i++) {
            rowHasBeenTraveledOn[i] = false;
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

    //Updates the player's score depending on what row they are on.
    private void updateScore() {
        if (!this.getRowHasBeenTraveledOn()[this.getyPos()]) {
            // Sets score depending on which row has been crossed
            if (this.getyPos() == 1 || this.getyPos() == 3 || this.getyPos() == 5) {
                this.setScore(this.getScore() + 10);
            } else if (this.getyPos() == 2 || this.getyPos() == 4) {
                this.setScore(this.getScore() + 20);
            } else if (this.getyPos() >= 7 && this.getyPos() <= 10) {
                this.setScore(this.getScore() + 50);
            } else if (this.getyPos() == 11 && this.getxPos() % 2 == 1) {
                this.setScore(this.getScore() + 100);
            }
            this.setRowHasBeenTraveledOn(yPos, true);
        }
    }
    public void hitByCar() {
        lives--;
        if (lives == 0) {
            alive = false;
        }
    }

    public void setRowHasBeenTraveledOn(int row, boolean hasBeenTraveledOn) {
        this.rowHasBeenTraveledOn[row] = hasBeenTraveledOn;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }
    public boolean getAlive() {
        return this.alive;
    }

}
