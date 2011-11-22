package com.tetris.gui;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import com.tetris.R;
import com.tetris.game.GameController;
import com.tetris.game.GameControllerImpl;
import com.tetris.gui.tinyView.TinyTetrisView;

/**
 * Handles behaviour-specific code for the TetrisGame interface.
 * Has the main game loop, and handles the redraw-interval.
 * Handles keyboard-input.
 * Defers to TetrisView for the actual drawing.
 */
public class TetrisViewController extends TetrisView {
    private GameController gameController;
    RefreshHandler redrawHandler = new RefreshHandler();
    int gameState = GameController.READY;
    private long timeOfLastMove;
    private TextView comboBoard;
    private TextView scoreBoard;
    private TinyTetrisView nextPiecePreview;

    public TetrisViewController(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public TetrisViewController(Context context, AttributeSet attributeSet, int defStyle) {
        super(context, attributeSet, defStyle);
    }

    public void newGame() {
        gameController = new GameControllerImpl();
        setMode(GameController.RUNNING);
        View p = this.getRootView();
        scoreBoard = (TextView) p.findViewById(R.id.score);
        comboBoard = (TextView) p.findViewById(R.id.combo);
        nextPiecePreview = (TinyTetrisView) p.findViewById(R.id.next_piece);
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
        nextPiecePreview.redrawScreen(gameController.getNextPieceReadyToDraw());
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
            TetrisViewController.this.invalidate();
        }

        public void sleep(long delayMillis) {
            this.removeMessages(0);
            sendMessageDelayed(obtainMessage(0), delayMillis);
        }
    }
}
