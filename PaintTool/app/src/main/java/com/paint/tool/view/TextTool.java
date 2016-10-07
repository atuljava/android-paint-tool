package com.paint.tool.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.DynamicLayout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.Log;
import android.view.View;

import com.paint.tool.PaintUtil;
import com.paint.tool.TextBeanUtil;
import com.paint.tool.beans.TextBean;


public class TextTool  extends View {

    private Paint paint;
    int optionCounter =0;
    private boolean b;
    private int textSize = 20;
    private int color = Color.BLACK;


    public TextTool(Context context) {
        super(context);
    }
    public void onDraw(Canvas c){
        if(PaintUtil.getInstance().isDrawingLine() || PaintUtil.getInstance().isDrawingImage()){ invalidate();return;}
        PaintUtil.getInstance().setDrawingText(true);
        optionCounter = PaintUtil.getInstance().getAllText().size()-1;

        b=false;
        int rc=0;
        if(PaintUtil.getInstance().getTextBean() != null) {
            paint = PaintUtil.getInstance().getTextBean().getPaint();
            paint.setTextSize(TextBeanUtil.getInstance().getSize());
            paint.setColor(getColor());
            Typeface tf=Typeface.create(TextBeanUtil.getInstance().getFontName(), TextBeanUtil.getInstance().getStyle());
            paint.setTypeface(tf);
            //drawText(paint, c, textBean);
            c.drawText(PaintUtil.getInstance().getTextBean().getValue(),
                    PaintUtil.getInstance().getTextBean().getX(), PaintUtil.getInstance().getTextBean().getY(), paint);
        }
        for(int i = PaintUtil.getInstance().getOperations().size()-1;i>=0;i--){
            if(PaintUtil.getInstance().getOperations().get(i)==90){
                c.rotate(90, this.getWidth()/2,this.getHeight()/2);
                c.save();
                rc++;
                Log.d("SAVE", "rotated");
            }
            else if(PaintUtil.getInstance().getOperations().get(i)==2){
                TextBean tb = PaintUtil.getInstance().getAllText().get(optionCounter);
                drawFixText(tb.getPaint(), c, tb);
                  // c.drawText(tb.getValue(),  tb.getX(), tb.getY(), tb.getPaint());
                optionCounter--;
            }
        }
        for(int i=0;i<rc;i++){
           try{ c.restore();}catch(Exception e){}
        }




        PaintUtil.getInstance().setDrawingText(false);
        invalidate();
    }

    private void drawText(Paint paint,Canvas c,TextBean textBean) {
         if(textBean!=null) {
             try {

                 TextPaint textPaint = new TextPaint(paint);
                 DynamicLayout staticLayout = new DynamicLayout(textBean.getValue(), textPaint, (int) textPaint.measureText(textBean.getValue().toString()), android.text.Layout.Alignment.ALIGN_CENTER, 1, 1, false);
                 c.translate(textBean.getX(), textBean.getY());
                 staticLayout.draw(c);
                 try{ c.restore();}catch(Exception e){}
             }catch (Exception e){}
        }
    }

    private void drawFixText(Paint paint,Canvas c,TextBean textBean) {
        if(textBean!=null) {
            try {
               TextPaint textPaint = new TextPaint(paint);
                StaticLayout staticLayout = new StaticLayout(textBean.getValue(), textPaint, (int) textPaint.measureText(textBean.getValue().toString()), android.text.Layout.Alignment.ALIGN_CENTER, 1, 1, false);
                c.translate(textBean.getX(), textBean.getY());
                staticLayout.draw(c);
               c.restore();

            }catch (Exception e){}
        }
    }

    public int getTextSize() {
        return textSize;
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
    }


    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
