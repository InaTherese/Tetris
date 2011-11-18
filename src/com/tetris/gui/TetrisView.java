package com.tetris.gui;

import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.KeyEvent;
import com.tetris.R;
import com.tetris.game.GameController;
import com.tetris.game.GameControllerImpl;
import com.tetris.game.Piece;

public class TetrisView extends TileView {

    private int gameState = GameController.READY;

    private long mLastMove;

    GameController gameController;

    public TetrisView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initTetrisView();
    }

    public TetrisView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initTetrisView();
    }

    private RefreshHandler mRedrawHandler = new RefreshHandler();

    class RefreshHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            TetrisView.this.update();
            TetrisView.this.invalidate();
        }

        public void sleep(long delayMillis) {
            this.removeMessages(0);
            sendMessageDelayed(obtainMessage(0), delayMillis);
        }
    }

    private void initTetrisView() {
        setFocusable(true);

        Resources r = this.getContext().getResources();

        resetTiles(4);
        loadTile(Piece.BLUE_SQAURE, r.getDrawable(R.drawable.blue));
        loadTile(Piece.GREEN_SQAURE, r.getDrawable(R.drawable.green));
        loadTile(Piece.LIGHT_GREEN_SQAURE, r.getDrawable(R.drawable.light_green));
        loadTile(Piece.ORANGE_SQAURE, r.getDrawable(R.drawable.orange));
        loadTile(Piece.PINK_SQAURE, r.getDrawable(R.drawable.pink));
        loadTile(Piece.PURPLE_SQAURE, r.getDrawable(R.drawable.purple));
        loadTile(Piece.YELLOW_SQAURE, r.getDrawable(R.drawable.yellow));
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent msg) {

        switch (keyCode){
            case KeyEvent.KEYCODE_DPAD_UP:
                if (gameState == GameController.READY | gameState == GameController.LOSE) {
                    setupNewGame();
                } else if (gameState == GameController.PAUSE) {
                    resumeGame();
                } else {
                    gameController.rotatePiece();
                }
                return true;
            case KeyEvent.KEYCODE_DPAD_DOWN:
                gameController.movePieceDown();
                return true;
            case KeyEvent.KEYCODE_DPAD_LEFT:
                gameController.movePieceLeft();
                return true;
            case KeyEvent.KEYCODE_DPAD_RIGHT:
                gameController.movePieceRight();
                return true;
        }

        return super.onKeyDown(keyCode, msg);
    }

    private void resumeGame() {
        setMode(GameController.RUNNING);
        update();
    }

    private void setupNewGame() {
        gameController = new GameControllerImpl();
        setMode(GameController.RUNNING);
        update();
    }

    public void setMode(int newMode) {
        gameState = newMode;
    }

    public void update() {
        if (gameState == GameController.RUNNING) {
            long now = System.currentTimeMillis();

            if (now - mLastMove > gameController.moveDelay()) {
                clearTiles();
                gameController.movePieceDown();
                mLastMove = now;
            }
            mRedrawHandler.sleep(gameController.moveDelay());
        }

    }
}
