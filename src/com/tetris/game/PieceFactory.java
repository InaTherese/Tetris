package com.tetris.game;

import com.tetris.game.pieces.*;

public class PieceFactory {
    public enum PieceKind {
        LABAN, TJUKKAS, TIPSY, ELLE, INELLE, KNOLL, TOTT
    }

    public static Piece generateDesiredPiece(PieceKind piece){
        Piece newPiece = null;
        switch (piece){
            case LABAN: newPiece = new Laban();break;
            case TJUKKAS: newPiece = new Tjukkas();break;
            case TIPSY: newPiece = new Tipsy();break;
            case ELLE: newPiece = new Elle();break;
            case INELLE: newPiece = new Inelle();break;
            case KNOLL: newPiece = new Knoll();break;
            case TOTT: newPiece = new Tott();break;
        }
        return newPiece;
    }
}
