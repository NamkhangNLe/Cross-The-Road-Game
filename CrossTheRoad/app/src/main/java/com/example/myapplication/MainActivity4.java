package com.example.myapplication;

//import android.animation.ValueAnimator;
//import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
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
    private Drawable correctSprite;
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
            correctSprite = getResources().getDrawable(R.drawable.mouse);
        } else if (character.equals("char2")) {
            characterSprite.setImageDrawable(ResourcesCompat.getDrawable(getResources(),
                    R.drawable.rat, null));
            correctSprite = getResources().getDrawable(R.drawable.rat);
        } else {
            characterSprite.setImageDrawable(ResourcesCompat.getDrawable(getResources(),
                    R.drawable.remy, null));
            correctSprite = getResources().getDrawable(R.drawable.remy);
        }
        runVehicles();
    }

    public void moveUp(View view) {
        if (mouse.getIsAnimate()) {
            characterSprite.setX(characterSprite.getX());
            characterSprite.setY(characterSprite.getY());
        }
        characterSprite.setRotation(0);
        if (mouse.getyPos() != 11) {
            float move = characterSprite.getHeight();
            characterSprite.setY(characterSprite.getY() - move);
            mouse.moveUp();
            if (mouse.checkGameWin()) {
                win();
            }
        }
        if (mouse.getyPos() > 6 && !mouse.getRiding() && mouse.getyPos() < 11) {
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
                mouse.resetPosition();
                finish();
            }
        }
        TextView scoreDisplay = findViewById(R.id.scoreDisplay);
        scoreDisplay.setText(Integer.toString(mouse.getScore()));
    }

    public void moveDown(View view) {
        if (mouse.getIsAnimate()) {
            characterSprite = findViewById(R.id.charSprite);
            characterSprite.setImageDrawable(correctSprite);
            characterSprite.setX(mouse.getaX());
            characterSprite.setY(mouse.getaY());
            mouse.setIsAnimate(false);
        }
        //Rotate the mouse.
        characterSprite.setRotation(180);

        if (mouse.getyPos() > 5 && !mouse.getRiding()) {
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

        if (mouse.getyPos() > 6 && !mouse.getRiding()) {
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
            if (mouse.checkGameWin()) {
                win();
            }
        }

        TextView scoreDisplay = findViewById(R.id.scoreDisplay);
        scoreDisplay.setText(Integer.toString(mouse.getScore()));
    }
    public void moveRight(View view) {
        //Rotate the mouse.
        characterSprite.setRotation(90);

        if (mouse.getyPos() > 6 && !mouse.getRiding()) {
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
            if (mouse.checkGameWin()) {
                win();
            }
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
            animation.setInterpolator(new LinearInterpolator());
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
                if (mouse.getLives() > 0 && !mouse.isGameWin()) {
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
            animation.setInterpolator(new LinearInterpolator());
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
                if (mouse.getLives() > 0 && !mouse.isGameWin()) {
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
            animation.setInterpolator(new LinearInterpolator());
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
                if (mouse.getLives() > 0 && !mouse.isGameWin()) {
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
            animation.setInterpolator(new LinearInterpolator());
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
                if (mouse.getLives() > 0 && !mouse.isGameWin()) {
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
            animation.setInterpolator(new LinearInterpolator());
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
                if (mouse.getLives() > 0 && !mouse.isGameWin()) {
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
            ObjectAnimator playerAnimation = null;
            ObjectAnimator animation;
            view = (ViewGroup) findViewById(R.id.river_one);
            log1.setImageResource(R.drawable.log);
            view.addView(log1);
            animation = ObjectAnimator.ofFloat(log1, "translationX", 2000f);
            animation.setDuration(10000);
            animation.setInterpolator(new LinearInterpolator());
            animation.start();
            handler.postDelayed(riverOne, 3000);
            Rect rc1 = new Rect();
            characterSprite.getDrawingRect(rc1);
            Rect rc2 = new Rect();
            view.getDrawingRect(rc2);
            if (Rect.intersects(rc1, rc2) && mouse.getyPos() == 7) {
                //Remove a life.
                mouse.setRiding(true);
                mouse.setIsAnimate(true);
                playerAnimation = ObjectAnimator.ofFloat(characterSprite, "translationX", 2000f);
                playerAnimation.setDuration(10000);
                playerAnimation.setInterpolator(new LinearInterpolator());
                playerAnimation.start();
            } else if (mouse.getyPos() == 7){
                mouse.setRiding(false);
            }
            if (mouse.getyPos() != 7) {
                mouse.setaX(characterSprite.getX());
                mouse.setaY(characterSprite.getY());
            }
            int[] location = new int[2];
            characterSprite.getLocationOnScreen(location);
            int x = location[0];
            Log.d("myTag", String.valueOf(x));
            if (x >= 1300) {
                if (mouse.getLives() > 1) {
                    float xMove = characterSprite.getWidth();
                    characterSprite.setX(xMove * 5);
                    float yMove = characterSprite.getHeight();
                    characterSprite.setY(characterSprite.getY() + yMove * mouse.getyPos());
                    mouse.removeLife();
                    TextView livesDisplay = findViewById(R.id.livesDisplay);
                    livesDisplay.setText(Integer.toString(mouse.getLives()));
                    mouse.resetPosition();
                    characterSprite.setRotation(0);
                    //Reset the score to 0.
                    mouse.setScore(0);
                    TextView scoreDisplay = findViewById(R.id.scoreDisplay);
                    int score = mouse.getScore();
                    scoreDisplay.setText(Integer.toString(score));
                    mouse.resetScoreIncrement();
                } else {
                    handler.removeCallbacks(riverOne);
                    finish();
                }
            }
        }
    };

    private Runnable riverTwo = new Runnable() {
        @Override
        public void run() {
            ObjectAnimator playerAnimation;
            ImageView log2 = new ImageView(getApplicationContext());
            ViewGroup view;
            ObjectAnimator animation;
            view = (ViewGroup) findViewById(R.id.river_two);
            log2.setImageResource(R.drawable.log);
            view.addView(log2);
            animation = ObjectAnimator.ofFloat(log2, "translationX", 2000f, 1);
            animation.setDuration(10000);
            animation.setInterpolator(new LinearInterpolator());
            animation.start();
            handler.postDelayed(riverTwo, 2500);
            Rect rc1 = new Rect();
            characterSprite.getDrawingRect(rc1);
            Rect rc2 = new Rect();
            view.getDrawingRect(rc2);
            if (log2.getX() == characterSprite.getX()) {
                mouse.setRiding(true);
            }

            if (Rect.intersects(rc1, rc2) && mouse.getyPos() == 8) {
                //Remove a life.
                mouse.setRiding(true);
                playerAnimation = ObjectAnimator.ofFloat(characterSprite, "translationX", 2000f, 1);
                playerAnimation.setDuration(10000);
                playerAnimation.setInterpolator(new LinearInterpolator());
                playerAnimation.start();
            }
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
            animation.setInterpolator(new LinearInterpolator());
            animation.start();
            handler.postDelayed(riverThree, 2800);
            Rect rc1 = new Rect();
            characterSprite.getDrawingRect(rc1);
            Rect rc2 = new Rect();
            view.getDrawingRect(rc2);
            if (log3.getX() == characterSprite.getX()) {
                mouse.setRiding(true);
            }

            if (Rect.intersects(rc1, rc2) && mouse.getyPos() == 9) {
                //Remove a life.
                mouse.setRiding(true);
                ObjectAnimator playerAnimation = ObjectAnimator.ofFloat(characterSprite,
                        "translationX", 3000f);
                playerAnimation.setDuration(10000);
                playerAnimation.setInterpolator(new LinearInterpolator());
                playerAnimation.start();
            }
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
            animation.setInterpolator(new LinearInterpolator());
            animation.start();
            handler.postDelayed(riverFour, 1500);
            Rect rc1 = new Rect();
            characterSprite.getDrawingRect(rc1);
            Rect rc2 = new Rect();
            view.getDrawingRect(rc2);
            if (log4.getX() == characterSprite.getX()) {
                mouse.setRiding(true);
            }

            if (Rect.intersects(rc1, rc2) && mouse.getyPos() == 10) {
                //Remove a life.
                mouse.setRiding(true);
                ObjectAnimator playerAnimation = ObjectAnimator.ofFloat(characterSprite,
                        "translationX", 2000f);
                playerAnimation.setDuration(10000);
                playerAnimation.setInterpolator(new LinearInterpolator());
                playerAnimation.start();
            }
        }
    };

    public void win() {
        mouse.setGameWin(true);
        mouse.setScore((mouse.getScore() + 500));
        Intent intent = new Intent(this, Main7Activity.class);
        intent.putExtra("score", Integer.toString((mouse.getScore())));
        startActivity(intent);
    }

    public void finish() {
        Intent intent = new Intent(this, MainActivity5.class);
        intent.putExtra("score", Integer.toString(mouse.getScore()));
        startActivity(intent);
    }
}
