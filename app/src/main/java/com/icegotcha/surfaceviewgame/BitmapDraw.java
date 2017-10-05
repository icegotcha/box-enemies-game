package com.icegotcha.surfaceviewgame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.DisplayMetrics;

/**
 * Created by icegotcha on 26/9/2560.
 */

public abstract class BitmapDraw {

    protected static float iconWidthDivideFactor;
    protected Context context;
    protected int maxX;
    protected int maxY;
    protected Bitmap bitmap;
    protected int x;
    protected int y;
    protected int id;

    public BitmapDraw(Context context, int resourceId, float iconWidthDivideFactor, int id) {

        this.context = context;

        DisplayMetrics metrics = context.getResources().getDisplayMetrics();

        bitmap = BitmapFactory.decodeResource(context.getResources(), resourceId);
        BitmapDraw.iconWidthDivideFactor = iconWidthDivideFactor;
        this.maxX = metrics.widthPixels;
        this.maxY = metrics.heightPixels;
        this.id = id;

        setStartPosition();
        resizeBitmap();

    }

    public abstract void setStartPosition();

    public abstract void update(int playerSpeed);

    protected void resizeBitmap() {
        int iconW = (int) (maxX / iconWidthDivideFactor);
        float scaleFactor = (float) iconW / bitmap.getWidth();
        int iconH = (int) (bitmap.getHeight() * scaleFactor);
        bitmap = Bitmap.createScaledBitmap(bitmap, iconW, iconH, true);
    }

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
