package com.paint.tool.beans;

import android.graphics.Color;
import android.graphics.Paint;

public class TextBean {
    String value;
    int x;
    int y;
    int textSize = 20;
    Paint paint;
    private boolean isBold;
    private boolean isItalic;
    private String fontName;
    private int style;

    public Paint getPaint() {
        if(paint ==null){
            paint=new Paint();
            paint.setColor(Color.CYAN);
        }
        return paint;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getTextSize() {
        return textSize;
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
    }

    android.text.Layout.Alignment alignment;
   
    public void setAlignment(android.text.Layout.Alignment align){
       alignment=align;
   };

    public void setFont(String fontName){
        this.fontName=fontName;
    };

    public String getFontName() {
        return fontName;
    }

    public void setFontName(String fontName) {
        this.fontName = fontName;
    }

    public android.text.Layout.Alignment getAlignment() {
        return alignment;
    }

    public void setStyle(int style) {
        this.style = style;
    }
}