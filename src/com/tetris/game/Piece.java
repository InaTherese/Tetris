package com.tetris.game;

import com.tetris.gui.TetrisView;

public abstract class Piece {

    int x = 0;
    int y = 0;

    protected Square[] piece = new Square[4];

    public Square[] getSquaresWithGlobalCoordinates(){
        Square[] squares = getCopyOfSquares().clone();
        for (Square s : squares){
            s.makeCoordinatesGlobal(x,y);
        }
        return squares;
    }

    private Square[] getCopyOfSquares(){
        Square[] s = new Square[4];
        for (int i=0;i<4;i++){
            s[i] = piece[i].duplicate();
        }
        return s;
    }

    public abstract void rotate();

    void moveLeft() {
        if (withinBounds(-1, 0))
            x-=1;
    }

    void moveRight() {
        if (withinBounds(1, 0))
            x+=1;
    }

    void moveDown() {
        if (withinBounds(0, 1))
            y+=1;
    }
    
    public boolean withinBounds(int x, int y){
        for (Square s : getSquaresWithGlobalCoordinates()){
            if (squareOutOfBounds(x, y, s))
                return false;
        }
        return true;
    }

    private boolean squareOutOfBounds(int x, int y, Square s) {
        return s.getX()+x>= TetrisView.BOARD_WIDTH || s.getX()+x<0 || s.getY()+y>=TetrisView.BOARD_HEIGHT || s.getY()+y<0;
    }
}
