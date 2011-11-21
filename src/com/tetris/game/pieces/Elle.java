package com.tetris.game.pieces;

import com.tetris.game.Piece;
import com.tetris.game.Square;
import com.tetris.game.SquareImpl;

public class Elle extends Piece {

	int position;

	public Elle() {
		piece[0] = new SquareImpl(1, 0, Square.GREEN);
		piece[1] = new SquareImpl(1, 1, Square.GREEN);
		piece[2] = new SquareImpl(1, 2, Square.GREEN);
		piece[3] = new SquareImpl(2, 2, Square.GREEN);
		position = 1;
	}

	@Override
	public void rotate() {
		if (position==1) {
			positionTwo();
		} else if(position==2){
			positionThree();
		} else if (position==3){
			positionFour();
		} else {
			positionOne();
		}
	}

	private void positionOne() {
		piece[0].changeCoordinates(1, 0);
		piece[1].changeCoordinates(1, 1);
		piece[2].changeCoordinates(1, 2);
		piece[3].changeCoordinates(2, 2);
		position=1;
	}

	private void positionTwo() {
		piece[0].changeCoordinates(1, 2);
		piece[1].changeCoordinates(1, 1);
		piece[2].changeCoordinates(2, 1);
		piece[3].changeCoordinates(3, 1);
		position=2;
	}

	private void positionThree() {
		piece[0].changeCoordinates(1, 0);
		piece[1].changeCoordinates(2, 0);
		piece[2].changeCoordinates(2, 1);
		piece[3].changeCoordinates(2, 2);
		position=3;
	}

	private void positionFour() {
		piece[0].changeCoordinates(1, 2);
		piece[1].changeCoordinates(2, 2);
		piece[2].changeCoordinates(3, 2);
		piece[3].changeCoordinates(3, 1);
		position=4;
	}
}
