package com.icegotcha.surfaceviewgame;

import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;

public class MainActivity extends AppCompatActivity {

    private GameView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        Display display = getWindowManager().getDefaultDisplay();

        // Get the screen resolution into point object
        Point size = new Point();
        display.getSize(size);

        gameView = new GameView(this, size.x, size.y);
        Log.d("Screen", "Size X:" + size.x + ", Y:" + size.y);

        setContentView(gameView);

    }

    @Override
    protected void onResume() {
        super.onResume();
        gameView.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        gameView.pause();
    }
}
