package com.tetris.gui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import com.tetris.R;

public class Splash extends Activity {

	private final int SPLASH_DISPLAY_LENGHT = 5000;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setContentView(R.layout.splashscreen);

		new Handler().postDelayed(new Runnable() {
			//@Override
			public void run() {
				Intent mainIntent = new Intent(Splash.this, Menu.class);
				Splash.this.startActivity(mainIntent);
				Splash.this.finish();
			}

		}, SPLASH_DISPLAY_LENGHT);
	}
}
