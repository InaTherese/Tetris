package com.tetris.game.pieces;

import com.tetris.game.Piece;
import com.tetris.game.Square;
import com.tetris.game.SquareImpl;

public class Tipsy extends Piece {

	int position;

	public Tipsy() {
		piece[0] = new SquareImpl(1, 0, Square.PURPLE);
		piece[1] = new SquareImpl(0, 1, Square.PURPLE);
		piece[2] = new SquareImpl(1, 1, Square.PURPLE);
		piece[3] = new SquareImpl(2, 1, Square.PURPLE);
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
		piece[0].changeCoordinates(1, 0);
		piece[1].changeCoordinates(0, 1);
		piece[2].changeCoordinates(1, 1);
		piece[3].changeCoordinates(2, 1);
		position = 1;
	}

	public void positionTwo() {
		piece[0].changeCoordinates(1, 0);
		piece[1].changeCoordinates(1, 2);
		piece[2].changeCoordinates(1, 1);
		piece[3].changeCoordinates(2, 1);
		position = 2;
	}

	public void positionThree() {
		piece[0].changeCoordinates(0, 1);
		piece[1].changeCoordinates(1, 1);
		piece[2].changeCoordinates(2, 1);
		piece[3].changeCoordinates(1, 2);
		position = 3;
	}

	public void positionFour() {
		piece[0].changeCoordinates(1, 0);
		piece[1].changeCoordinates(1, 1);
		piece[2].changeCoordinates(1, 2);
		piece[3].changeCoordinates(0, 1);
		position = 4;
	}
}
