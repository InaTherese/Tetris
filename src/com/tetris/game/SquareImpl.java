package com.tetris.game;

public class SquareImpl implements Square {
    private int x;
    private int y;
    private int color;

    public SquareImpl(int x, int y, int color) {
        this.color = color;
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getColor() {
        return color;
    }

    public void changeCoordinates(int x, int y) {
        this.x=x;
        this.y=y;
    }
}
