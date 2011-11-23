package com.tetris.gui;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;
import com.tetris.R;
import com.tetris.game.GameController;

/**
 * Handles behaviour-specific code for the TetrisActivity interface.
 * Has the main game loop, and handles the redraw-interval.
 * Handles keyboard-input.
 * Defers to TetrisView for the actual drawing.
 */
public class TetrisViewController {
    private GameController gameController;
    RefreshHandler redrawHandler = new RefreshHandler();
    int gameState = GameController.READY;
    private long timeOfLastMove;
    private TextView comboBoard;
    private TextView scoreBoard;
    private TetrisView tetrisView;
    private TinyTetrisView preview;

    public TetrisViewController(View viewContainer, GameController controller) {
        this.gameController = controller;
        comboBoard = (TextView)viewContainer.findViewById(R.id.combo);
        scoreBoard = (TextView)viewContainer.findViewById(R.id.score);
        preview = (TinyTetrisView)viewContainer.findViewById(R.id.next_piece);
        tetrisView = (TetrisView)viewContainer.findViewById(R.id.tetris);
    }

    public void gameLoop() {
        if (isTimeToMovePieceDown()) {
            gameController.movePieceDown();
            updateScoreBoard();
            timeOfLastMove = System.currentTimeMillis();
        }
        tetrisView.redrawScreen(gameController.getSquaresReadyToDraw());
        redrawHandler.sleep(10);
    }

    void updateScoreBoard() {
        preview.redrawScreen(gameController.getNextPieceReadyToDraw());
        scoreBoard.setText("" + gameController.getScore());
        comboBoard.setText(gameController.getRemainingTimeOfCombo() / 1000 + "s; x" + gameController.getCombos());
    }

    public void setMode(int newMode) {
        gameState = newMode;
    }

    private boolean isTimeToMovePieceDown() {
        return System.currentTimeMillis() - timeOfLastMove > gameController.moveDelay();
    }

    class RefreshHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            TetrisViewController.this.gameLoop();
            TetrisViewController.this.tetrisView.invalidate();
        }

        public void sleep(long delayMillis) {
            this.removeMessages(0);
            sendMessageDelayed(obtainMessage(0), delayMillis);
        }
    }
}
