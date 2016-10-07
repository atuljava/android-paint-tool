package com.paint.tool;

import android.app.ActionBar;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.paint.tool.view.DrawingTool;
import com.paint.tool.view.ImageTool;

import java.util.Hashtable;

public class ToolController {
    ImageTool imageTool;
    DrawingTool drawingTool;
    private FrameLayout container;
    private Context context;
    private Bitmap bitmap;
    private Rect rect;

    Hashtable<String,Boolean> selectedTool =new Hashtable<String,Boolean>();

    private ToolController(){
    }
    private static ToolController toolController;
    
   public  static ToolController getController(){
       if(toolController == null) toolController=new ToolController();
       return toolController;
   }

    public ImageTool getImageTool() {
        return imageTool;
    }

    public void setImageTool(ImageTool imageTool) {
        this.imageTool = imageTool;
    }

    public DrawingTool getDrawingTool() {
        return drawingTool;
    }

    public void setDrawingTool(DrawingTool drawingTool) {
        this.drawingTool = drawingTool;
    }    
    
    public void init(Context mcontext,FrameLayout mcontainer){
        this.container=mcontainer;
        this.context=mcontext;

        bitmap= BitmapFactory.decodeResource(context.getResources(),R.drawable.images);
        imageTool=(ImageTool) container.getChildAt(0);
        resizeContainer();
        imageTool.setBitmap(bitmap);
        resetSelection();
    }

    public void resizeContainer(){
        FrameLayout.LayoutParams frameLayoutParam= new FrameLayout.LayoutParams(bitmap.getWidth(),bitmap.getHeight(),Gravity.CENTER);
        imageTool.setLayoutParams(frameLayoutParam);
    }

    private void resetSelection() {
        selectedTool.put("line",false);
        selectedTool.put("rectangle",false);
        selectedTool.put("circle", false);
        selectedTool.put("rotation", false);
        selectedTool.put("text", false);
        selectedTool.put("pencil", false);
    }

    public void selectPencilTool(){
        resetSelection();
        selectedTool.put("pencil", true);
    }

    public void selectLinelTool(){
        resetSelection();
        selectedTool.put("line", true);
    }

    public void touchEvent(MotionEvent event) {
        rect = new Rect(imageTool.getLeft(), imageTool.getTop(), imageTool.getRight(), imageTool.getBottom());
        if (rect.contains((int)event.getX(),(int)event.getY())){
            Toast.makeText(context, event.getX() + "," +event.getY(), Toast.LENGTH_SHORT).show();
        }
    }

    public void selectRotateTool() {
        resetSelection();
        selectedTool.put("rotation", true);
        container.setRotation(90);
        resizeContainer();
    }
}
