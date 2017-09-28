package com.icegotcha.surfaceviewgame;

import android.content.Context;
import android.graphics.BitmapFactory;

import java.util.Random;

/**
 * Created by icegotcha on 26/9/2560.
 */

public class Enemy extends BitmapDraw {
    private static final String TAG = "Enemy";

    public Enemy(int screenW, int screenH, int i) {
        super(screenW, screenH, i);
    }

    public Enemy(Context context, int screenW, int screenH, int i) {
        this(screenW, screenH, i);
        initialBitmap(context);
    }

    @Override
    protected void initialBitmap(Context context) {
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.pig);
        icon_width_divide_factor = 5.2f;
        resizeBitmap(context);
        setStartPosition();
    }

    @Override
    public void setStartPosition() {
        Random rand = new Random();
        x = rand.nextInt(maxX - bitmap.getWidth());
        //x = 100;
        y = rand.nextInt(maxY);
        //y = 0;
    }

    public void update(int playerSpeed) {
        Random rand = new Random();
        y += playerSpeed;
        if (y > maxY) {
            y = 0;
            x = rand.nextInt(maxX - bitmap.getWidth());
        }
    }
}
