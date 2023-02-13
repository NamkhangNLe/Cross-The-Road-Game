package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
    }

    private String name;
    public void startGame(View view) {
        //errors again
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;

        //get inputted text and check if its valid
        TextInputLayout nameInput = (TextInputLayout) findViewById(R.id.nameInput);
        name = nameInput.getEditText().getText().toString();

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

            Intent intent = new Intent(this, MainActivity4.class);
            intent.putExtra("character", character);
            intent.putExtra("difficulty", difficulty);
            intent.putExtra("name", name);
            startActivity(intent);
        }
    }
}