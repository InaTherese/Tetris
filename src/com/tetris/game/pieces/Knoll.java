package com.tetris.game.pieces;

import com.tetris.game.Piece;
import com.tetris.game.Square;
import com.tetris.game.SquareImpl;

public class Knoll extends Piece {

    boolean standing = false;
    
    public Knoll(){
        piece[0] = new SquareImpl(0,1, Square.ORANGE);
        piece[1] = new SquareImpl(1,1, Square.ORANGE);
        piece[2] = new SquareImpl(1,0, Square.ORANGE);
        piece[3] = new SquareImpl(2,0, Square.ORANGE);
    }
    
    @Override
    public void rotate() {
        if (standing){
            layItDown();
        } else {
            standUp();
        }
    }

    private void layItDown() {
        piece[0].changeCoordinates(0,1);
        piece[1].changeCoordinates(1,1);
        piece[2].changeCoordinates(1,0);
        piece[3].changeCoordinates(2,0);
        standing = false;
    }

    public void standUp() {
        piece[0].changeCoordinates(1, 0);
        piece[1].changeCoordinates(1, 1);
        piece[2].changeCoordinates(2, 1);
        piece[3].changeCoordinates(2, 2);
        standing = true;
    }
}
