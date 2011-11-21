package com.tetris.game;

import java.util.ArrayList;
import java.util.Collections;

public class GameControllerImpl implements GameController{

    Piece currentPiece;
    Piece nextPiece;
    Bottom bottomBricks;
    private int moveDelay = 1000;

    public GameControllerImpl(){
        generateNewPiece();
        generateNewPiece();
        bottomBricks = new BottomBrick();
    }

    private void generateNewPiece() {
    	currentPiece = nextPiece;
        increaseDifficulty();
    	nextPiece = PieceFactory.generateRandomPiece();
    }

    private void increaseDifficulty() {
        if (moveDelay()>0)
            moveDelay--;
    }

    public void rotatePiece() {
        if (unlessRotationWillCollide())
            currentPiece.rotate();
    }

    private boolean unlessRotationWillCollide() {
        currentPiece.rotate();
        boolean willNotCollide = !willCollide(0,0) && currentPiece.withinBounds(0,0);
        currentPiece.rotate();
        currentPiece.rotate();
        currentPiece.rotate();
        return willNotCollide;
    }

    public void movePieceLeft() {
        if (!willCollide(-1, 0))
            currentPiece.moveLeft();
    }

    public void movePieceRight() {
        if (!willCollide(1, 0))
            currentPiece.moveRight();
    }

    public void movePieceDown() {
        if (willCollide(0, 1)) {
            bottomBricks.commitPieceToBottom(currentPiece);
            generateNewPiece();
        } else {
            currentPiece.moveDown();
        }
    }

    private boolean willCollide(int x, int y) {
        Square[] squares = currentPiece.getSquaresWithGlobalCoordinates();
        for (Square s : squares){
            if (bottomBricks.hasPieceAt(s.getX()+x,s.getY()+y))
                return true;
        }
        return false;
    }

    public int getScore() {
        int scoreMultiplier = 1000/moveDelay();
        return bottomBricks.getNumberOfRemovedLines()*scoreMultiplier;
    }

    public int moveDelay() {
        return moveDelay;
    }

    public ArrayList<Square> getSquaresReadyToDraw() {
        ArrayList<Square> squares = new ArrayList<Square>();
        Collections.addAll(squares, currentPiece.getSquaresWithGlobalCoordinates());
        squares.addAll(bottomBricks.getBottomGrid());
        return squares;
    }
}
