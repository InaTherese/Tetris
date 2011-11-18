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
import com.tetris.game.Square;
import com.tetris.game.SquareImpl;

public class TetrisView extends TileView {

    private int gameState = GameController.READY;
    private RefreshHandler mRedrawHandler = new RefreshHandler();

    private long mLastMove;
    private GameController gameController;

    public TetrisView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initTetrisView();
    }

    public TetrisView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initTetrisView();
    }

    private void initTetrisView() {
        setFocusable(true);
        loadTiles(this.getContext().getResources());
    }

    private void newGame() {
        gameController = new GameControllerImpl();
        setMode(GameController.RUNNING);
        updateView();
    }

    public void setMode(int newMode) {
        gameState = newMode;
    }

    public void updateView() {
        if (gameState == GameController.RUNNING) {
            long now = System.currentTimeMillis();

            if (now - mLastMove > gameController.moveDelay()) {
                updateWalls();
                clearTiles();
                gameController.movePieceDown();
                mLastMove = now;
            }
            mRedrawHandler.sleep(gameController.moveDelay());
        }

    }

    private void updateWalls() {
        for (int x = 0; x < mXTileCount; x++) {
            setSquare(new SquareImpl(x, 0, Square.GREEN_SQAURE));
            setSquare(new SquareImpl(x, mYTileCount - 1, Square.GREEN_SQAURE));
        }
        for (int y = 1; y < mYTileCount - 1; y++) {
            setSquare(new SquareImpl(0, y, Square.GREEN_SQAURE));
            setSquare(new SquareImpl(mXTileCount - 1, y, Square.GREEN_SQAURE));
        }
    }

    private void loadTiles(Resources r) {
        resetTiles(8);
        loadTile(Square.BLUE_SQAURE, r.getDrawable(R.drawable.blue));
        loadTile(Square.GREEN_SQAURE, r.getDrawable(R.drawable.green));
        loadTile(Square.LIGHT_GREEN_SQAURE, r.getDrawable(R.drawable.light_green));
        loadTile(Square.ORANGE_SQAURE, r.getDrawable(R.drawable.orange));
        loadTile(Square.PINK_SQAURE, r.getDrawable(R.drawable.pink));
        loadTile(Square.PURPLE_SQAURE, r.getDrawable(R.drawable.purple));
        loadTile(Square.YELLOW_SQAURE, r.getDrawable(R.drawable.yellow));
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent msg) {

        switch (keyCode){
            case KeyEvent.KEYCODE_DPAD_UP:
                if (gameState == GameController.READY | gameState == GameController.LOSE) {
                    newGame();
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

    class RefreshHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            TetrisView.this.updateView();
            TetrisView.this.invalidate();
        }

        public void sleep(long delayMillis) {
            this.removeMessages(0);
            sendMessageDelayed(obtainMessage(0), delayMillis);
        }
    }
}
