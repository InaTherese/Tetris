package com.tetris.game;

public interface Score {
    int getMultiplier();

    long getRemainingTimeOfCombo();

    int getScore();

    void addPoints(int lines);
}
