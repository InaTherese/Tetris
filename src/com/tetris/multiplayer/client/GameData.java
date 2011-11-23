package com.tetris.multiplayer.client;

import android.util.Log;
import com.tetris.game.Square;

import java.util.ArrayList;

public class GameData {
    static ArrayList<Square> board = new ArrayList<Square>();
    static ArrayList<Square> next = new ArrayList<Square>();
    static String score = "0";
    static String bonus = "0s; 1x";
    private static boolean drawn = true;

    public static boolean isDrawn() {
        return drawn;
    }

    public static ArrayList<Square> getBoard() {
        return board;
    }

    public static void setBoard(ArrayList<Square> board) {
        GameData.board = board;
    }

    public static ArrayList<Square> getNext() {
        return next;
    }

    public static void setNext(ArrayList<Square> next) {
        GameData.next = next;
    }

    public static String getScore() {
        Log.e("GetScore: ", score);
        return score;
    }

    public static void setScore(String score) {
        GameData.score = score;
        Log.e("SetScore: ", score);
    }

    public static String getBonus() {
        return bonus;
    }

    public static void setBonus(String bonus) {
        GameData.bonus = bonus;
    }

    public static void setDrawn(boolean newDrawn) {
        drawn = newDrawn;
    }
}
