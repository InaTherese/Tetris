package com.tetris.game;

public interface Square {
    int BLUE_SQAURE = 1;
    int GREEN_SQAURE = 2;
    int LIGHT_GREEN_SQAURE = 3;
    int ORANGE_SQAURE = 4;
    int PINK_SQAURE = 5;
    int PURPLE_SQAURE = 6;
    int YELLOW_SQAURE = 7;

    void changeCoordinates(int x, int y);
    
    int getX();
    int getY();
}
