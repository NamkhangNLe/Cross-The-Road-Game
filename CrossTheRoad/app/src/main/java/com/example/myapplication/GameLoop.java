package com.example.myapplication;

import android.view.SurfaceHolder;

public class GameLoop {
    private boolean isRunning = false;
    public GameLoop(Game game, SurfaceHolder surfaceHolder) {
    }

    public double getAverageUPS() {
        return 0.0;
    }

    public void startLoop() {
        isRunning = true;
        start();
    }
}
