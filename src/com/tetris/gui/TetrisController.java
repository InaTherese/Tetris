package com.tetris.gui;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import com.tetris.R;
import com.tetris.game.GameController;
import com.tetris.game.GameControllerImpl;

/**
 * Handles behaviour-specific code for the Tetris interface.
 * Has the main game loop, and handles the redraw-interval.
 * Handles keyboard-input.
 * Defers to TetrisView for the actual drawing.
 */
public class TetrisController extends TetrisView {
    private GameController gameController;
    RefreshHandler redrawHandler = new RefreshHandler();
    private int gameState = GameController.READY;
    private long timeOfLastMove;
    private TextView comboBoard;
    private TextView scoreBoard;

    public TetrisController(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public TetrisController(Context context, AttributeSet attributeSet, int defStyle) {
        super(context, attributeSet, defStyle);
    }

    public void newGame() {
        gameController = new GameControllerImpl();
        setMode(GameController.RUNNING);
        gameLoop();
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

    public void gameLoop() {
        if (isTimeToMovePieceDown()) {
            gameController.movePieceDown();
            updateScoreBoard();
            timeOfLastMove = System.currentTimeMillis();
        }
        redrawScreen(gameController.getSquaresReadyToDraw());
        redrawHandler.sleep(10);
    }

    void updateScoreBoard() {
        View p = this.getRootView();
        scoreBoard = (TextView) p.findViewById(R.id.score);
        comboBoard = (TextView) p.findViewById(R.id.combo);
        scoreBoard.setText("Score: " + gameController.getScore());
        comboBoard.setText(gameController.getRemainingTimeOfCombo()/1000 + "s; x" + gameController.getCombos());
    }

    public void setMode(int newMode) {
        gameState = newMode;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int centerOfScreen = this.getWidth() / 2;
        double bottomThirdOfScreen = (this.getHeight() / 2) * 1.5;
        float clickX = event.getX();
        if (gameState == GameController.READY | gameState == GameController.LOSE) {
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
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent msg) {

        switch (keyCode) {
            case KeyEvent.KEYCODE_DPAD_UP:
                if (gameState == GameController.READY | gameState == GameController.LOSE) {
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

    private boolean isTimeToMovePieceDown() {
        return System.currentTimeMillis() - timeOfLastMove > gameController.moveDelay();
    }

    class RefreshHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            TetrisController.this.gameLoop();
            TetrisController.this.invalidate();
        }

        public void sleep(long delayMillis) {
            this.removeMessages(0);
            sendMessageDelayed(obtainMessage(0), delayMillis);
        }
    }
}
