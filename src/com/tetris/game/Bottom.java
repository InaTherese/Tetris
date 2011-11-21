package com.tetris.game;

import java.util.ArrayList;

public interface Bottom {
    boolean hasPieceAt(int x, int y);
    int commitPieceToBottom(Piece piece);
    ArrayList<Square> getBottomGrid();
}
