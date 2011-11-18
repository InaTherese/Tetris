package com.tetris.game;

/**
 * Created by IntelliJ IDEA.
 * User: christian
 * Date: 18.11.11
 * Time: 13:50
 * To change this template use File | Settings | File Templates.
 */
public class BottomBrick implements Bottom {
    
    Square[][] bottomGrid = new Square[20][10];

    public boolean hasPieceAt(int x, int y) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
