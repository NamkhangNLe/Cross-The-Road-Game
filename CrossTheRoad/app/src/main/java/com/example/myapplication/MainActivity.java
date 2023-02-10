package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //This function happens when the activity begins, on setup
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void charSelect(View view) {
        //This is a function which begins another activity
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }
}