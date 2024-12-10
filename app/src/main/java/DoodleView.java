package com.example.ciscx82doodler2024;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import java.util.Stack;

public class DoodleView extends View {
    private Paint paint;
    private Path path;
    private int brushColor = 0xFF000000; // Default: Black
    private float brushSize = 10f;
    private Stack<Path> paths = new Stack<>();
    private Stack<Path> undonePaths = new Stack<>();

    public DoodleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(brushColor);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(brushSize);
        paint.setAntiAlias(true);

        path = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // Draw all paths
        for (Path p : paths) {
            canvas.drawPath(p, paint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                path.moveTo(x, y);
                break;
            case MotionEvent.ACTION_MOVE:
                path.lineTo(x, y);
                break;
            case MotionEvent.ACTION_UP:
                paths.push(path);
                path = new Path(); // Create a new path for next stroke
                break;
        }

        invalidate(); // Redraw the view
        return true;
    }

    public void clearCanvas() {
        paths.clear();
        undonePaths.clear();
        invalidate();
    }

    public void setBrushColor(int color) {
        brushColor = color;
        paint.setColor(brushColor);
    }

    public void increaseBrushSize() {
        brushSize += 5f;
        paint.setStrokeWidth(brushSize);
    }

    public void decreaseBrushSize() {
        brushSize = Math.max(5f, brushSize - 5f); // Prevent brush size from becoming too small
        paint.setStrokeWidth(brushSize);
    }

    public void undo() {
        if (!paths.isEmpty()) {
            undonePaths.push(paths.pop()); // Move last path to redo stack
            invalidate(); // Redraw the view
        }
    }

    public void redo() {
        if (!undonePaths.isEmpty()) {
            paths.push(undonePaths.pop()); // Move path back to drawn paths
            invalidate(); // Redraw the view
        }
    }
}
