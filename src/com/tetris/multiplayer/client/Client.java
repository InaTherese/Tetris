package com.tetris.multiplayer.client;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
  
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
            
            while(in.ready()) {
            	// Parse
            	// coord x : coord y : color
            	// score : value
            	// next : coord x : coord y : color
            	// stop
            	String res = in.readLine();
            	if(res.equals("stop")) {
            		// draw and flush
            	} else {
	            	String[] spl = res.split(":");
	            	if(spl[0].equals("score")) {
	            		// update score
	            	} else if(spl[0].equals("next")) {
	            		// make nextPiece
	            	} else {
	            		// add square
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