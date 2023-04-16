package com.example.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

/**
 * MainActivityPrime is the entry point to our game.
 */
public class MainActivityPrime extends Activity {
    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Set window to fullscreen
        Window window = getWindow();
        window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );

        // Set content view to game, so that objects in the Game class can be rendered to the screen
        game = new Game(this);
        setContentView(game);
    }
}
