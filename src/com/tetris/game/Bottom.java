package com.tetris.game;

import java.util.ArrayList;

public interface Bottom {
    boolean hasPieceAt(int x, int y);
    void commitPieceToBottom(Piece piece);
    ArrayList<Square> getBottomGrid();
}
