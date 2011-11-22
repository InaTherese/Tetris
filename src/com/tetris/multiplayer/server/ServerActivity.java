package com.tetris.multiplayer.server;

import android.app.Activity;
import android.os.Bundle;
import com.tetris.R;

public class ServerActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        new Server().start();//start server
    }
}