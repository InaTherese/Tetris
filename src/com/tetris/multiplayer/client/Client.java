package com.tetris.multiplayer.client;

import android.util.Log;
import com.tetris.gui.ClientActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import com.tetris.game.Square;
import com.tetris.game.SquareImpl;
  
public class Client extends Thread {   
	private final static String TAG = "Client";
	private final static String IP = "10.0.2.2";
	private final static int PORT = 12345;
    private static ClientActivity parent;

    public Client(ClientActivity clientActivity) {
        parent = clientActivity;
    }

    public void run() {
    	Socket s 			= null;
    	PrintWriter out		= null;
    	BufferedReader in 	= null;
    	
        try {
            s = new Socket(IP, PORT);
            Log.v(TAG, "C: Connected to server" + s.toString());
            out = new PrintWriter(s.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            
            while(in.ready()) {
            	// Parse
            	// coord x : coord y : color
            	// score : value
            	// next : coord x : coord y : color
            	// stop
            	String res = in.readLine();
            	ArrayList<Square> squares = new ArrayList<Square>();
            	ArrayList<Square> nextSquares = new ArrayList<Square>();
            	String score;
            	if(res.equals("stop")) {
            		// draw and flush
            	} else {
	            	String[] spl = res.split(":");
	            	if(spl[0].equals("score")) {
	            		score = spl[1];
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
	            		nextSquares.add(new SquareImpl(x, y, color));
	            	}
            	}
            	Log.i(TAG,res);
            }
            out.println("PING to server from client");
        } catch (IOException e) {
            e.printStackTrace();
        }finally{//close socket!!
        	try{
	        	out.close();
	        	in.close();
	        	s.close();
        	}catch(IOException e){}
        }
    }
}