package com.example.myapplication;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.animation.ObjectAnimator;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

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

        TextView scoreDisplay = findViewById(R.id.scoreDisplay);
        scoreDisplay.setText(Integer.toString(mouse.getScore()));
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
    private Runnable roadOne = new Runnable() {
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

            //Detect collision between the mouse and the vehicle.
            Rect rc1 = new Rect();
            characterSprite.getDrawingRect(rc1);
            Rect rc2 = new Rect();
            view.getDrawingRect(rc2);

            //Detects collision and does subsequent actions.
            if (Rect.intersects(rc1, rc2) && mouse.getyPos() == 1) {
                //Remove a life.
                mouse.removeLife();
                TextView livesDisplay = findViewById(R.id.livesDisplay);
                livesDisplay.setText(Integer.toString(mouse.getLives()));

                //Reset the mouse to the start.
                if (mouse.getLives() > 0) {
                    float xMove = characterSprite.getWidth();
                    characterSprite.setX(xMove * 5);
                    float yMove = characterSprite.getHeight();
                    characterSprite.setY(characterSprite.getY() + yMove * mouse.getyPos());

                    //Reset the mouse's position.
                    mouse.resetPosition();
                    characterSprite.setRotation(0);

                    //Reset the score to 0.
                    mouse.setScore(0);
                    TextView scoreDisplay = findViewById(R.id.scoreDisplay);
                    int score = mouse.getScore();
                    scoreDisplay.setText(Integer.toString(score));
                    mouse.resetScoreIncrement();
                } else {
                    //Go to GameOver Screen.
                    //This is a placeholder method for now that simply goes back to the previous screen.
                    handler.removeCallbacks(roadOne);
                    finish();
                }
            }
        }
    };
    private Runnable roadTwo = new Runnable() {
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

            Rect rc1 = new Rect();
            characterSprite.getDrawingRect(rc1);
            Rect rc2 = new Rect();
            view.getDrawingRect(rc2);
            if (Rect.intersects(rc1, rc2) && mouse.getyPos() == 2) {
                mouse.removeLife();
                TextView livesDisplay = findViewById(R.id.livesDisplay);
                livesDisplay.setText(Integer.toString(mouse.getLives()));

                //Reset the mouse to the start.
                if (mouse.getLives() > 0) {
                    float xMove = characterSprite.getWidth();
                    characterSprite.setX(xMove * 5);
                    float yMove = characterSprite.getHeight();
                    characterSprite.setY(characterSprite.getY() + yMove * mouse.getyPos());

                    //Reset the mouse's position.
                    mouse.resetPosition();
                    characterSprite.setRotation(0);

                    //Reset the score to 0.
                    mouse.setScore(0);
                    TextView scoreDisplay = findViewById(R.id.scoreDisplay);
                    int score = mouse.getScore();
                    scoreDisplay.setText(Integer.toString(score));
                    mouse.resetScoreIncrement();
                } else {
                    //Go to GameOver Screen.
                    //This is a placeholder method for now that simply goes back to the previous screen.
                    handler.removeCallbacks(roadTwo);
                    finish();
                }
            }
        }
    };
    private Runnable roadThree = new Runnable() {
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

            Rect rc1 = new Rect();
            characterSprite.getDrawingRect(rc1);
            Rect rc2 = new Rect();
            view.getDrawingRect(rc2);
            if (Rect.intersects(rc1, rc2) && mouse.getyPos() == 3) {
                mouse.removeLife();
                TextView livesDisplay = findViewById(R.id.livesDisplay);
                livesDisplay.setText(Integer.toString(mouse.getLives()));

                //Reset the mouse to the start.
                if (mouse.getLives() > 0) {
                    float xMove = characterSprite.getWidth();
                    characterSprite.setX(xMove * 5);
                    float yMove = characterSprite.getHeight();
                    characterSprite.setY(characterSprite.getY() + yMove * mouse.getyPos());

                    //Reset the mouse's position.
                    mouse.resetPosition();
                    characterSprite.setRotation(0);

                    //Reset the score to 0.
                    mouse.setScore(0);
                    TextView scoreDisplay = findViewById(R.id.scoreDisplay);
                    int score = mouse.getScore();
                    scoreDisplay.setText(Integer.toString(score));
                    mouse.resetScoreIncrement();
                } else {
                    //Go to GameOver Screen.
                    //This is a placeholder method for now that simply goes back to the previous screen.
                    handler.removeCallbacks(roadThree);
                    finish();
                }
            }
        }
    };
    private Runnable roadFour = new Runnable() {
        @Override
        public void run() {
            ImageView iv = new ImageView(getApplicationContext());
            ViewGroup view;
            ObjectAnimator animation;
            view = (ViewGroup) findViewById(R.id.road_four);
            iv.setImageResource(R.drawable.mousetrap_obstacle);
            view.addView(iv);
            animation = ObjectAnimator.ofFloat(iv, "translationX", 2000f, 1);
            animation.setDuration(6000);
            animation.start();
            handler.postDelayed(roadFour, 3000);

            Rect rc1 = new Rect();
            characterSprite.getDrawingRect(rc1);
            Rect rc2 = new Rect();
            view.getDrawingRect(rc2);
            if (Rect.intersects(rc1, rc2) && mouse.getyPos() == 4) {
                mouse.removeLife();
                TextView livesDisplay = findViewById(R.id.livesDisplay);
                livesDisplay.setText(Integer.toString(mouse.getLives()));

                //Reset the mouse to the start.
                if (mouse.getLives() > 0) {
                    float xMove = characterSprite.getWidth();
                    characterSprite.setX(xMove * 5);
                    float yMove = characterSprite.getHeight();
                    characterSprite.setY(characterSprite.getY() + yMove * mouse.getyPos());

                    //Reset the mouse's position.
                    mouse.resetPosition();
                    characterSprite.setRotation(0);

                    //Reset the score to 0.
                    mouse.setScore(0);
                    TextView scoreDisplay = findViewById(R.id.scoreDisplay);
                    int score = mouse.getScore();
                    scoreDisplay.setText(Integer.toString(score));
                    mouse.resetScoreIncrement();
                } else {
                    //Go to GameOver Screen.
                    //This is a placeholder method for now that simply goes back to the previous screen.
                    handler.removeCallbacks(roadFour);
                    finish();
                }
            }
        }
    };
    private Runnable roadFive = new Runnable() {
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

            Rect rc1 = new Rect();
            characterSprite.getDrawingRect(rc1);
            Rect rc2 = new Rect();
            view.getDrawingRect(rc2);
            if (Rect.intersects(rc1, rc2) && mouse.getyPos() == 5) {
                mouse.removeLife();
                TextView livesDisplay = findViewById(R.id.livesDisplay);
                livesDisplay.setText(Integer.toString(mouse.getLives()));

                //Reset the mouse to the start.
                if (mouse.getLives() > 0) {
                    float xMove = characterSprite.getWidth();
                    characterSprite.setX(xMove * 5);
                    float yMove = characterSprite.getHeight();
                    characterSprite.setY(characterSprite.getY() + yMove * mouse.getyPos());

                    //Reset the mouse's position.
                    mouse.resetPosition();
                    characterSprite.setRotation(0);

                    //Reset the score to 0.
                    mouse.setScore(0);
                    TextView scoreDisplay = findViewById(R.id.scoreDisplay);
                    int score = mouse.getScore();
                    scoreDisplay.setText(Integer.toString(score));
                    mouse.resetScoreIncrement();
                } else {
                    //Go to GameOver Screen.
                    //This is a placeholder method for now that simply goes back to the previous screen.
                    handler.removeCallbacks(roadFive);
                    finish();
                }
            }
        }
    };
    public void finish() {
        Intent intent = new Intent(this, MainActivity5.class);
        intent.putExtra("score", Integer.toString(mouse.getScore()));
        startActivity(intent);
    }
}
