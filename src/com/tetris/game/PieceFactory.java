package com.tetris.game;

import com.tetris.game.pieces.*;

public class PieceFactory {
    public enum PieceKind {
        LABAN, TJUKKAS, TIPSY, ELLE, INELLE, KNOLL, TOTT
    }

    public static Piece generateDesiredPiece(PieceKind piece) {
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
}
