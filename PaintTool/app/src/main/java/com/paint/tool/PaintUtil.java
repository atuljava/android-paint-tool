package com.paint.tool;

import android.graphics.Bitmap;

import com.paint.tool.beans.LineBean;
import com.paint.tool.beans.TextBean;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by Yogesh Soni on 1/27/2016.
 */
public class PaintUtil {

    private static PaintUtil instance;
    private ArrayList<Integer> operations = new ArrayList<Integer>();
    private ArrayList<LineBean> allDrawing = new ArrayList<LineBean>();
    private ArrayList<TextBean> allText = new ArrayList<TextBean>();

    Stack<LineBean> redoUndoLineBean = new Stack<LineBean>();
    Stack<TextBean> redoUndoTextBean = new Stack<TextBean>();
    Stack<Integer> redoUndoOperations = new Stack<Integer>();

    private boolean textToolSelected;
    private boolean drawingText=false;
    private boolean drawingLine=false;
    private boolean drawingImage=false;

    private TextBean textBean;
    private float originalWidth;
    private float originalHeight;
    private float canvasWidth;
    private float canvasHeight;
    private float finalWidth;
    private float finalHeight;

    private float ratio;
    private float ratioX = 1.0f;
    private float ratioY = 1.0f;
    private float totalRotation;

    private Bitmap originalBitmap;
    private Bitmap bitmap;

    private PaintUtil(){

    }

    public static PaintUtil getInstance() {
        if(instance == null){
            instance = new PaintUtil();
        }
        return instance;
    }

    public ArrayList<Integer> getOperations() {
        return operations;
    }

    public ArrayList<LineBean> getAllDrawing() {
        return allDrawing;
    }

    public ArrayList<TextBean> getAllText() {
        return allText;
    }

    public boolean isDrawingText() {
        return drawingText;
    }

    public void setDrawingText(boolean drawingText) {
        this.drawingText = drawingText;
    }

    public boolean isDrawingLine() {
        return drawingLine;
    }

    public void setDrawingLine(boolean drawingLine) {
        this.drawingLine = drawingLine;
    }

    public boolean isDrawingImage() {
        return drawingImage;
    }

    public void setDrawingImage(boolean drawingImage) {
        this.drawingImage = drawingImage;
    }

    public TextBean getTextBean() {
        return textBean;
    }

    public void setTextBean(TextBean textBean) {
        this.textBean = textBean;
    }

    public boolean isTextToolSelected() {
        return textToolSelected;
    }

    public void setTextToolSelected(boolean textToolSelected) {
        this.textToolSelected = textToolSelected;
    }


    public Stack<Integer> getRedoUndoOperations() {
        return redoUndoOperations;
    }

    public void setRedoUndoOperations(Stack<Integer> redoUndoOperations) {
        this.redoUndoOperations = redoUndoOperations;
    }

    public float getOriginalWidth() {
        return originalWidth;
    }

    public void setOriginalWidth(float originalWidth) {
        this.originalWidth = originalWidth;
    }

    public float getOriginalHeight() {
        return originalHeight;
    }

    public void setOriginalHeight(float originalHeight) {
        this.originalHeight = originalHeight;
    }

    public float getCanvasWidth() {
        return canvasWidth;
    }

    public void setCanvasWidth(float canvasWidth) {
        this.canvasWidth = canvasWidth;
    }

    public float getCanvasHeight() {
        return canvasHeight;
    }

    public void setCanvasHeight(float canvasHeight) {
        this.canvasHeight = canvasHeight;
    }

    public float getFinalWidth() {
        return finalWidth;
    }

    public void setFinalWidth(float finalWidth) {
        this.finalWidth = finalWidth;
    }

    public float getFinalHeight() {
        return finalHeight;
    }

    public void setFinalHeight(float finalHeight) {
        this.finalHeight = finalHeight;
    }

    public float getTotalRotation() {
        return totalRotation;
    }

    public void setTotalRotation(float totalRotation) {
        this.totalRotation += totalRotation;
        float rotation = (this.totalRotation/90);
        if((rotation % 2) == 0){
            imageResize();
        }else{
            imageResizeAfterRotation();
        }
        bitmap = Bitmap.createScaledBitmap(originalBitmap, (int)PaintUtil.getInstance().getFinalWidth(), (int)PaintUtil.getInstance().getFinalHeight(), true);
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.originalBitmap = bitmap;
    }

    public void initResize(){
        imageResize();
        bitmap = Bitmap.createScaledBitmap(originalBitmap, (int)PaintUtil.getInstance().getFinalWidth(), (int)PaintUtil.getInstance().getFinalHeight(), true);
    }

    public void afterCropResize(){
        imageResize();
        bitmap = Bitmap.createScaledBitmap(originalBitmap, (int)PaintUtil.getInstance().getFinalWidth(), (int)PaintUtil.getInstance().getFinalHeight(), true);
    }

    public void imageResize(){
        if(originalWidth >= canvasWidth){
            ratio = canvasWidth/originalWidth;

            finalWidth = canvasWidth;
            finalHeight =  ratio * originalHeight;
            ratioY = ratio;
        }

        if(originalHeight >= canvasHeight){
            ratio = canvasHeight/originalHeight;

            finalHeight = canvasHeight;
            finalWidth = ratio * originalWidth;
            ratioX = ratio;
        }
        if(finalHeight==0)finalHeight=originalHeight;
        if(finalWidth==0)finalWidth=originalWidth;
    }

    public void imageResizeAfterRotation(){

       /* if(originalHeight > canvasWidth){
            ratio = canvasWidth/originalHeight;

            finalHeight = canvasWidth;
            finalWidth = ratio * originalWidth;
            ratioX = ratio;
        }

        if(originalWidth > canvasHeight){
            ratio = canvasHeight/originalWidth;

            finalWidth = canvasHeight;
            finalHeight =  ratio * originalHeight;
            ratioY = ratio;
        }*/
    }

    public float getRatioX() {
        return ratioX;
    }

    public float getRatioY() {
        return ratioY;
    }

    public void reset(){
        operations.clear();
        allDrawing.clear();
        allText.clear();

        redoUndoOperations.clear();
        redoUndoLineBean.clear();
        redoUndoTextBean.clear();

        textToolSelected=false;
        drawingText=false;
        drawingLine=false;
        drawingImage=false;

        originalWidth=0;
        originalHeight=0;
        canvasWidth=0;
        canvasHeight=0;
        finalWidth=0;
        finalHeight=0;
        ratio=0;
        ratioX = 1.0f;
        ratioY = 1.0f;
        totalRotation=0;

    }

    public  void redoTextBean(){
        if(redoUndoTextBean != null && allText != null && redoUndoTextBean.size()>0){
            TextBean tb=  redoUndoTextBean.pop();
            allText.add(tb);
        }
    }

    public void undoTextBean(){
        if(redoUndoTextBean != null && allText != null && allText.size()>0){
            TextBean tb= allText.get(allText.size() - 1);
            redoUndoTextBean.push(tb);
            allText.remove(allText.size() - 1);
        }
    }

    public  void redoLineBean(){
        if(redoUndoLineBean != null && allDrawing != null && redoUndoLineBean.size()>0){
            LineBean lb=  redoUndoLineBean.pop();
            allDrawing.add(lb);
        }
    }

    public void undoLineBean(){
        if(redoUndoLineBean != null && allDrawing != null && allDrawing.size()>0){
            LineBean tb= allDrawing.get(allDrawing.size() - 1);
            redoUndoLineBean.push(tb);
            allDrawing.remove(allDrawing.size() - 1);
        }
    }
}
