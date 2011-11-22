package com.tetris.multiplayer.client;

import android.app.Activity;
import android.os.Bundle;
import com.tetris.R;

public class ClientActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        new Client().start();
    }
}