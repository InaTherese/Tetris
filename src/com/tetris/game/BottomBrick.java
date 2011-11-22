package com.tetris.game;

import com.tetris.gui.TetrisView;

import java.util.ArrayList;
import java.util.Collections;

public class BottomBrick implements Bottom {

    ArrayList<Square> bottomGrid = new ArrayList<Square>();

    private int numberOfRemovedLines = 0;
    private int numberOfCombos = 1;
    private long timeOfLastLine = System.currentTimeMillis();
    private long comboThreshold = 12000;

    public int getNumberOfCombos() {
        return numberOfCombos;
    }

    public long getComboThreshold() {
        return comboThreshold;
    }

    public long getTimeLeftOfCombo() {
        long timeSince = System.currentTimeMillis()-timeOfLastLine;
        return comboThreshold-timeSince;
    }

    public void setComboThreshold(long comboThreshold) {
        this.comboThreshold = comboThreshold;
    }

    public int getNumberOfRemovedLines() {
        return numberOfRemovedLines;
    }

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

    public void commitPieceToBottom(Piece piece) {
        Collections.addAll(bottomGrid, piece.getSquaresWithGlobalCoordinates());
        checkRowsForPoints();
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
    
    private void checkRowsForPoints(){
        for (int i=0;i<TetrisView.BOARD_HEIGHT;i++){
            if (hasFullLineAtRow(i))
                removeLine(i);
        }
    }

    private void removeLine(int i) {
        ArrayList<Square> squares = new ArrayList<Square>();
        for (Square s : bottomGrid){
            if (s.getY()==i)
                squares.add(s);
        }
        bottomGrid.removeAll(squares);
        applyGravity(i);
        giveScore();
    }

    private void giveScore() {
        numberOfRemovedLines++;
        if (System.currentTimeMillis()-comboThreshold < timeOfLastLine){
            numberOfCombos++;
        } else {
            numberOfCombos = 1;
        }
        timeOfLastLine = System.currentTimeMillis();
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
