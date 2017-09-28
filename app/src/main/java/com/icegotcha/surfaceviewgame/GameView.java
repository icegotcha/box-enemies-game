package com.icegotcha.surfaceviewgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by icegotcha on 19/9/2560.
 */

public class GameView extends SurfaceView implements Runnable {

    private static final String TAG = "Game";
    Enemy[] enemies;
    int enemyCount = 5;
    Box[] boxes;
    int boxCount = 2;
    private Thread gameThread = null;
    private volatile boolean playing;
    private SurfaceHolder surfaceHolder;
    private Canvas canvas;
    private Paint paint;
    private int screenW;
    private int screenH;
    // This variable track the game frame rate
    private long fps = (long) 2.0; // dummy (normal should be > 30)
    // Help calculate the fps
    private long timeThisFrame;

    public GameView(Context context) {
        super(context);
    }

    public GameView(Context context, int screenW, int screenH) {
        super(context);
        this.screenW = screenW;
        this.screenH = screenH;

        surfaceHolder = getHolder();
        paint = new Paint();

        playing = true;

        enemies = new Enemy[enemyCount];
        for (int i = 0; i < enemyCount; i++) {
            enemies[i] = new Enemy(context, screenW, screenH, i);
            // context คือสภาพ ณ ปัจจุบันที่เรียกใช้
        }

        boxes = new Box[boxCount];
        boxes[0] = new Box(context, screenW, screenH, false, 0);
        boxes[1] = new Box(context, screenW, screenH, true, 1);
    }

    @Override
    public void run() {
        Log.d(TAG, "Starting game");
        while (playing) {
            Log.d(TAG, "In Game");
            long startFrameTime = System.currentTimeMillis();

            // Update the frame
            update();

            // Draw the frame
            draw();

            timeThisFrame = System.currentTimeMillis() - startFrameTime;
            if (timeThisFrame > 0) {
                fps = 1000 / 6 / timeThisFrame;
            }

            control();
        }
    }

    private void update() {
        Log.d(TAG, "Update Frame...");

        int movingSpeed = 20;
        for (int i = 0; i < enemyCount; i++) {
            enemies[i].update(movingSpeed);
        }

        for (int i = 0; i < boxCount; i++) {
            boxes[i].update(movingSpeed);
        }
    }

    private void draw() {
        Log.d(TAG, "Drawing...");

        if (surfaceHolder.getSurface().isValid()) {
            canvas = surfaceHolder.lockCanvas();
            canvas.drawColor(Color.argb(255, 26, 128, 182));

            paint.setColor(Color.argb(255, 249, 129, 0));

            paint.setTextSize(30);
            canvas.drawText("FPS :" + fps, 20, 40, paint);

            // draw enemies
            for (int i = 0; i < enemyCount; i++) {
                canvas.drawBitmap(
                        enemies[i].getBitmap(),
                        enemies[i].getX(),
                        enemies[i].getY(),
                        paint);
            }

            //draw box
            for (int i = 0; i < boxCount; i++) {
                canvas.drawBitmap(
                        boxes[i].getBitmap(),
                        boxes[i].getX(),
                        boxes[i].getY(),
                        paint);
            }

            surfaceHolder.unlockCanvasAndPost(canvas);
        }

    }

    private void control() {
        try {
            Thread.sleep(30);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    // If Game Activity is paused/stopped, shutdown this thread
    public void pause() {
        playing = false;
        try {
            gameThread.join();
        } catch (InterruptedException e) {
            Log.d("Error:", "Can not join thread");
        }
    }

    // If GameEngine Activity is start, start the thread
    public void resume() {
        playing = true;
        gameThread = new Thread(this);
        gameThread.start();
    }
}
