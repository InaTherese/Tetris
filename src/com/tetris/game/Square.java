package com.tetris.game;

public interface Square {
    int BLUE = 1;
    int GREEN = 2;
    int LIGHT_GREEN = 3;
    int ORANGE = 4;
    int PINK = 5;
    int PURPLE = 6;
    int YELLOW = 7;

    void changeCoordinates(int x, int y);
    
    int getX();
    int getY();

    int getColor();
    void makeCoordinatesGlobal(int xOffset, int yOffset);
    
    Square duplicate();
}
