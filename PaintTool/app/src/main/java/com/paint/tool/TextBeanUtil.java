package com.paint.tool;

import android.graphics.Typeface;

/**
 * Created by Dell on 02/02/2016.
 */
public class TextBeanUtil {

    private static TextBeanUtil textBeanUtil = new TextBeanUtil();
    private TextBeanUtil(){}

    public static TextBeanUtil getInstance(){
        return textBeanUtil;
    }

    boolean leftAlign;
    boolean bold;
    boolean italic;
    boolean underline;
    boolean centerAlign;
    boolean rightAlign;
    int size;
    String fontName;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getFontName() {
        return fontName;
    }

    public void setFontName(String fontName) {
        this.fontName = fontName;
    }

     public void setCenterAlign(boolean centerAlign) {
        this.centerAlign = centerAlign;
    }

    public boolean  isCenterAlign() {
       return centerAlign;
    }

     public boolean isRightAlign() {
        return rightAlign;
    }

    public void setRightAlign(boolean rightAlign) {
        this.rightAlign = rightAlign;
    }

    public boolean isLeftAlign() {
        return leftAlign;
    }

    public void setLeftAlign(boolean leftAlign) {
        this.leftAlign = leftAlign;
    }

    public boolean isBold() {
        return bold;
    }

    public void setBold(boolean bold) {
        this.bold = bold;
    }

    public boolean isItalic() {
        return italic;
    }

    public void setItalic(boolean italic) {
        this.italic = italic;
    }

    public boolean isUnderline() {
        return underline;
    }

    public void setUnderline(boolean underline) {
        this.underline = underline;
    }

     public void toggleCenterAlign() {
        setCenterAlign(! isCenterAlign());
    }

    public void toggleRightAlign() {
        setRightAlign(!isRightAlign());
    }

    public void toggleLeftAlign() {
        setLeftAlign(!isLeftAlign());
    }

    public void toggleUnderLineAlign() {
        setUnderline(!isUnderline());
    }

    public void toggleBold() {
        setBold(!isBold());
    }

    public void toggleItalic() {
        setItalic(!isItalic());
    }

    public int getStyle(){
        int style= Typeface.NORMAL;
        if(isBold())style= Typeface.BOLD;
        if(isItalic())style= Typeface.ITALIC;
        if(isBold() && isItalic()) style= Typeface.BOLD_ITALIC;
        return style;
    }
    public android.text.Layout.Alignment getAlignment(){
         return android.text.Layout.Alignment.ALIGN_CENTER;
    }
}
