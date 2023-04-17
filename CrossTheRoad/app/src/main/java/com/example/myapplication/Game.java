package com.example.myapplication;

import android.app.Notification;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

/**
 * Game manages all objects in the game and is responsible for updating all states and rendering
 * all objects to the screen
 */
class Game extends SurfaceView implements SurfaceHolder.Callback {
    private GameLoop gameLoop;
    private Context context;
    private Player mouse;

    public Game(Context context, Player player) {
        super(context);

        //Get surface holder and add callback
        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);

        this.context = context;
        gameLoop = new GameLoop(this, surfaceHolder);

        // Initialize the player (mouse)
        mouse = player;

        setFocusable(true);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        gameLoop.startLoop();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {

    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        drawBackground(canvas);
        drawPlayer(canvas, mouse);
    }

    public void drawBackground(Canvas canvas) {
        // Convert the background PNG into a bitmap
        Bitmap background = BitmapFactory.decodeResource(getResources(), R.drawable.background);
        Paint paint = new Paint();
        int color = ContextCompat.getColor(context, R.color.background_color);
        paint.setColor(color);
        int width = Resources.getSystem().getDisplayMetrics().widthPixels;
        int height = Resources.getSystem().getDisplayMetrics().heightPixels;
        canvas.drawBitmap(Bitmap.createScaledBitmap(background, width, height, false),0,0,paint);
    }

    public void drawPlayer(Canvas canvas, Player player) {
        Paint paint = new Paint();
        int color = ContextCompat.getColor(context, R.color.background_color);
        paint.setColor(color);
        canvas.drawText(player.getName(),10,10,paint);
//        ImageView playerSprite = player.getSprite();
//        Drawable drawable = playerSprite.getDrawable();
//        drawable.draw(canvas);

//        // Convert the mouse PNG into a bitmap
//        Bitmap playerSprite = BitmapFactory.decodeResource(getResources(), R.drawable.char_1);
//        Paint paint = new Paint();
//        int color = ContextCompat.getColor(context, R.color.background_color);
//        paint.setColor(color);
//        //int width = Resources.getSystem().getDisplayMetrics().widthPixels;
//        //int height = Resources.getSystem().getDisplayMetrics().heightPixels;
//        //canvas.drawBitmap(Bitmap.createScaledBitmap(background, width, height, false),0,0,paint);
//        canvas.drawBitmap(playerSprite, 10,10,paint);
    }


    public void drawUPS(Canvas canvas) {
        String averageUPS = Double.toString(gameLoop.getAverageUPS());
        Paint paint = new Paint();
        int color = ContextCompat.getColor(context, R.color.background_color);
        paint.setColor(color);
        Rect r = new Rect(10,10,50,50);
        canvas.drawRect(r, paint);
    }

    public void update() {
        // Update game state
        mouse.update();
    }
}
