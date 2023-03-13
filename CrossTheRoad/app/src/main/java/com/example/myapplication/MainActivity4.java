package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Handler;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import java.util.TimerTask;

public class MainActivity4 extends AppCompatActivity {
    private int lives;
    private boolean gameOver = false;
    private Handler handler;
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
        runVehicles();
    }

    public void moveUp(View view) {
        //Rotate the mouse.
        characterSprite.setRotation(0);
        if (mouse.getyPos() == 6
                || mouse.getyPos() == 7
                || mouse.getyPos() == 8
                || mouse.getyPos() == 9) {
            mouse.removeLife();
            if (mouse.getLives() < 0) {
                mouse.setAlive(false);
                gameOver();
            }
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

    public void gameOver() {
        gameOver = true;

    }


    public void runVehicles() {
        handler = new Handler();
        roadOne.run();
        roadTwo.run();
        roadThree.run();
        roadFour.run();
        roadFive.run();
    }
    Runnable roadOne = new Runnable() {
        @Override
        public void run() {
            ImageView iv = new ImageView(getApplicationContext());
            ViewGroup view;
            ObjectAnimator animation;
            view = (ViewGroup) findViewById(R.id.road_one);
            iv.setImageResource(R.drawable.mousetrap_obstacle);
            view.addView(iv);
            animation = ObjectAnimator.ofFloat(iv, "translationX", 2000f);
            animation.setDuration(5000);
            animation.start();
            handler.postDelayed(roadOne, 2000);
        }
    };
    Runnable roadTwo = new Runnable() {
        @Override
        public void run() {
            ImageView iv = new ImageView(getApplicationContext());
            ViewGroup view;
            ObjectAnimator animation;
            view = (ViewGroup) findViewById(R.id.road_two);
            iv.setImageResource(R.drawable.knife_obstacle);
            view.addView(iv);
            animation = ObjectAnimator.ofFloat(iv, "translationX", 2000f);
            animation.setDuration(10000);
            animation.start();
            handler.postDelayed(roadTwo, 8000);
        }
    };
    Runnable roadThree = new Runnable() {
        @Override
        public void run() {
            ImageView iv = new ImageView(getApplicationContext());
            ViewGroup view;
            ObjectAnimator animation;
            view = (ViewGroup) findViewById(R.id.road_three);
            iv.setImageResource(R.drawable.rollingpin_obstacle);
            view.addView(iv);
            animation = ObjectAnimator.ofFloat(iv, "translationX", 2000f);
            animation.setDuration(1000);
            animation.start();
            handler.postDelayed(roadThree, 2000);
        }
    };
    Runnable roadFour = new Runnable() {
        @Override
        public void run() {
            ImageView iv = new ImageView(getApplicationContext());
            ViewGroup view;
            ObjectAnimator animation;
            view = (ViewGroup) findViewById(R.id.road_four);
            iv.setImageResource(R.drawable.mousetrap_obstacle);
            view.addView(iv);
            animation = ObjectAnimator.ofFloat(iv, "translationX", 2000f);
            animation.setDuration(6000);
            animation.start();
            handler.postDelayed(roadFour, 3000);
        }
    };
    Runnable roadFive = new Runnable() {
        @Override
        public void run() {
            ImageView iv = new ImageView(getApplicationContext());
            ViewGroup view;
            ObjectAnimator animation;
            view = (ViewGroup) findViewById(R.id.road_five);
            iv.setImageResource(R.drawable.rollingpin_obstacle);
            view.addView(iv);
            animation = ObjectAnimator.ofFloat(iv, "translationX", 2000f);
            animation.setDuration(8000);
            animation.start();
            handler.postDelayed(roadFive, 4000);
        }
    };
}
