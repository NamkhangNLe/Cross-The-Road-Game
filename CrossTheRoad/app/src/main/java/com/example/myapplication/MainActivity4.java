package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        setUp();
    }

    private void setUp() {
        //Retrieve old variables
        Intent prev = getIntent();
        String name = prev.getStringExtra("name");
        String difficulty = prev.getStringExtra("difficulty");
        String lives;
        if (difficulty == "Easy") {
            lives = "5";
        } else if (difficulty == "Medium") {
            lives = "3";
        } else {
            lives = "1";
        }
        String character = prev.getStringExtra("character");
        //Display names and lives
        TextView nameDisplay = (TextView) findViewById(R.id.nameDisplay);
        TextView livesDisplay = (TextView) findViewById(R.id.livesDisplay);
        TextView difficultyDisplay = (TextView) findViewById(R.id.difficultyDisplay);
        nameDisplay.setText(name);
        livesDisplay.setText(lives);
        difficultyDisplay.setText(difficulty);
        //Display character
        ImageView characterSprite = (ImageView) findViewById(R.id.charSprite);
        Context context = getApplicationContext();
        if (character == "char1") {
            characterSprite.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.mouse, null));
        } else if (character == "char2") {
            characterSprite.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.rat, null));
        } else {
            characterSprite.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.remy, null));
        }
    }
}