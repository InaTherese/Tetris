package com.tetris.gui;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import com.tetris.R;
import com.tetris.game.Square;

import java.util.ArrayList;

/**
 * Combination of TetrisView and TileView that draws single Pieces without behaviour.
 */
public class TinyTetrisView extends View {
    protected static int mTileSize = 14;

    protected static int mXTileCount;
    protected static int mYTileCount;

    private static int mXOffset;
    private static int mYOffset;
    private Bitmap[] mTileArray;
    private int[][] mTileGrid;

    private final Paint mPaint = new Paint();

    public TinyTetrisView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initTetrisView();
    }

    public TinyTetrisView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initTetrisView();
    }

    private void initTetrisView() {
        setFocusable(true);
        loadTiles(this.getContext().getResources());
    }

    public void redrawScreen(ArrayList<Square> pieceSquares) {
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

        /**
         * Rests the internal array of Bitmaps used for drawing tiles, and
         * sets the maximum index of tiles to be inserted
         *
         * @param tilecount
         */
        public void resetTiles(int tilecount) {
            mTileArray = new Bitmap[tilecount];
        }

        @Override
        protected void onSizeChanged(int w, int h, int oldw, int oldh) {
            mXTileCount = (int) Math.floor(w / mTileSize);
            mYTileCount = (int) Math.floor(h / mTileSize);

            mXOffset = ((w - (mTileSize * mXTileCount)) / 2);
            mYOffset = ((h - (mTileSize * mYTileCount)) / 2);

            mTileGrid = new int[mXTileCount][mYTileCount];
            clearScreen();
        }

        /**
         * Function to set the specified Drawable as the tile for a particular
         * integer key.
         *
         * @param key
         * @param tile
         */
        public void loadTile(int key, Drawable tile) {
            Bitmap bitmap = Bitmap.createBitmap(mTileSize, mTileSize, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            tile.setBounds(0, 0, mTileSize, mTileSize);
            tile.draw(canvas);

            mTileArray[key] = bitmap;
        }

        /**
         * Resets all tiles to 0 (empty)
         */
        public void clearScreen() {
            for (int x = 0; x < mXTileCount; x++) {
                for (int y = 0; y < mYTileCount; y++) {
                    setTile(0, x, y);
                }
            }
        }

        /**
         * Used to indicate that a particular tile (set with loadTile and referenced
         * by an integer) should be drawn at the given x/y coordinates during the
         * next invalidate/draw cycle.
         *
         * @param tileindex
         * @param x
         * @param y
         */
        public void setTile(int tileindex, int x, int y) {
            mTileGrid[x][y] = tileindex;
        }

        public void setSquare(Square square) {
            if (square.getY() < TetrisView.BOARD_HEIGHT && square.getX() < TetrisView.BOARD_WIDTH)
                mTileGrid[square.getX()][square.getY()] = square.getColor();
        }

        @Override
        public void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            for (int x = 0; x < mXTileCount; x += 1) {
                for (int y = 0; y < mYTileCount; y += 1) {
                    if (mTileGrid[x][y] > 0) {
                        canvas.drawBitmap(mTileArray[mTileGrid[x][y]],
                                mXOffset + x * mTileSize,
                                mYOffset + y * mTileSize,
                                mPaint);
                    }
                }
            }
        }
}
