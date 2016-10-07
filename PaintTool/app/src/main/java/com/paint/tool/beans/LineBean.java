package com.paint.tool.beans;

import android.graphics.Paint;
import android.graphics.Point;

import java.util.ArrayList;

public class LineBean {

    ArrayList<Point> points;

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
        paint.setStrokeWidth(4);
        this.paint = paint;
    }
    Paint paint;
}
