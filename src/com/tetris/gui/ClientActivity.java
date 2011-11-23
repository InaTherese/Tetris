package com.tetris.gui;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import com.tetris.R;
import com.tetris.game.Square;
import com.tetris.multiplayer.client.Client;

import java.util.ArrayList;

public class ClientActivity extends Activity {
    TetrisView board;
    TinyTetrisView preview;
    TextView comboBoard;
    TextView scoreBoard;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        board = (TetrisView) findViewById(R.id.tetris);
        preview = (TinyTetrisView) findViewById(R.id.next_piece);
        scoreBoard = (TextView) findViewById(R.id.score);
        comboBoard = (TextView) findViewById(R.id.combo);
        new Client(this).start();
    }

    public void setScore(String score) {
        scoreBoard.setText(score);
    }

    public void setComboBoard(String combo) {
        comboBoard.setText(combo);
    }

    public void drawTetris(ArrayList<Square> pieceSquares) {
        board.redrawScreen(pieceSquares);
    }

    public void drawNext(ArrayList<Square> pieceSquares) {
        preview.redrawScreen(pieceSquares);
    }
}