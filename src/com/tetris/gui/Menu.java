package com.tetris.gui;

import com.tetris.R;

import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.app.Activity;
import android.content.Intent;

public class Menu extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu);

		
		Button play = (Button) findViewById(R.id.Button01);
		play.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				Intent myIntent = new Intent(view.getContext(),
						TetrisActivity.class);
				startActivityForResult(myIntent, 0);
			}
		});
		
		Button look = (Button) findViewById(R.id.Button02);
		look.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				Intent myIntent = new Intent(view.getContext(),
						ClientActivity.class);
				startActivityForResult(myIntent, 0);
			}
		});
	}
}
