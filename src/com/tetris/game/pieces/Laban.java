package com.tetris.game.pieces;

import com.tetris.game.Piece;
import com.tetris.game.Square;
import com.tetris.game.SquareImpl;

public class Laban extends Piece {

    Square[] piece = new Square[4];
    
    boolean standing = true;
    
    public Laban(){
        piece[0] = new SquareImpl(0,0);
        piece[1] = new SquareImpl(0,1);
        piece[2] = new SquareImpl(0,2);
        piece[3] = new SquareImpl(0,3);
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
        piece[2].changeCoordinates(2,0);
        piece[3].changeCoordinates(3,0);
    }

    public void standUp() {
        piece[0].changeCoordinates(0, 0);
        piece[1].changeCoordinates(0, 1);
        piece[2].changeCoordinates(0, 2);
        piece[3].changeCoordinates(0, 3);
    }
}
