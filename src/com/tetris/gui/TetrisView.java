package com.tetris.gui;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import com.tetris.R;
import com.tetris.game.Square;
import com.tetris.game.SquareImpl;

/**
 * Handles drawing-related code for Tetris. Where TileView is general and works with graphics,
 * TetrisView is game-specific and works with Squares.
 */
public class TetrisView extends TileView {

    public TetrisView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initTetrisView();
    }

    public TetrisView(Context context, AttributeSet attributeSet, int defStyle) {
        super(context, attributeSet, defStyle);
        initTetrisView();
    }

    private void initTetrisView() {
        setFocusable(true);
        loadTiles(this.getContext().getResources());
    }

    void redrawScreen(Square[] pieceSquares) {
        clearScreen();
        drawWalls();
        drawPiece(pieceSquares);
    }

    void drawPiece(Square[] squaresForPiece) {
        for (Square s : squaresForPiece) {
            setSquare(s);
        }
    }

    void drawWalls() {
        for (int x = 0; x < mXTileCount; x++) {
            setSquare(new SquareImpl(x, 0, Square.GREEN));
            setSquare(new SquareImpl(x, mYTileCount - 1, Square.GREEN));
        }
        for (int y = 1; y < mYTileCount - 1; y++) {
            setSquare(new SquareImpl(0, y, Square.GREEN));
            setSquare(new SquareImpl(mXTileCount - 1, y, Square.GREEN));
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
