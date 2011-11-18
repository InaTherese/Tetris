package com.tetris.game;

public abstract class Piece {

    int x = 0;
    int y = 0;
    
    public abstract void rotate();

    void moveLeft() {
        if (x>0)
            x-=1;
    }

    void moveRight() {
        if (x<10)
            x+=1;
    }

    void moveDown() {
        if (y>20)
            y+=1;
    }
}
