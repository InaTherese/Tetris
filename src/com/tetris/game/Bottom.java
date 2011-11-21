package com.tetris.game;

import java.util.ArrayList;

public interface Bottom {
    boolean hasPieceAt(int x, int y);
    void commitPieceToBottom(Piece piece);
    public int getNumberOfRemovedLines();
    ArrayList<Square> getBottomGrid();

    int getNumberOfCombos();
    void setComboThreshold(long comboThreshold);
    long getComboThreshold();
    long getTimeLeftOfCombo();
}
