package com.example;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;

public class TetrisView extends TileView {

    /**
     * Current mode of application: READY to run, RUNNING, or you have already
     * lost. static final ints are used instead of an enum for performance
     * reasons.
     */
    private int mMode = READY;
    public static final int PAUSE = 0;
    public static final int READY = 1;
    public static final int RUNNING = 2;
    public static final int LOSE = 3;
    /**
     * Labels for the drawables that will be loaded into the TileView class
     */

    private static final int BLUE_SQAURE = 1;
    private static final int GREEN_SQAURE = 2;
    private static final int LIGHT_GREEN_SQAURE = 3;
    private static final int ORANGE_SQAURE = 4;
    private static final int PINK_SQAURE = 5;
    private static final int PURPLE_SQAURE = 6;
    private static final int YELLOW_SQAURE = 7;

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

        Resources r = this.getContext().getResources();

        resetTiles(4);
        loadTile(BLUE_SQAURE, r.getDrawable(R.drawable.blue));
        loadTile(GREEN_SQAURE, r.getDrawable(R.drawable.green));
        loadTile(LIGHT_GREEN_SQAURE, r.getDrawable(R.drawable.light_green));
        loadTile(ORANGE_SQAURE, r.getDrawable(R.drawable.orange));
        loadTile(PINK_SQAURE, r.getDrawable(R.drawable.pink));
        loadTile(PURPLE_SQAURE, r.getDrawable(R.drawable.purple));
        loadTile(YELLOW_SQAURE, r.getDrawable(R.drawable.yellow));
    }
}
