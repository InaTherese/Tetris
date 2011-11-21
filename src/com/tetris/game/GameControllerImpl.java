package com.tetris.game;

import com.tetris.game.pieces.Laban;

import java.util.ArrayList;
import java.util.Collections;

public class GameControllerImpl implements GameController{

    Piece currentPiece;
    Bottom bottomBricks;
    
    public GameControllerImpl(){
        generateNewPiece();
        bottomBricks = new BottomBrick();
    }

    private void generateNewPiece() {
        currentPiece = new Laban();
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
        boolean willHitBottom;
        Square[] squares = currentPiece.getSquaresWithGlobalCoordinates();
        for (Square s : squares){
            if (bottomBricks.hasPieceAt(s.getX()+x,s.getY()+y))
                return true;
        }
        return false;
    }

    public int getScore() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public int moveDelay() {
        return 1000;
    }

    public ArrayList<Square> getSquaresReadyToDraw() {
        ArrayList<Square> squares = new ArrayList<Square>();
        Collections.addAll(squares, currentPiece.getSquaresWithGlobalCoordinates());
        squares.addAll(bottomBricks.getBottomGrid());
        return squares;
    }
}
