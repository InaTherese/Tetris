package com.tetris.game.pieces;

import com.tetris.game.Piece;
import com.tetris.game.Square;
import com.tetris.game.SquareImpl;

public class Inelle extends Piece {

    int position;
    
    public Inelle(){
        piece[0] = new SquareImpl(2,0, Square.PINK);
        piece[1] = new SquareImpl(2,1, Square.PINK);
        piece[2] = new SquareImpl(2,2, Square.PINK);
        piece[3] = new SquareImpl(1,2, Square.PINK);
        position = 1;
    }
    
	@Override
	public void rotate() {
		switch (position) {
		case 1: positionTwo();
			break;
		case 2: positionThree();
			break;
		case 3: positionFour();
			break;
		case 4: positionOne();
			break;
		}
	}

	private void positionOne() {
		piece[0].changeCoordinates(2, 0);
		piece[1].changeCoordinates(2, 1);
		piece[2].changeCoordinates(2, 2);
		piece[3].changeCoordinates(1, 2);
		position=1;
	}

	private void positionTwo() {
		piece[0].changeCoordinates(1, 1);
		piece[1].changeCoordinates(2, 1);
		piece[2].changeCoordinates(2, 2);
		piece[3].changeCoordinates(3, 2);
		position=2;
	}

	private void positionThree() {
		piece[0].changeCoordinates(1, 0);
		piece[1].changeCoordinates(1, 1);
		piece[2].changeCoordinates(1, 2);
		piece[3].changeCoordinates(2, 1);
		position=3;
	}

	private void positionFour() {
		piece[0].changeCoordinates(1, 1);
		piece[1].changeCoordinates(2, 1);
		piece[2].changeCoordinates(3, 1);
		piece[3].changeCoordinates(3, 2);
		position=4;
	}
}
