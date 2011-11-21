package com.tetris.game;

import com.tetris.game.pieces.Laban;

public class PieceFactory {
    public enum PieceKind {
        LABAN, TJUKKAS, TIPSY, ELLE, INELLE, KNOLL, TOTT
    }

    public static Piece generateDesiredPiece(PieceKind piece){
        Piece newPiece = null;
        switch (piece){
            case LABAN: newPiece = new Laban();break;
            case TJUKKAS: newPiece = new Laban();break;
            case TIPSY: newPiece = new Laban();break;
            case ELLE: newPiece = new Laban();break;
            case INELLE: newPiece = new Laban();break;
            case KNOLL: newPiece = new Laban();break;
            case TOTT: newPiece = new Laban();break;
        }
        return newPiece;
    }
}
