package com.icegotcha.surfaceviewgame;

import android.content.Context;
import android.graphics.BitmapFactory;

/**
 * Created by icegotcha on 26/9/2560.
 */

public class Box extends BitmapDraw {
    private static final String TAG = "Box";

    private boolean left;

    public Box(int screenW, int screenH, int i) {
        super(screenW, screenH, i);
    }

    public Box(Context context, int screenW, int screenH, boolean left, int i) {
        this(screenW, screenH, i);
        this.left = left;

        initialBitmap(context);
    }

    @Override
    protected void initialBitmap(Context context) {
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.packing);
        icon_width_divide_factor = 4f;
        resizeBitmap(context);
        setStartPosition();
    }

    @Override
    public void setStartPosition() {
        if (left) {
            x = bitmap.getWidth();
        } else {
            x = maxX - bitmap.getWidth();
        }
        y = maxY - bitmap.getHeight() - 500;
    }

    public void update(int playerSpeed) {
        if (left) {
            x += playerSpeed;
            if (x > maxX - bitmap.getWidth()) {
                left = false;
            }
        } else {
            x -= playerSpeed;
            if (x < 0) {
                left = true;
            }
        }
    }
}
