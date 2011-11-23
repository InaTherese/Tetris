package com.tetris.game;

import java.util.ArrayList;
import java.util.Collections;

public class GameControllerImpl implements GameController {

    Piece currentPiece;
    Piece nextPiece;
    Bottom bottomBricks;
    Score score;
    private int moveDelay = 1000;

    public GameControllerImpl() {
        score = new ScoreImpl();
        bottomBricks = new BottomBrick();
        generateNewPiece();
        generateNewPiece();
    }

    private void generateNewPiece() {
        currentPiece = nextPiece;
        nextPiece = PieceFactory.generateRandomPiece();
    }

    private void increaseDifficulty() {
        if (1000-score.getScore() > 0)
            moveDelay=1000-score.getScore();
    }


    public void rotatePiece() {
        if (unlessRotationWillCollide())
            currentPiece.rotate();
    }

    private boolean unlessRotationWillCollide() {
        currentPiece.rotate();
        boolean willNotCollide = !willCollide(0, 0) && currentPiece.withinBounds(0, 0);
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

    public void movePieceToBottom() {
        while (!willCollide(0, 1)) {
            movePieceDown();
        }
    }

    public int movePieceDown() {
        if (willCollide(0, 1)) {
            score.addPoints(bottomBricks.commitPieceToBottom(currentPiece));
            increaseDifficulty();
            generateNewPiece();
            if (willCollide(0, 0)) {
                return GameController.LOSE;
            }
            return GameController.RUNNING;
        } else {
            currentPiece.moveDown();
        }
        return GameController.RUNNING;
    }

    public boolean willCollide(int x, int y) {
        Square[] squares = currentPiece.getSquaresWithGlobalCoordinates();
        for (Square s : squares) {
            if (bottomBricks.hasPieceAt(s.getX() + x, s.getY() + y))
                return true;
        }
        return false;
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

    public ArrayList<Square> getNextPieceReadyToDraw() {
        ArrayList<Square> squares = new ArrayList<Square>();
        Collections.addAll(squares, nextPiece.getSquaresWithGlobalCoordinates());
        return squares;
    }

    public int getMultiplier() {
        return score.getMultiplier();
    }

    public int getRemainingTimeOfCombo() {
        return (int) (score.getRemainingTimeOfCombo()/1000);
    }

    public int getScore(){
        return score.getScore();
    }
}
