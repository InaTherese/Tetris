package com.tetris.gui;

import com.tetris.R;

import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.content.Intent;

public class Menu extends Tetris {
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.menu);
	    }
	 
	 /*
	 
	 Button play = (Button) findViewById(R.id.Button01);
     play.setOnClickListener(new View.OnClickListener() {
         public void onClick(View view) {
             Intent myIntent = new Intent(view.getContext(), EE.class);
             startActivityForResult(myIntent, 0);
         }

     });
     */
}
