package com.tetris.game;

import com.tetris.gui.TetrisView;

import java.util.ArrayList;
import java.util.Collections;

public class BottomBrick implements Bottom {

    ArrayList<Square> bottomGrid = new ArrayList<Square>();

    public boolean hasPieceAt(int x, int y) {
        boolean hasPieceAt = false;
        if (y == TetrisView.BOARD_HEIGHT) {
            hasPieceAt = true;
        } else {
            for (Square s : bottomGrid) {
                if (s.getX() == x && s.getY() == y)
                    hasPieceAt = true;
            }
        }
        return hasPieceAt;
    }

    public int commitPieceToBottom(Piece piece) {
        Collections.addAll(bottomGrid, piece.getSquaresWithGlobalCoordinates());
        return checkRowsForPoints();
    }

    private void applyGravity(int i) {
        for (Square s : bottomGrid){
            if (s.getY()<i)
                s.setY(s.getY()+1);
        }
    }

    public ArrayList<Square> getBottomGrid() {
        return bottomGrid;
    }
    
    private int checkRowsForPoints(){
        int score = 0;
        for (int i=0;i<TetrisView.BOARD_HEIGHT;i++){
            if (hasFullLineAtRow(i))
                removeLine(i);
                score++;
        }
        return score;
    }

    private void removeLine(int i) {
        ArrayList<Square> squares = new ArrayList<Square>();
        for (Square s : bottomGrid){
            if (s.getY()==i)
                squares.add(s);
        }
        bottomGrid.removeAll(squares);
        applyGravity(i);
    }

    private boolean hasFullLineAtRow(int row){
        int numberOfSquares = 0;
        for (Square s : bottomGrid){
            if (s.getY()==row)
                numberOfSquares++;
        }
        return numberOfSquares==TetrisView.BOARD_WIDTH;
    }
}
