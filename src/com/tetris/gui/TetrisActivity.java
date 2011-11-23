package com.tetris.gui;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import com.tetris.R;
import com.tetris.game.GameController;
import com.tetris.game.GameControllerImpl;

public class TetrisActivity extends Activity {
    TetrisViewController viewController;
    View view;
    private GameController gameController;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        view = findViewById(R.id.layout);
        gameController = new GameControllerImpl();
        viewController = new TetrisViewController(view, gameController);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent msg) {

        switch (keyCode) {
            case KeyEvent.KEYCODE_DPAD_UP:
                if (viewController.gameState == GameController.READY | viewController.gameState == GameController.LOSE) {
                    newGame();
                } else {
                    rotate();
                }
                break;
            case KeyEvent.KEYCODE_DPAD_DOWN:
                down();
                break;
            case KeyEvent.KEYCODE_DPAD_LEFT:
                left();
                break;
            case KeyEvent.KEYCODE_DPAD_RIGHT:
                right();
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
            if (viewController.gameState == GameController.READY | viewController.gameState == GameController.LOSE) {
                newGame();
            } else if (event.getY() > bottomThirdOfScreen) {
                down();
            } else {
                if (centerOfScreen * 1.3 > clickX && centerOfScreen * 0.7 < clickX) {
                    rotate();
                } else if (centerOfScreen < clickX) {
                    right();
                } else {
                    left();
                }
            }
        }
        return super.onTouchEvent(event);
    }

    public void newGame() {
        viewController.setMode(GameController.RUNNING);
        viewController.gameLoop();
    }
    public void rotate() {
        gameController.rotatePiece();
    }

    public void left() {
        gameController.movePieceLeft();
    }

    public void right() {
        gameController.movePieceRight();
    }

    public void down() {
        gameController.movePieceToBottom();
    }


}
