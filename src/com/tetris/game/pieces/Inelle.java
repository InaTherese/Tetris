package com.tetris.game.pieces;

import com.tetris.game.Piece;
import com.tetris.game.Square;
import com.tetris.game.SquareImpl;

public class Inelle extends Piece {

    boolean standing = true;
    
    public Inelle(){
        piece[0] = new SquareImpl(1,0, Square.BLUE);
        piece[1] = new SquareImpl(1,1, Square.BLUE);
        piece[2] = new SquareImpl(1,2, Square.BLUE);
        piece[3] = new SquareImpl(1,3, Square.BLUE);
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
        piece[2].changeCoordinates(2,1);
        piece[3].changeCoordinates(3,1);
        standing = false;
    }

    public void standUp() {
        piece[0].changeCoordinates(2, 0);
        piece[1].changeCoordinates(2, 1);
        piece[2].changeCoordinates(2, 2);
        piece[3].changeCoordinates(2, 3);
        standing = true;
    }
}
