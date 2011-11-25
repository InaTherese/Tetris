package com.tetris.multiplayer.client;

import android.util.Log;
import com.tetris.game.Square;
import com.tetris.game.SquareImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
  
public class Client extends Thread {   
	private final static String TAG = "Client";
	private final static String IP = "10.0.2.2";
	private final static int PORT = 12345;

    public void run() {
    	Socket s 			= null;
    	PrintWriter out		= null;
    	BufferedReader in 	= null;

        try {
            s = new Socket(IP, PORT);
            Log.v(TAG, "C: Connected to server" + s.toString());
            out = new PrintWriter(s.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            ArrayList<Square> squares = new ArrayList<Square>();
            ArrayList<Square> nextSquares = new ArrayList<Square>();
            String score = "0";
            String combo = "0s; 1x";
            String res;
            while((res = in.readLine()) != null) {
                // Parse
                // coord x : coord y : color
                // x:y:c
                // score : value
                // combo : value
                // next : coord x : coord y : color
                // stop
            	if(res.equals("stop")) {
            		ServerGameProxy.setBoard(squares);
            		ServerGameProxy.setNext(nextSquares);
            		ServerGameProxy.setScore(score);
            		ServerGameProxy.setBonus(combo);
                    squares = new ArrayList<Square>();
                    nextSquares = new ArrayList<Square>();
                    ServerGameProxy.setDrawn(false);
            	} else {
	            	String[] spl = res.split(":");
	            	if(spl[0].equals("score")) {
	            		score = spl[1];
	            	} else if(spl[0].equals("combo")) {
	            		combo = spl[1];
	            	} else if(spl[0].equals("next")) {
	            		// make nextPiece
	            		int x = Integer.parseInt(spl[1]);
	            		int y = Integer.parseInt(spl[2]);
	            		int color = Integer.parseInt(spl[3]);
	            		nextSquares.add(new SquareImpl(x, y, color));
	            	} else {
	            		// add square
	            		int x = Integer.parseInt(spl[0]);
	            		int y = Integer.parseInt(spl[1]);
	            		int color = Integer.parseInt(spl[2]);
	            		squares.add(new SquareImpl(x, y, color));
	            	}
            	}
            	Log.i(TAG,res);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally{//close socket!!
        	try{
	        	out.close();
	        	in.close();
	        	s.close();
        	}catch(IOException e){}
            catch (NullPointerException n){}
        }
    }
}