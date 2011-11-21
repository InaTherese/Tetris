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
        currentPiece.rotate();
    }

    public void movePieceLeft() {
        currentPiece.moveLeft();
    }

    public void movePieceRight() {
        currentPiece.moveRight();
    }

    public void movePieceDown() {
        if (hasHitBottom()) {
            bottomBricks.commitPieceToBottom(currentPiece);
            generateNewPiece();
        } else {
            currentPiece.moveDown();
        }
    }

    private boolean hasHitBottom() {
        boolean hasHitBottom;
        Square[] squares = currentPiece.getSquaresWithGlobalCoordinates();
        for (Square s : squares){
            if (bottomBricks.hasPieceAt(s.getX(),s.getY()))
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
