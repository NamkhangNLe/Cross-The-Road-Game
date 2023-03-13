package com.example.myapplication;

import org.junit.Test;

import static org.junit.Assert.*;


public class Tests {
    /**
     * @author Ben Steele
     * Tests that lives can be lost in the game in the player class.
     */
    @Test
    public void testLifeLost() {
        Player testPlayer = new Player(5, "Remy");
        int currentLives = testPlayer.getLives();
        testPlayer.removeLife();
        assertEquals(currentLives - 1, testPlayer.getLives());
    }

    /**
     * @author Ben Steele
     * Ensures that when a player is instantiated with a name that it matches the expected string.
     */
    @Test
    public void nameProperlySet() {
        Player testPlayer = new Player(5, "Remy");
        String trueName = "Remy";
        assertEquals(trueName, testPlayer.getName());
    }

    /**
     * @author Patrick Kim
     * Ensures that when the difficulty is selected to be easy that it represents our selected integer
     * that represents easy.
     */
    @Test
    public void difficultySetProperlyEasy() {
        Player testPlayer = new Player();
        GameInstance testInstance = new GameInstance(testPlayer, "Easy");
        assertEquals(testInstance.getDifficultyNum(), 1);
    }

    /**
     * @author Patrick Kim
     * Ensures that when the difficulty is selected to be medium that it represents our selected integer
     * that represents medium.
     */
    @Test
    public void difficultySetProperlyMedium() {
        Player testPlayer = new Player();
        GameInstance testInstance = new GameInstance(testPlayer, "Medium");
        assertEquals(testInstance.getDifficultyNum(), 2);
    }

    /**
     * @author Keller Smith
     * Ensures that the player cannot move off the screen to the right.
     */
    @Test
    public void cannotExitScreenRight() {
        Player testPlayer = new Player();
        for (int i = 0; i < 20; i++) {
            testPlayer.moveRight();
        }
        assertEquals(testPlayer.getxPos(), 9);
    }
    /**
     * @author Keller Smith
     * Ensures that the player can move left and right.
     */
    @Test
    public  void moveLeftRight() {
        Player testPlayer = new Player();
        testPlayer.moveRight();
        testPlayer.moveRight();
        testPlayer.moveRight();
        testPlayer.moveRight();
        testPlayer.moveLeft();
        testPlayer.moveLeft();
        testPlayer.moveLeft();
        assertEquals(testPlayer.getxPos(), 6);
    }

    /**
     * @author Namkhang Le
     * Ensures that when the difficulty is selected to be hard that it represents our selected integer
     * that represents hard.
     */
    @Test
    public void difficultySetProperlyHard() {
        Player testPlayer = new Player();
        GameInstance testInstance = new GameInstance(testPlayer, "Hard");
        assertEquals(testInstance.getDifficultyNum(), 3);
    }

    /**
     * @author Namkhang Le
     * Ensures that the player cannot move off the screen upward.
     */
    @Test
    public void cannotExitScreenUp() {
        Player testPlayer = new Player();
        for (int i = 0; i < 20; i++) {
            testPlayer.moveUp();
        }
        assertEquals(testPlayer.getyPos(), 11);
    }

    /**
     * @author Rushda Umrani
     * Ensures that the player can move up and down.
     */
    @Test
    public void testUpAndDown() {
        Player testPlayer = new Player();
        testPlayer.moveUp();
        testPlayer.moveUp();
        testPlayer.moveUp();
        testPlayer.moveDown();
        testPlayer.moveDown();
        testPlayer.moveUp();
        testPlayer.moveUp();
        testPlayer.moveDown();
        testPlayer.moveDown();
        testPlayer.moveDown();
        assertEquals(testPlayer.getyPos(), 0);
    }

    /**
     * @author Rushda Umrani
     * Ensures that player cannot move off the screen downward.
     */
    @Test
    public void cannotExitScreenDown(){
        Player testPlayer = new Player();
        for (int i = 0; i < 20; i++) {
            testPlayer.moveDown();
        }
        assertEquals(testPlayer.getyPos(), 0);
    }

    /**
     * @author Amanda Janusz
     * Checks that the player can move up, down, left, and right.
     */
    @Test
    public void moveKonami() {
        Player testPlayer = new Player();
        testPlayer.moveUp();
        testPlayer.moveDown();
        testPlayer.moveUp();
        testPlayer.moveDown();
        testPlayer.moveLeft();
        testPlayer.moveRight();
        testPlayer.moveLeft();
        testPlayer.moveRight();
        testPlayer.moveUp();
        testPlayer.moveUp();

        assertEquals(testPlayer.getyPos(), 2);
    }
  
     /**
     * @author Amanda Janusz
     * Checks that the player cannot move off the screen to the left.
     */
    @Test
    public void cannotExitScreenLeft() {
        Player testPlayer = new Player();
        for (int i = 0; i < 20; i++) {
            testPlayer.moveLeft();
        }
        assertEquals(testPlayer.getxPos(), 0);
    }

    /**
     * @author Ben Steele
     * Ensures the vehicles speed is properly set
     */
    @Test
    public void testVehicleSpeed() {
        Vehicle testVehicle = new Vehicle(5,"1",true);
        assertEquals(testVehicle.speed, 5);
    }
    /**
     * @author Ben Steele
     * Ensures that the inputted vehicle type matches the input value
     */
    @Test
    public void testVehicleType() {
        Vehicle testVehicle = new Vehicle(5,"1",true);
        assertEquals(testVehicle.type, "1");
    }

    /**
     * @author
     * checks to make sure that the players score starts at 0
     */
    @Test
    public void checkScore() {
        Player testPlayer = new Player(5,"Frank");
        assertEquals(0,testPlayer.getScore());
    }

    /**
     * @author
     * Checks that the starting point is actually where the mouse starts at in the
     * middle of the bottom of the screen
     */
    @Test
    public void startingPoint() {
        Player testPlayer = new Player();
        assertEquals(testPlayer.getxPos(), 5);
        assertEquals(testPlayer.getyPos(), 0);
    }

    /**
     * @author Rushda Umrani
     * Checks that the vehicle direction is properly set
     */
    @Test
    public void testVehicleDirection() {
        Vehicle testVehicle = new Vehicle(5,"1",true);
        assertTrue(testVehicle.direction);
    }

    /**
     * @author Rushda Umrani
     * checks to make sure that the player is set to "alive" at the beginning of the game
     */
    @Test
    public void isAlive() {
        Player testPlayer = new Player(5,"Frank");
        assertTrue(testPlayer.getAlive());
    }


}
