package com.icegotcha.surfaceviewgame;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.DisplayMetrics;

/**
 * Created by icegotcha on 26/9/2560.
 */

public abstract class BitmapDraw {

    protected static float icon_width_divide_factor = 2f;

    protected Bitmap bitmap;
    protected int x;
    protected int y;

    protected int maxX;
    protected int maxY;

    protected int id;

    public BitmapDraw(int screenW, int screenH, int i) {
        this.maxX = screenW;
        this.maxY = screenH;
        this.id = i;
    }

    protected abstract void initialBitmap(Context context);

    protected void resizeBitmap(Context context) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        int scrWidth = metrics.widthPixels;
        int iconW = (int) (scrWidth / icon_width_divide_factor);
        int bmpW = bitmap.getWidth();
        float scaleFactor = (float) iconW / bmpW;
        bitmap = Bitmap.createScaledBitmap(bitmap, iconW, (int) (bitmap.getHeight() * scaleFactor), true);
    }

    public abstract void setStartPosition();

    public abstract void update(int playerSpeed);

    public Bitmap getBitmap() {
        return bitmap;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
