package com.icegotcha.surfaceviewgame;

import android.content.Context;
import java.util.Random;

/**
 * Created by icegotcha on 26/9/2560.
 */

public class Enemy extends BitmapDraw {

    public Enemy(Context context, float iconWidthDivideFactor, int id) {
        super(context, R.drawable.pig, iconWidthDivideFactor, id);
    }

    @Override
    public void setStartPosition() {
        Random rand = new Random();
        x = rand.nextInt(maxX - bitmap.getWidth());
        y = rand.nextInt(maxY);
    }

    @Override
    public void update(int playerSpeed) {
        Random rand = new Random();
        y += playerSpeed;
        if (y > maxY) {
            y = 0;
            x = rand.nextInt(maxX - bitmap.getWidth());
        }
    }
}
