package com.tetris.gui;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;
import com.tetris.R;
import com.tetris.game.Square;
import com.tetris.multiplayer.client.Client;
import com.tetris.multiplayer.client.ServerGameProxy;

import java.util.ArrayList;

public class ClientActivity extends Activity {
    TetrisView board;
    TinyTetrisView preview;
    TextView comboBoard;
    TextView scoreBoard;
    private RefreshHandler redrawHandler = new RefreshHandler();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        board = (TetrisView) findViewById(R.id.tetris);
        preview = (TinyTetrisView) findViewById(R.id.next_piece);
        scoreBoard = (TextView) findViewById(R.id.score);
        comboBoard = (TextView) findViewById(R.id.combo);
        new Client().start();
        gameLoop();
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
    public void gameLoop() {
            if (!ServerGameProxy.isDrawn()) {
                setScore(ServerGameProxy.getScore());
                setComboBoard(ServerGameProxy.getBonus());
                drawTetris(ServerGameProxy.getBoard());
                drawNext(ServerGameProxy.getNext());
            }
            ServerGameProxy.setDrawn(true);
            redrawHandler.sleep(10);
        }

    class RefreshHandler extends Handler {

            @Override
            public void handleMessage(Message msg) {
                ClientActivity.this.gameLoop();
                ClientActivity.this.board.invalidate();
                ClientActivity.this.preview.invalidate();
            }

            public void sleep(long delayMillis) {
                this.removeMessages(0);
                sendMessageDelayed(obtainMessage(0), delayMillis);
            }
        }
}