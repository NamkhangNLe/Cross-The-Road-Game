package com.example.myapplication;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

public class MainActivity4 extends AppCompatActivity {
    private int lives;
    private Player mouse = new Player();
    private ImageView characterSprite;
    /**
     * on Create.
     * @param savedInstanceState savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        setUp();
    }

    /**
     * set Up.
     */
    private void setUp() {
        //Retrieve old variables
        Intent prev = getIntent();

        String name = prev.getStringExtra("name");
        String difficulty = prev.getStringExtra("difficulty");
        mouse.setName(name);
        GameInstance currentGame = new GameInstance(mouse, difficulty);
        if (difficulty.equals("Easy")) {
            mouse.setLives(5);
        } else if (difficulty.equals("Medium")) {
            mouse.setLives(3);
        } else {
            mouse.setLives(1);
        }
        String character = prev.getStringExtra("character");
        //Display names and lives
        TextView nameDisplay = findViewById(R.id.nameDisplay);
        TextView livesDisplay = findViewById(R.id.livesDisplay);
        TextView difficultyDisplay = findViewById(R.id.difficultyDisplay);
        nameDisplay.setText(name);
        livesDisplay.setText(Integer.toString(mouse.getLives()));
        difficultyDisplay.setText(currentGame.getDifficulty());
        //Display character
        characterSprite = findViewById(R.id.charSprite);
        getApplicationContext();
        if (character.equals("char1")) {
            characterSprite.setImageDrawable(ResourcesCompat.getDrawable(getResources(),
                    R.drawable.mouse, null));
        } else if (character.equals("char2")) {
            characterSprite.setImageDrawable(ResourcesCompat.getDrawable(getResources(),
                    R.drawable.rat, null));
        } else {
            characterSprite.setImageDrawable(ResourcesCompat.getDrawable(getResources(),
                    R.drawable.remy, null));
        }
    }

    public void moveUp(View view) {
        //Rotate the mouse.
        characterSprite.setRotation(0);
        if (mouse.getyPos() == 6
                || mouse.getyPos() == 7
                || mouse.getyPos() == 8
                || mouse.getyPos() == 9) {
            mouse.removeLife();
            TextView livesDisplay = findViewById(R.id.livesDisplay);
            livesDisplay.setText(Integer.toString(mouse.getLives()));
        }
        if (mouse.getyPos() == 11) {
            return;
        }
        float move = characterSprite.getHeight();
        characterSprite.setY(characterSprite.getY() - move);
        mouse.moveUp();
    }

    public void moveDown(View view) {
        //Rotate the mouse.
        characterSprite.setRotation(180);

        if (mouse.getyPos() == 0) {
            return;
        }
        float move = characterSprite.getHeight();
        characterSprite.setY(characterSprite.getY() + move);
        mouse.moveDown();
    }
    public void moveLeft(View view) {
        //Rotate the mouse.
        characterSprite.setRotation(-90);

        if (mouse.getxPos() == 0) {
            return;
        }
        float move = characterSprite.getHeight();
        characterSprite.setX(characterSprite.getX() - move);
        mouse.moveLeft();
    }
    public void moveRight(View view) {
        //Rotate the mouse.
        characterSprite.setRotation(90);

        float move = characterSprite.getHeight();
        if (mouse.getxPos() == 9) {
            return;
        }
        characterSprite.setX((characterSprite.getX() + move));
        mouse.moveRight();
    }
}
