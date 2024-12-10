package com.example.ciscx82doodler2024;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private com.example.ciscx82doodler2024.DoodleView doodleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize DoodleView
        doodleView = findViewById(R.id.doodle_view);

        // Tool Panel Buttons
        Button clearButton = findViewById(R.id.clear_button);
        Button redButton = findViewById(R.id.red_button);
        Button blueButton = findViewById(R.id.blue_button);
        Button increaseBrushButton = findViewById(R.id.increase_brush_button);
        Button decreaseBrushButton = findViewById(R.id.decrease_brush_button);

        // Undo and Redo Buttons
        Button undoButton = findViewById(R.id.undo_button);
        Button redoButton = findViewById(R.id.redo_button);

        // Clear Canvas
        clearButton.setOnClickListener(v -> {
            if (doodleView != null) {
                doodleView.clearCanvas();
            }
        });

        // Change Brush Color
        redButton.setOnClickListener(v -> {
            if (doodleView != null) {
                doodleView.setBrushColor(Color.RED);
            }
        });

        blueButton.setOnClickListener(v -> {
            if (doodleView != null) {
                doodleView.setBrushColor(Color.BLUE);
            }
        });

        // Change Brush Size
        increaseBrushButton.setOnClickListener(v -> {
            if (doodleView != null) {
                doodleView.increaseBrushSize();
            }
        });

        decreaseBrushButton.setOnClickListener(v -> {
            if (doodleView != null) {
                doodleView.decreaseBrushSize();
            }
        });

        // Undo Button
        undoButton.setOnClickListener(v -> {
            if (doodleView != null) {
                doodleView.undo();
            }
        });

        // Redo Button
        redoButton.setOnClickListener(v -> {
            if (doodleView != null) {
                doodleView.redo();
            }
        });
    }
}
