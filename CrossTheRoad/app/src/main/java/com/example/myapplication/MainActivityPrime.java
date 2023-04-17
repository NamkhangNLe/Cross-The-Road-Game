package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;


/**
 * MainActivityPrime is the entry point to our game.
 */
public class MainActivityPrime extends AppCompatActivity {
    private Game game;
    private Player mouse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        //setContentView(R.layout.activity_main_prime);

        //Set window to fullscreen
        Window window = getWindow();
        window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );

        // Set content view to game, so that objects in the Game class can be rendered to the screen
        mouse = createPlayer();
        game = new Game(this, mouse);
        setContentView(game);
    }

    /**
     * Set up
     */
    private Player createPlayer() {
        // Retrieve old variables
        Intent prev = getIntent();
        String name = prev.getStringExtra("name");
        String difficulty = prev.getStringExtra("difficulty");
        String character = prev.getStringExtra("character");
        int lives = 0;
        ImageView sprite = null;
        
        // Set the lives
        if (difficulty.equals("Easy")) {
            lives = 5;
        } else if (difficulty.equals("Medium")) {
            lives = 3;
        } else {
            lives = 1;
        }
        
        // Set the sprite
        if (character.equals("char1")) {
            sprite.setImageDrawable(ResourcesCompat.getDrawable(getResources(),
                    R.drawable.mouse, null));
        } else if (character.equals("char2")) {
            sprite.setImageDrawable(ResourcesCompat.getDrawable(getResources(),
                    R.drawable.rat, null));
        } else {
            sprite.setImageDrawable(ResourcesCompat.getDrawable(getResources(),
                    R.drawable.remy, null));
        }

        Player player = new Player(lives, name, sprite);
        return player;
    }
}