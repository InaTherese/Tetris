package com.tetris.game;

import com.tetris.gui.TetrisView;

import java.util.ArrayList;
import java.util.Collections;

public class BottomBrick implements Bottom {

    ArrayList<Square> bottomGrid = new ArrayList<Square>();

    public boolean hasPieceAt(int x, int y) {
        boolean hasPieceAt = false;
        if (y == TetrisView.BOARD_HEIGHT-1) {
            hasPieceAt = true;
        } else {
            for (Square s : bottomGrid) {
                if (s.getX() == x && s.getY() == y)
                    hasPieceAt = true;
            }
        }
        return hasPieceAt;
    }

    public void commitPieceToBottom(Piece piece) {
        Collections.addAll(bottomGrid, piece.getSquaresWithGlobalCoordinates());
    }

    public ArrayList<Square> getBottomGrid() {
        return bottomGrid;
    }
}
