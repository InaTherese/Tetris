package com.tetris.gui;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import com.tetris.R;
import com.tetris.game.Square;

import java.util.ArrayList;

/**
 * Handles drawing-related code for TetrisActivity. Where TileView is general and works with graphics,
 * TetrisView is game-specific and works with Squares.
 */
public class TetrisView extends TileView {
    public final static int BOARD_HEIGHT = 21;
    public final static int BOARD_WIDTH = 10;
    public final static int TILE_SIZE = 22;

    public TetrisView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, TILE_SIZE);
        initTetrisView();
    }

    public TetrisView(Context context, AttributeSet attributeSet, int defStyle) {
        super(context, attributeSet, defStyle, TILE_SIZE);
        initTetrisView();
    }

    private void initTetrisView() {
        setFocusable(true);
        loadTiles(this.getContext().getResources());
    }

    void redrawScreen(ArrayList<Square> pieceSquares) {
        clearScreen();
        drawSquares(pieceSquares);
    }

    void drawSquares(ArrayList<Square> squaresForPiece) {
        for (Square s : squaresForPiece) {
            setSquare(s);
        }
    }

    private void loadTiles(Resources r) {
        resetTiles(8);
        loadTile(Square.BLUE, r.getDrawable(R.drawable.blue));
        loadTile(Square.GREEN, r.getDrawable(R.drawable.green));
        loadTile(Square.LIGHT_GREEN, r.getDrawable(R.drawable.light_green));
        loadTile(Square.ORANGE, r.getDrawable(R.drawable.orange));
        loadTile(Square.PINK, r.getDrawable(R.drawable.pink));
        loadTile(Square.PURPLE, r.getDrawable(R.drawable.purple));
        loadTile(Square.YELLOW, r.getDrawable(R.drawable.yellow));
    }
}
