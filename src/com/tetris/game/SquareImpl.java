package com.tetris.game;

public class SquareImpl implements Square {
    private int x;
    private int y;

    public SquareImpl(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void changeCoordinates(int x, int y) {
        this.x=x;
        this.y=y;
    }
}
