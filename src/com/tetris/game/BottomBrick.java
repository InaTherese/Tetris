package com.tetris.game;

public class BottomBrick implements Bottom {
    
    Square[][] bottomGrid = new Square[20][10];

    public boolean hasPieceAt(int x, int y) {
        return (bottomGrid[y][x]!=null);
    }
}
