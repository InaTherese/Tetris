package com.tetris.game;

import com.tetris.game.pieces.Laban;
//import com.tetris.game.pieces.Elle;

public class GameControllerImpl implements GameController{

    Piece currentPiece;
    Bottom bottomBricks;
    
    public GameControllerImpl(){
        generateStartingPiece();
        bottomBricks = new BottomBrick();
    }

    private void generateStartingPiece() {
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
        currentPiece.moveDown();
    }

    public int getScore() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public int moveDelay() {
        return 1000;
    }

    public Square[] getSquaresReadyToDraw() {
        return currentPiece.getSquaresWithGlobalCoordinates();
    }
}
