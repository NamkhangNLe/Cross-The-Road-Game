package com.example.myapplication;

import org.junit.Test;

import static org.junit.Assert.*;


public class Tests {
    //Sprint 2 Unit Tests

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

    //Sprint 3 Unit Tests

    /**
     * @author Ben Steele
     * Ensures the vehicles speed is properly set
     */
    @Test
    public void testVehicleSpeed() {
        Vehicle testVehicle = new Vehicle(5,"1",true);
        assertEquals(testVehicle.getSpeed(), 5);
    }
    /**
     * @author Ben Steele
     * Ensures that the inputted vehicle type matches the input value
     */
    @Test
    public void testVehicleType() {
        Vehicle testVehicle = new Vehicle(5,"1",true);
        assertEquals(testVehicle.getType(), "1");
    }

    /**
     * @author Patrick Kim
     * Ensures that the player's score is correct after moving up once.
     */
    @Test
    public void testScoreAtRowOne() {
        Player player = new Player(3, "Patrick");
        player.moveUp();
        assertEquals(10, player.getScore());
    }

    /**
     * @author Patrick Kim
     * Ensures that the player's score is correct on road 2.
     */
    @Test
    public void testScoreAtRowTwo() {
        Player player = new Player(3, "Patrick");
        player.moveUp();
        player.moveLeft();
        player.moveUp();
        assertEquals(30, player.getScore());
    }

    /**
     * @author Namkhang Le
     * checks to make sure that the players score starts at 0
     */
    @Test
    public void checkScore() {
        Player testPlayer = new Player(5,"Frank");
        assertEquals(0,testPlayer.getScore());
    }

    /**
     * @author Namkhang Le
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
        assertTrue(testVehicle.getDirection());
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

    /**
     * @author Keller Smith
     * Makes sure the player is no longer 'alive' if all lives lost.
     */
    @Test
    public void isDead() {
        Player testPlayer = new Player(3, "Bob");
        testPlayer.removeLife();
        testPlayer.removeLife();
        testPlayer.removeLife();
        assertFalse(testPlayer.getAlive());
    }

    /**
     * @author Keller Smith
     * Makes sure the first row is set to traveled on startup
     */
    @Test
    public void firstRowTraveledOnStart() {
        Player testPlayer = new Player(5, "Bob");
        assertTrue(testPlayer.getRowHasBeenTraveledOn()[0]);
    }

    /**
     * @author Amanda Janusz
     * Ensures that lateral movement on safe ground (specifically yPos 7) does not deduct lives.
     */
    @Test
    public void safeGroundIsSafe() {
        Player testPlayer = new Player();
        for (int i = 0; i <6; i++) {
            testPlayer.moveUp();
        }
        int currLives = testPlayer.getLives();
        testPlayer.moveRight();
        testPlayer.moveRight();
        testPlayer.moveLeft();
        assertEquals(currLives, testPlayer.getLives());
    }

    /**
     * @author Amanda Janusz
     * Verifies that score increases for all forward movement.
     */
    @Test
    public void forwardMovementIncreasesScore() {
        Player testPlayer = new Player();
        for (int i = 0; i < 11; i++) { // increase i-bound as map lengthens
            int initScore = testPlayer.getScore();
            testPlayer.moveUp();
            //Makes sure we are not on a safe space.
            if (i != 5) {
                assertTrue(initScore < testPlayer.getScore());
            }
        }
    }

    //Sprint 4 Unit Tests
    /**
     * @author Keller Smith
     * Verifies that the hit by car function makes you lose a life.
     */
    @Test
    public void testHitByCar() {
        Player testPlayer = new Player(5, "Bob");
        testPlayer.hitByCar();
        testPlayer.hitByCar();
        assertEquals(testPlayer.getLives(), 3);
    }

    /**
     * @author Keller Smith
     * Verifies that when hit by car, if lives == 0, there is a game over.
     */
    @Test
    public void testDeathByCar() {
        Player testPlayer = new Player(5, "Bob");
        for (int i = 0; i < 5; i++) {
            testPlayer.hitByCar();
        }
        assertFalse(testPlayer.getAlive());
    }

    /**
     * @author Patrick Kim
     * Checks that the player's X position is reset correctly.
     */
    @Test
    public void testXResetPosition() {
        Player testPlayer = new Player();
        int initialXPos = testPlayer.getxPos();

        testPlayer.moveUp();
        testPlayer.moveUp();

        testPlayer.resetPosition();

        assertEquals(initialXPos, testPlayer.getxPos());
    }

    /**
     * @author Patrick Kim
     * Checks that the player's Y position is reset correctly.
     */
    @Test
    public void testYResetPosition() {
        Player testPlayer = new Player();
        int initialYPos = testPlayer.getyPos();

        testPlayer.moveUp();
        testPlayer.moveUp();

        testPlayer.resetPosition();

        assertEquals(initialYPos, testPlayer.getyPos());
    }

    /**
     * @author Rushda Umrani
     * Checks if hitting water reduces lives by 1.
     */
    @Test
    public void waterRemoveLife() {
        Player testPlayer = new Player(5, "Bob");
        testPlayer.touchedWater();
        assertEquals(4, testPlayer.getLives());
    }

    /**
     * @author Rushda Umrani
     * Checks if score does not change after hitting a car.
     */
    @Test
    public void hitByCarNoScoreChange() {
        Player testPlayer = new Player();
        int initScore = testPlayer.getScore();
        testPlayer.hitByCar();
        assertEquals(initScore, testPlayer.getScore());
    }

    /**
     * @author Amanda Janusz
     * Verifies that hitting a car decrements lives.
     */
    @Test
    public void carDecrementsLives() {
        Player testPlayer = new Player(5, "Bob");
        for (int i = 0; i < 5; i++) {
            int currLives = testPlayer.getLives();
            testPlayer.hitByCar();
            assertTrue(testPlayer.getLives() < currLives);
        }
    }

    /**
     * @author Amanda Janusz
     * Checks that hitting water decrements lives.
     */

    @Test
    public void waterDecrementsLives() {
        Player testPlayer = new Player(5, "Bob");
        for (int i = 0; i < 5; i++) {
            int currLives = testPlayer.getLives();
            testPlayer.touchedWater();
            assertTrue(testPlayer.getLives() < currLives);
        }
    }

     /**
     * @author Ben Steele
     * Checks if score does not change after touching water.
     */
    @Test
    public void touchedWaterNoScoreChange() {
        Player testPlayer = new Player();
        int initScore = testPlayer.getScore();
        testPlayer.touchedWater();
        assertEquals(initScore, testPlayer.getScore());
    }

    /**
     * @author Ben Steele
     * Checks that the player's Y position is reset correctly.
     */
    @Test
    public void testResetPosition() {
        Player testPlayer = new Player();
        int initialYPos = testPlayer.getyPos();

        testPlayer.moveUp();
        testPlayer.moveUp();

        testPlayer.resetPosition();

        assertEquals(initialYPos, testPlayer.getyPos());
    }

    /**
     * @author Namkhang Le
     * Verifies that the hit by car function makes you lose your score.
     */
    @Test
    public void testLossOfScore() {
        Player testPlayer = new Player(5, "Bob");
        testPlayer.hitByCar();
        testPlayer.hitByCar();
        assertEquals(testPlayer.getScore(), 0);
    }

    /**
     * @author Namkhang Le
     * Makes sure no score change on reset position
     */
    @Test
    public void scoreResetPosition() {
        Player testPlayer = new Player();
        int initialYPos = testPlayer.getyPos();

        testPlayer.moveUp();
        testPlayer.moveUp();

        testPlayer.resetPosition();

        assertEquals(30, testPlayer.getScore());
    }

    // Unit Test for Sprint 5
    /**
     * @author Amanda Janusz
     * Checks that score is correct when player advances to row 3.
     */
    @Test
    public void testScoreAtRowThree() {
        Player player = new Player(3, "Remy");
        for (int i = 0; i < 3; i++) {
            player.moveUp();
        }
        assertEquals(40, player.getScore());
    }

    /**
     * @author Amanda Janusz
     * Checks that score is correct when player advances to row 4.
     */
    @Test
    public void testScoreAtRowFour() {
        Player player = new Player(3, "Remy");
        for (int i = 0; i < 4; i++) {
            player.moveUp();
        }
        assertEquals(60, player.getScore());
    }

    /**
     * @author Patrick Kim
     * Checks that score is correct when player advances to row 5.
     */
    @Test
    public void testScoreAtRowFive() {
        Player player = new Player(3, "Remy");
        for (int i = 0; i < 5; i++) {
            player.moveUp();
        }
        assertEquals(70, player.getScore());
    }

    /**
     * @author Patrick Kim
     * Checks that score is correct when player advances to row 6.
     */
    @Test
    public void testScoreAtRowSix() {
        Player player = new Player(3, "Remy");
        for (int i = 0; i < 6; i++) {
            player.moveUp();
        }
        assertEquals(70, player.getScore());
    }

    /**
     * @author Keller Smith
     * Checks that score is correct when player advances to row 5.
     */
    @Test
    public void testScoreAtRowSeven() {
        Player player = new Player(3, "Remy");
        for (int i = 0; i < 7; i++) {
            player.moveUp();
        }
        assertEquals(120, player.getScore());
    }

    /**
     * @author Keller Smith
     * Checks that score is correct when player advances to row 8.
     */
    @Test
    public void testScoreAtRowEight() {
        Player player = new Player(3, "Remy");
        for (int i = 0; i < 8; i++) {
            player.moveUp();
        }
        assertEquals(170, player.getScore());
    }

    /**
     * @author Rushda Umrani
     * Checks that score is correct when player advances to row 9.
     */
    @Test
    public void testScoreAtRowNine() {
        Player player = new Player(3, "Remy");
        for (int i = 0; i < 9; i++) {
            player.moveUp();
        }
        assertEquals(220, player.getScore());
    }

    /**
     * @author Rushda Umrani
     * Checks that score is correct when player advances to row 10.
     */
    @Test
    public void testScoreAtRowTen() {
        Player player = new Player(3, "Remy");
        for (int i = 0; i < 10; i++) {
            player.moveUp();
        }
        assertEquals(270, player.getScore());
    }

    /**
     * @author Ben Steele
     * Checks that score is correct when player advances to a win at row 11.
     */
    @Test
    public void testScoreAtRowElevenWin() {
        Player player = new Player(3, "Remy");
        for (int i = 0; i < 11; i++) {
            player.moveUp();
        }
        assertEquals(370, player.getScore());
    }

    /**
     * @author Ben Steele
     * Checks that score is correct when player advances to a safe spot on row 11.
     */
    @Test
    public void testScoreAtRowElevenSafe() {
        Player player = new Player(3, "Remy");
        for (int i = 0; i < 11; i++) {
            player.moveUp();
        }
        player.moveRight();
        assertEquals(270, player.getScore());
    }

    /**
     * @author Namkhang Le
     * Reaching the goal tile should give the greatest number of points test.
     */
    @Test
    public void maxScoreTest() {
        Player player = new Player(5, "Bob");
        player.moveUp();
        player.moveUp();
        player.moveUp();
        player.moveUp();
        player.moveUp();
        player.moveUp();
        player.moveUp();
        player.moveUp();
        player.moveUp();
        player.moveUp();
        player.moveUp();
        assertEquals(player.getScore(), 370);
    }
    /**
     * @author Namkhang Le
     * Touching water removes life.
     */
    @Test
    public void touchedWaterTest() {
        Player player = new Player(5, "Bob");
        player.touchedWater();
        assertEquals(player.getLives(), 4);
    }

    /**
     * @author Namkhang Le
     * Succesfully loss the game
     */
    @Test
    public void gameNotWonTest() {
        Player player = new Player(5, "Bob");
        player.checkGameWin();
        assertFalse(player.isGameWin());
    }
}
