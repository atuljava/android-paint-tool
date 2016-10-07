package com.paint.tool.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import com.paint.tool.PaintUtil;

public class ImageTool  extends View {

    private Context context;
    private Paint paint;

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    private Bitmap bitmap;
    int opationCounter=0;
    private boolean b;

    public ImageTool(Context context) {
                super(context);
                this.context=context;
    }
    public ImageTool(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

/*
        @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //PaintUtil.getInstance().setCanvasHeight(h);
        //PaintUtil.getInstance().setCanvasWidth(w);
       // PaintUtil.getInstance().initResize();
    }*/

    public void onDraw(Canvas c){
       /* if(PaintUtil.getInstance().isDrawingLine() || PaintUtil.getInstance().isDrawingText()){ invalidate();return;}
        PaintUtil.getInstance().setDrawingImage(true);
        opationCounter = PaintUtil.getInstance().getAllDrawing().size()-1;
        b=false;
        int rc=0;
        for(int i = PaintUtil.getInstance().getOperations().size()-1;i>=0;i--){
            if(PaintUtil.getInstance().getOperations().get(i)==90){
                c.rotate(90, this.getWidth()/2,this.getHeight()/2);
                c.save();
                rc++;
                Log.d("SAVE", "rotated");
            }
        }
        c.drawBitmap(PaintUtil.getInstance().getBitmap(),
                this.getWidth()/2- PaintUtil.getInstance().getBitmap().getWidth()/2,
                this.getHeight()/2- PaintUtil.getInstance().getBitmap().getHeight()/2,paint);
        for(int i=0;i<rc;i++)c.restore();
        PaintUtil.getInstance().setDrawingImage(false);
        invalidate();*/
        c.drawBitmap(bitmap,0,0,null);
    }


}