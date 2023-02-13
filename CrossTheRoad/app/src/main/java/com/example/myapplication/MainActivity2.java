package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {
    /**
     * onCreate.
     * @param savedInstanceState savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

    /**
     * select Name.
     * @param view view
     */
    public void nameSelect(View view) {
        //For errors
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;

        //Variables for the radiobutton
        RadioGroup selectCharacter = findViewById(R.id.charRadioGroup);
        RadioGroup selectDifficulty = findViewById(R.id.difRadioGroup);

        //Sets the selected difficulty, error if none selected
        String difficulty;
        if (selectDifficulty.getCheckedRadioButtonId() == R.id.easyButton) {
            difficulty = "Easy";
        } else if (selectDifficulty.getCheckedRadioButtonId() == R.id.mediumButton) {
            difficulty = "Medium";
        } else if (selectDifficulty.getCheckedRadioButtonId() == R.id.hardButton) {
            difficulty = "Hard";
        } else {
            Toast selectDifficultyError = Toast.makeText(context, "Select a Difficulty", duration);
            selectDifficultyError.show();
            return;
        }

        //Sets the selected player, error if none selected
        String character;
        if (selectCharacter.getCheckedRadioButtonId() == R.id.char1Button) {
            character = "char1";
        } else if (selectCharacter.getCheckedRadioButtonId() == R.id.char2Button) {
            character = "char2";
        } else if (selectCharacter.getCheckedRadioButtonId() == R.id.char3Button) {
            character = "char3";
        } else {
            Toast selectPlayerError = Toast.makeText(context, "Select a Player", duration);
            selectPlayerError.show();
            return;
        }

        //Go to next screen to enter name
        Intent intent = new Intent(this, MainActivity3.class);
        //passes variables
        intent.putExtra("difficulty", difficulty);
        intent.putExtra("character", character);
        startActivity(intent);
    }
}
