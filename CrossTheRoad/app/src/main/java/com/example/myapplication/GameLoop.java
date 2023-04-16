package com.example.myapplication;

import android.graphics.Canvas;
import android.view.Surface;
import android.view.SurfaceHolder;

public class GameLoop extends Thread {
    private Game game;
    private boolean isRunning = false;
    private SurfaceHolder surfaceHolder;
    public GameLoop(Game game, SurfaceHolder surfaceHolder) {
        this.game = game;
        this.surfaceHolder = surfaceHolder;
    }

    public double getAverageUPS() {
        return 0.0;
    }

    public void startLoop() {
        isRunning = true;
        start();
    }

    @Override
    public void run() {
        super.run();

        // Game Loop
        Canvas canvas;
        while (isRunning) {
            // Try to update and render game
            try {
                canvas = surfaceHolder.lockCanvas();
                game.update();
                game.drawUPS(canvas);
                surfaceHolder.unlockCanvasAndPost(canvas);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }

            // Pause game loop to not exceed target UPS

            // Skip frames to keep up with target UPS

            // Calculate average UPS
        }
    }
}
