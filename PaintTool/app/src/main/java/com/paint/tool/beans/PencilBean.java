package com.paint.tool.beans;

import android.graphics.Paint;
import android.graphics.Point;
import java.util.ArrayList;

public class PencilBean {
    ArrayList<Point> points;
    Paint paint;
    private float strokeWidth=4;

    public ArrayList<Point> getPoints() {
        return points;
    }
    public void setPoints(ArrayList<Point> points) {
        this.points = points;
    }
    public Paint getPaint() {
        return paint;
    }
    public void setPaint(Paint paint) {
        paint.setStrokeWidth(strokeWidth);
        this.paint = paint;
    }
}
