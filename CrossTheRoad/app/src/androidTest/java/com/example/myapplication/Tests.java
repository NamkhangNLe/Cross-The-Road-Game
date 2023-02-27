package com.example.myapplication;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

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
}
