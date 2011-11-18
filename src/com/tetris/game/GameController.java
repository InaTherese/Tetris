package com.tetris.game;

public interface GameController {
    int PAUSE = 0;
    int READY = 1;
    int RUNNING = 2;
    int LOSE = 3;

    void rotatePiece();
    void movePieceLeft();
    void movePieceRight();
    void movePieceDown();

    int getScore();
    int moveDelay();
    
    Square[] getSquaresToDraw();
}
