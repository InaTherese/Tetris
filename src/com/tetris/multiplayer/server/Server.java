package com.tetris.multiplayer.server;

import android.util.Log;
import com.tetris.game.Square;
import com.tetris.gui.TetrisViewController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {
    private final static String TAG = "ServerThread";
    private final static int PORT = 12345;
    private static TetrisViewController controller;

    public Server(TetrisViewController viewController) {
        controller = viewController;
    }

    public void run() {
        ServerSocket ss = null;
        Socket s = null;
        PrintWriter out = null;
        BufferedReader in = null;

        try {
            Log.i(TAG, "start server....");
            ss = new ServerSocket(PORT);
            Log.i(TAG, "serversocket created, wait for client....");
            s = ss.accept();
            Log.v(TAG, "client connected...");
            out = new PrintWriter(s.getOutputStream(), true);
            in = new BufferedReader(
                    new InputStreamReader(s.getInputStream()));
            while (!controller.gameOver()) {
                for (Square square : controller.getSquaresForBoard()) {
                    out.println(square.toString());
                }
                for (Square square : controller.getSquaresForPreview()) {
                    out.println("next:" + square.toString());
                }
                out.println(controller.getScoreAsString());
                out.println(controller.getComboBoardAsString());

                out.println("stop");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {//close sockets!!
            try {
                out.close();
                in.close();
                s.close();
                ss.close();
            } catch (Exception e) {
            }
        }
    }
}
