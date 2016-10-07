package com.paint.tool.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Stack;

// View to handle line , pencil, rect , circle
public class DrawingTool extends View {

    private final ImageView imv;
    public static int maxId = 0;

    ArrayList<Integer> operations = new ArrayList<Integer>();
    private int currentId = 0;

    public Stack<Integer> getRedoUndoOperations() {
        return redoUndoOperations;
    }

    public void setRedoUndoOperations(Stack<Integer> redoUndoOperations) {
        this.redoUndoOperations = redoUndoOperations;
    }

    Stack<Integer> redoUndoOperations = new Stack<Integer>();

    public ArrayList<ArrayList> getAllDrawing() {
        return allDrawing;
    }

    public void setAllDrawing(ArrayList<ArrayList> allDrawing) {
        this.allDrawing = allDrawing;
    }

    ArrayList<ArrayList> allDrawing = new ArrayList<ArrayList>();
    Stack<ArrayList> redoUndoPencil = new Stack<ArrayList>();
    ArrayList<Point> pointList = new ArrayList<>();
    ArrayList<Integer> rotations = new ArrayList<Integer>();
    Stack<Integer> redoUndoRotations = new Stack<Integer>();
    ArrayList<Integer> colors = new ArrayList<Integer>();
    Stack<Integer> redoUndoColors = new Stack<Integer>();

    private float degree;
    int x1, x2, y1, y2;

    public ArrayList<Integer> getColors() {
        return colors;
    }

    public void setColors(ArrayList<Integer> colors) {
        this.colors = colors;
    }

    public ArrayList<Integer> getRotations() {
        return rotations;
    }

    public void setRotations(ArrayList<Integer> rotations) {
        this.rotations = rotations;
    }

    public ArrayList<Integer> getOperations() {
        return operations;
    }

    public void setOperations(ArrayList<Integer> operations) {
        this.operations = operations;
    }

    public float getDegree() {
        return degree;
    }

    public void setDegree(float degree) {
        this.degree = degree;
    }

    public Stack<ArrayList> getRedoUndoPencil() {
        return redoUndoPencil;
    }

    public void setRedoUndoPencil(Stack<ArrayList> redoUndoPencil) {
        this.redoUndoPencil = redoUndoPencil;
    }

    public void createNewAL() {
        pointList = new ArrayList<Point>();
    }

    public ArrayList<Point> getPointList() {
        return pointList;
    }

    public void setPointList(ArrayList<Point> pointList) {
        this.pointList = pointList;
    }

    public int getX1() {
        return x1;
    }

    public void setX1(int x12) {
        this.x1 = x12;
    }

    public int getX2() {
        return x2;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public int getY1() {
        return y1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public int getY2() {
        return y2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {

        this.color = color;
        paint.setColor(color);
    }

    Paint paint;
    private int color = Color.RED;

    public DrawingTool(Context context, ImageView imv) {
        super(context);
        this.imv = imv;
        paint = new Paint();
        paint.setStrokeWidth(10);
        paint.setColor(color);
    }

    public void onDraw(Canvas c) {
        if (allDrawing != null)
            for (ArrayList<Point> p : allDrawing) {
                if(p != null) {
                    for (int i = 0; i < p.size() - 1; i++) {
                        c.drawLine(p.get(i).x, p.get(i).y, p.get(i + 1).x, p.get(i + 1).y, paint);
                    }
                }
            }
        if (pointList != null)
            for (int i = 0; i < pointList.size() - 1; i++) {
                c.drawLine(pointList.get(i).x, pointList.get(i).y, pointList.get(i + 1).x, pointList.get(i + 1).y, paint);
            }
        invalidate();
    }

    public void addAL(ArrayList<Point> al2) {
        allDrawing.add(al2);
    }

    public void redo() {
        if (redoUndoOperations.size() > 0) {
            int id = redoUndoOperations.pop();
            operations.add(id);
            currentId = id;

           /* switch (id) {
                case 1: {
                    if (redoUndoPencil.size() > 0)
                        allDrawing.add(redoUndoPencil.pop());
                    break;
                }
                case 2: {
                    if (redoUndoRotations.size() > 0) {
                        rotations.add(redoUndoRotations.pop());
                        float val = getDegree() + 90;
                        setDegree(val);
                        imv.setRotation(val);
                        imv.setRotation(val);
                    }
                    break;
                }
                case 3:{
                    if(colors.size() > 0){
                        colors.add(redoUndoColors.pop());
                    }
                    break;
                }
            }*/
        }else{
            currentId = maxId;
        }

    }

    public void undo() {
        if (operations.size() > 0) {
            int id = operations.get(operations.size() - 1);
            redoUndoOperations.push(id);
            operations.remove(operations.size() - 1);

            if(operations.size() > 0){
                currentId = operations.get(operations.size() - 1);
            }else{
                currentId = 0;
            }
            /*switch (id) {
                case 1: {
                    if (allDrawing.size() > 0) {
                        redoUndoPencil.push(allDrawing.get(allDrawing.size() - 1));
                        allDrawing.remove(allDrawing.size() - 1);
                    }
                    break;
                }
                case 2: {
                    if (rotations.size() > 0) {
                        redoUndoRotations.push(rotations.get(rotations.size() - 1));
                        rotations.remove(rotations.size() - 1);
                        float val = getDegree() - 90;
                        setDegree(val);
                        imv.setRotation(val);

                    }
                    break;
                }
                case 3:{
                    if(colors.size() > 0){
                        redoUndoColors.push(colors.get(colors.size() - 1));
                        colors.remove(colors.size() - 1);
                    }
                    break;
                }
            }*/
        }
    }

    public int getCurrentId() {
        return currentId;
    }
}
