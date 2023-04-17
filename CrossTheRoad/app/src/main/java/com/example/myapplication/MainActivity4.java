package com.example.myapplication;

//import android.animation.ValueAnimator;
//import android.app.Activity;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
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
        characterSprite.setRotation(0);
        if (mouse.getyPos() != 11) {
            float move = characterSprite.getHeight();
            characterSprite.setY(characterSprite.getY() - move);
            mouse.moveUp();
        }
        if (mouse.getyPos() > 6 && mouse.getRiding() == false) {
            mouse.touchedWater();
            TextView livesDisplay = findViewById(R.id.livesDisplay);
            livesDisplay.setText(Integer.toString(mouse.getLives()));
            if (mouse.getLives() > 0) {
                float xMove = characterSprite.getWidth();
                characterSprite.setX(xMove * 5);
                float yMove = characterSprite.getHeight();
                characterSprite.setY(characterSprite.getY() + yMove * mouse.getyPos());
                mouse.resetPosition();
                characterSprite.setRotation(0);
                mouse.setScore(0);
                TextView scoreDisplay = findViewById(R.id.scoreDisplay);
                int score = mouse.getScore();
                scoreDisplay.setText(Integer.toString(score));
                mouse.resetScoreIncrement();
                return;
            } else {
                finish();
            }
        }
        TextView scoreDisplay = findViewById(R.id.scoreDisplay);
        scoreDisplay.setText(Integer.toString(mouse.getScore()));
    }

    public void moveDown(View view) {
        //Rotate the mouse.
        characterSprite.setRotation(180);

        if (mouse.getyPos() > 5 && mouse.getRiding() == false) {
            mouse.touchedWater();
            if (mouse.getLives() == 0) {
                mouse.setAlive(false);
                gameOver();
                finish();
            }
            TextView livesDisplay = findViewById(R.id.livesDisplay);
            livesDisplay.setText(Integer.toString(mouse.getLives()));
        }
        if (mouse.getyPos() != 0) {
            float move = characterSprite.getHeight();
            characterSprite.setY(characterSprite.getY() + move);
            mouse.moveDown();
        }

        TextView scoreDisplay = findViewById(R.id.scoreDisplay);
        scoreDisplay.setText(Integer.toString(mouse.getScore()));
    }
    public void moveLeft(View view) {
        //Rotate the mouse.
        characterSprite.setRotation(-90);

        if (mouse.getyPos() > 6 && mouse.getRiding() == false) {
            mouse.removeLife();
            if (mouse.getLives() == 0) {
                mouse.setAlive(false);
                gameOver();
                finish();
            }
            TextView livesDisplay = findViewById(R.id.livesDisplay);
            livesDisplay.setText(Integer.toString(mouse.getLives()));
        }
        if (mouse.getxPos() != 0) {
            float move = characterSprite.getHeight();
            characterSprite.setX(characterSprite.getX() - move);
            mouse.moveLeft();
        }

        TextView scoreDisplay = findViewById(R.id.scoreDisplay);
        scoreDisplay.setText(Integer.toString(mouse.getScore()));
    }
    public void moveRight(View view) {
        //Rotate the mouse.
        characterSprite.setRotation(90);

        if (mouse.getyPos() > 6 && mouse.getRiding() == false) {
            mouse.removeLife();
            if (mouse.getLives() == 0) {
                mouse.setAlive(false);
                gameOver();
                finish();
            }
            TextView livesDisplay = findViewById(R.id.livesDisplay);
            livesDisplay.setText(Integer.toString(mouse.getLives()));
        }
        if (mouse.getxPos() != 9) {
            float move = characterSprite.getHeight();
            characterSprite.setX((characterSprite.getX() + move));
            mouse.moveRight();
        }

        TextView scoreDisplay = findViewById(R.id.scoreDisplay);
        scoreDisplay.setText(Integer.toString(mouse.getScore()));
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
        riverOne.run();
        riverTwo.run();
        riverThree.run();
        riverFour.run();

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
                mouse.hitByCar();
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
                    //This is a placeholder method for now that simply goes back to the previous
                    // screen.
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
            animation.setDuration(5000);
            animation.start();
            handler.postDelayed(roadTwo, 5000);

            //Detect collision between the mouse and the vehicle.
            Rect rc1 = new Rect();
            characterSprite.getDrawingRect(rc1);
            Rect rc2 = new Rect();
            view.getDrawingRect(rc2);

            //Detects collision and does subsequent actions.
            if (Rect.intersects(rc1, rc2) && mouse.getyPos() == 2) {
                //Remove a life.
                mouse.hitByCar();
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
                    //This is a placeholder method for now that simply goes back to the previous
                    // screen.
                    handler.removeCallbacks(roadOne);
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
            animation.setDuration(10000);
            animation.start();
            handler.postDelayed(roadThree, 2000);

            Rect rc1 = new Rect();
            characterSprite.getDrawingRect(rc1);
            Rect rc2 = new Rect();
            view.getDrawingRect(rc2);
            if (Rect.intersects(rc1, rc2) && mouse.getyPos() == 3) {
                mouse.hitByCar();
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
                    //This is a placeholder method for now that simply goes back to the previous
                    // screen.
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
                mouse.hitByCar();
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
                    //This is a placeholder method for now that simply goes back to the previous
                    // screen.
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
                    //This is a placeholder method for now that simply goes back to the previous
                    // screen.
                    handler.removeCallbacks(roadFive);
                    finish();
                }
            }
        }
    };

    private Runnable riverOne = new Runnable() {
        @Override
        public void run() {
            ImageView log1 = new ImageView(getApplicationContext());
            ViewGroup view;
            ObjectAnimator animation;
            view = (ViewGroup) findViewById(R.id.river_one);
            log1.setImageResource(R.drawable.log);
            view.addView(log1);
            animation = ObjectAnimator.ofFloat(log1, "translationX", 2000f);
            animation.setDuration(10000);
            animation.start();
            handler.postDelayed(riverOne, 3000);
            Rect rc1 = new Rect();
            characterSprite.getDrawingRect(rc1);
            Rect rc2 = new Rect();
            view.getDrawingRect(rc2);
            if (log1.getX() == characterSprite.getX()) {
                mouse.setRiding(true);
            }

            if (Rect.intersects(rc1, rc2) && mouse.getyPos() == 7) {
                //Remove a life.
                mouse.setRiding(true);
                ObjectAnimator playerAnimation = ObjectAnimator.ofFloat(characterSprite, "translationX", 2000f);
                playerAnimation.setDuration(10000);
                playerAnimation.start();
            }
        }
    };

    private Runnable riverTwo = new Runnable() {
        @Override
        public void run() {
            ImageView log2 = new ImageView(getApplicationContext());
            ViewGroup view;
            ObjectAnimator animation;
            view = (ViewGroup) findViewById(R.id.river_two);
            log2.setImageResource(R.drawable.log);
            view.addView(log2);
            animation = ObjectAnimator.ofFloat(log2, "translationX", 2000f, 1);
            animation.setDuration(10000);
            animation.start();
            handler.postDelayed(riverTwo, 2500);
        }
    };

    private Runnable riverThree = new Runnable() {
        @Override
        public void run() {
            ImageView log3 = new ImageView(getApplicationContext());
            ViewGroup view;
            ObjectAnimator animation;
            view = (ViewGroup) findViewById(R.id.river_three);
            log3.setImageResource(R.drawable.log);
            view.addView(log3);
            animation = ObjectAnimator.ofFloat(log3, "translationX", 3000f);
            animation.setDuration(10000);
            animation.start();
            handler.postDelayed(riverThree, 2800);
        }
    };

    private Runnable riverFour = new Runnable() {
        @Override
        public void run() {
            ImageView log4 = new ImageView(getApplicationContext());
            ViewGroup view;
            ObjectAnimator animation;
            view = (ViewGroup) findViewById(R.id.river_four);
            log4.setImageResource(R.drawable.log);
            view.addView(log4);
            animation = ObjectAnimator.ofFloat(log4, "translationX", 2500f, 1);
            animation.setDuration(10000);
            animation.start();
            handler.postDelayed(riverFour, 1500);
        }
    };



    public void finish() {
        Intent intent = new Intent(this, MainActivity5.class);
        intent.putExtra("score", Integer.toString(mouse.getScore()));
        startActivity(intent);
    }
}
