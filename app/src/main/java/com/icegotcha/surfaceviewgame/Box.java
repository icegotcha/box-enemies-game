package com.icegotcha.surfaceviewgame;

import android.content.Context;

/**
 * Created by icegotcha on 26/9/2560.
 */

public class Box extends BitmapDraw {

    private boolean isGoRight;

    public Box(Context context, boolean isGoRight, float iconWidthDivideFactor, int id) {
        super(context, R.drawable.packing, iconWidthDivideFactor, id);
        this.isGoRight = isGoRight;
    }

    @Override
    public void setStartPosition() {
        if (isGoRight) {
            x = 0;
        } else {
            x = maxX - bitmap.getWidth();
        }
        y = maxY - bitmap.getHeight() - 250;
    }

    public void update(int playerSpeed) {
        if (isGoRight) {
            x += playerSpeed;
            if (x > maxX - bitmap.getWidth()) {
                isGoRight = false;
            }
        } else {
            x -= playerSpeed;
            if (x < 0) {
                isGoRight = true;
            }
        }
    }
}
