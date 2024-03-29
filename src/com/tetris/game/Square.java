package com.tetris.game;

import java.io.Serializable;

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
    void setX(int x);
    void setY(int y);

    int getColor();
    void makeCoordinatesGlobal(int xOffset, int yOffset);
    
    Square duplicate();
}
