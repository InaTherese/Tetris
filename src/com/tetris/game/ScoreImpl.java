package com.tetris.game;

public class ScoreImpl implements Score {
    private long comboThreshold = 12000;
    private long timeOfLastLine = System.currentTimeMillis();
    private int score = 0;
    private int removedLines = 0;

    public long getRemainingTimeOfCombo() {
        long timeSince = System.currentTimeMillis() - timeOfLastLine;
        return comboThreshold - timeSince > 0 ? comboThreshold - timeSince : 0;
    }

    public void setComboThreshold(long comboThreshold) {
        this.comboThreshold = comboThreshold;
    }

    public long getComboThreshold() {
        return comboThreshold;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void addPoints(int points) {
        this.removedLines += points;
        score += points*getMultiplier();
        if (points>0)
            timeOfLastLine = System.currentTimeMillis();
    }

    public int getMultiplier() {
        if (getRemainingTimeOfCombo() <=0)
            removedLines=0;
        return removedLines+1;
    }
}
