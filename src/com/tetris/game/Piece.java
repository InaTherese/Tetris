package com.tetris.game;

public abstract class Piece {

    int x = 0;
    int y = 0;

    protected Square[] piece = new Square[4];

    public abstract void rotate();

    private Square[] getCopyOfSquares(){
        Square[] s = new Square[4];
        for (int i=0;i<4;i++){
            s[i] = piece[i].duplicate();
        }
        return s;
    }

    public Square[] getSquaresWithGlobalCoordinates(){
        Square[] squares = getCopyOfSquares().clone();
        for (Square s : squares){
            s.makeCoordinatesGlobal(x,y);
        }
        return squares;
    }

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
