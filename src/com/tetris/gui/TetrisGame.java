package com.tetris.gui;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import com.tetris.R;
import com.tetris.game.GameController;

public class TetrisGame extends Activity {
    TetrisViewController view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        view = (TetrisViewController) findViewById(R.id.tetris);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent msg) {

        switch (keyCode) {
            case KeyEvent.KEYCODE_DPAD_UP:
                if (view.gameState == GameController.READY | view.gameState == GameController.LOSE) {
                    view.newGame();
                } else {
                    view.rotate();
                }
                break;
            case KeyEvent.KEYCODE_DPAD_DOWN:
                view.down();
                break;
            case KeyEvent.KEYCODE_DPAD_LEFT:
                view.left();
                break;
            case KeyEvent.KEYCODE_DPAD_RIGHT:
                view.right();
                break;
        }
        return super.onKeyDown(keyCode, msg);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            int centerOfScreen = view.getWidth() / 2;
            double bottomThirdOfScreen = (view.getHeight() / 2) * 1.5;
            float clickX = event.getX();
            if (view.gameState == GameController.READY | view.gameState == GameController.LOSE) {
                view.newGame();
            } else if (event.getY() > bottomThirdOfScreen) {
                view.down();
            } else {
                if (centerOfScreen * 1.3 > clickX && centerOfScreen * 0.7 < clickX) {
                    view.rotate();
                } else if (centerOfScreen < clickX) {
                    view.right();
                } else {
                    view.left();
                }
            }
        }
        return super.onTouchEvent(event);
    }

}
