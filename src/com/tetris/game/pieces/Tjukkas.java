package com.tetris.game.pieces;

import com.tetris.game.Piece;
import com.tetris.game.Square;
import com.tetris.game.SquareImpl;

public class Tjukkas extends Piece {

    boolean standing = true;
    
    public Tjukkas(){
        piece[0] = new SquareImpl(0,0, Square.YELLOW);
        piece[1] = new SquareImpl(0,1, Square.YELLOW);
        piece[2] = new SquareImpl(1,0, Square.YELLOW);
        piece[3] = new SquareImpl(1,1, Square.YELLOW);
    }
    
    @Override
    public void rotate() {
    }
}
