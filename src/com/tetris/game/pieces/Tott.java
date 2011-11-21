package com.tetris.game.pieces;

import com.tetris.game.Piece;
import com.tetris.game.Square;
import com.tetris.game.SquareImpl;

public class Tott extends Piece {

    boolean standing = false;
    
    public Tott(){
        piece[0] = new SquareImpl(0,0, Square.LIGHT_GREEN);
        piece[1] = new SquareImpl(1,0, Square.LIGHT_GREEN);
        piece[2] = new SquareImpl(1,1, Square.LIGHT_GREEN);
        piece[3] = new SquareImpl(2,1, Square.LIGHT_GREEN);
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
        piece[0].changeCoordinates(0,0);
        piece[1].changeCoordinates(1,0);
        piece[2].changeCoordinates(1,1);
        piece[3].changeCoordinates(2,1);
        standing = false;
    }

    public void standUp() {
        piece[0].changeCoordinates(2,0);
        piece[1].changeCoordinates(2,1);
        piece[2].changeCoordinates(1,1);
        piece[3].changeCoordinates(1,2);
        standing = true;
    }
}
