package com.tetris.game;

import com.tetris.game.pieces.*;

import java.util.Random;

public class PieceFactory {
	public static final int LABAN = 0;
	public static final int TJUKKAS = 1;
	public static final int TIPSY = 2;
	public static final int ELLE = 3;
	public static final int INELLE = 4;
	public static final int KNOLL = 5;
	public static final int TOTT = 6;
    
    public static Piece generateDesiredPiece(int piece) {
        switch (piece) {
            case LABAN:
                return new Laban();
            case TJUKKAS:
                return new Tjukkas();
            case TIPSY:
                return new Tipsy();
            case ELLE:
                return new Elle();
            case INELLE:
                return new Inelle();
            case KNOLL:
                return new Knoll();
            default:
                return new Tott();
        }
    }
    
    public static Piece generateRandomPiece() {
    	Random r = new Random();
        Piece p = generateDesiredPiece(r.nextInt(7));
        for (int i=0;i<r.nextInt(2);i++){
            p.rotate();
        }
        return p;
    }
}
