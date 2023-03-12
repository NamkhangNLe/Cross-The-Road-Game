package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
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

        //Display score
        TextView scoreDisplay = findViewById(R.id.scoreDisplay);
        int score = mouse.getScore();
        scoreDisplay.setText(Integer.toString(score));

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
        createCar(0, "rolling_pin", true);
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
        if (mouse.getyPos() != 11) {
            float move = characterSprite.getHeight();
            characterSprite.setY(characterSprite.getY() - move);
            mouse.moveUp();
        }
        if (!mouse.getRowHasBeenTraveledOn()[mouse.getyPos()]) {
            // Set's score depending on which row has been crossed
            if (mouse.getyPos() == 1 || mouse.getyPos() == 3 || mouse.getyPos() == 5) {
                mouse.setScore(mouse.getScore() + 10);
            } else if (mouse.getyPos() == 2 || mouse.getyPos() == 4) {
                mouse.setScore(mouse.getScore() + 20);
            } else if (mouse.getyPos() >= 7 && mouse.getyPos() <= 10) {
                mouse.setScore(mouse.getScore() + 50);
            } else if (mouse.getyPos() == 11 && mouse.getxPos() % 2 == 1) {
            mouse.setScore(mouse.getScore() + 100);
            }
            mouse.setRowHasBeenTraveledOn(mouse.getRowHasBeenTraveledOn()[mouse.getyPos()] = true);
            TextView scoreDisplay = findViewById(R.id.scoreDisplay);
            scoreDisplay.setText(Integer.toString(mouse.getScore()));
        }
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

    public void createCar(int speed, String type, boolean direction) {
        Vehicle curCar = new Vehicle(speed, type, direction, this);
        ImageView iv = new ImageView(this);
        ViewGroup view = (ViewGroup) findViewById(R.id.road_one);
        if (type.equals("rolling_pin")) {
            iv.setImageResource(R.drawable.rolling_pin);
        }
        view.addView(iv);
    }
}
