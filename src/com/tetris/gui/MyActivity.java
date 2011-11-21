package com.tetris.gui;

import android.app.Activity;
import android.gesture.*;
import android.os.Bundle;
import com.tetris.R;

import java.util.ArrayList;

public class MyActivity extends Activity implements GestureOverlayView.OnGesturePerformedListener {
    private GestureLibrary mLibrary;
    private TetrisController controller;
    private boolean activeGame = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mLibrary = GestureLibraries.fromRawResource(this, R.raw.gestures);
        if (!mLibrary.load()) {
            finish();
        }

        GestureOverlayView gestures = (GestureOverlayView) findViewById(R.id.gestures);
        gestures.addOnGesturePerformedListener(this);

        controller = (TetrisController) findViewById(R.id.tetris);
    }

    public void onGesturePerformed(GestureOverlayView gestureOverlayView, Gesture gesture) {
        if (!activeGame) {
            controller.newGame();
            activeGame = true;
        }
        ArrayList<Prediction> predictions = mLibrary.recognize(gesture);
        if (predictions.size() > 0 && predictions.get(0).score > 1.0) {
            String action = predictions.get(0).name;
            if ("tap_center".equals(action)) {
                controller.rotate();
            } else if ("tap_left".equals(action)) {
                controller.left();
            } else if ("tap_right".equals(action)) {
                controller.right();
            } else if ("swipe_left".equals(action)) {
                controller.left();
            } else if ("swipe_right".equals(action)) {
                controller.right();
            }
        }
    }
}
