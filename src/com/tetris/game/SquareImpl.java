package com.tetris.game;

import java.io.Serializable;

public class SquareImpl implements Square, Serializable {
    private int x;
    private int y;
    private int color;

    public SquareImpl(int x, int y, int color) {
        this.color = color;
        this.x = x;
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
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

    public void makeCoordinatesGlobal(int xOffset, int yOffset) {
        x+=xOffset;
        y+=yOffset;
    }

    public Square duplicate(){
        return new SquareImpl(x,y,color);
    }

    public void changeCoordinates(int x, int y) {
        this.x=x;
        this.y=y;
    }

    @Override
    public String toString() {
        return "SquareImpl{" +
                "x=" + x +
                ", y=" + y +
                ", color=" + color +
                '}';
    }
}
