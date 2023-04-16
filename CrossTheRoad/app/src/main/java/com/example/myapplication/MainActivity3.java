package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class MainActivity3 extends AppCompatActivity {
    /**
     * onCreate.
     * @param savedInstanceState savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
    }

    /**
     * start Game.
     * @param view view
     */
    public void startGame(View view) {
        //errors again
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;

        //get inputted text and check if its valid
        TextInputLayout nameInput = findViewById(R.id.nameInput);
        String name = Objects.requireNonNull(nameInput.getEditText()).getText().toString();

        boolean isNotValid = (name.trim().isEmpty());
        if (isNotValid) {
            Toast nameError = Toast.makeText(context, "Enter a name", duration);
            nameError.show();
        } else {
            String character;
            String difficulty;

            //gets the previous activity's variables
            Intent prev = getIntent();
            character = prev.getStringExtra("character");
            difficulty = prev.getStringExtra("difficulty");

            Intent intent = new Intent(this, MainActivityPrime.class);
            intent.putExtra("character", character);
            intent.putExtra("difficulty", difficulty);
            intent.putExtra("name", name);
            startActivity(intent);
        }
    }
}
