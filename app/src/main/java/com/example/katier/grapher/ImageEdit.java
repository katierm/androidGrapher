package com.example.katier.grapher;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.SeekBar;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class ImageEdit extends View implements View.OnDragListener {
    Bitmap bitmap;
    Canvas mCanvas = new Canvas();

    Bitmap bitmapTmp;
    Canvas mCanvasTmp = new Canvas();
    boolean redo = false;

    Paint p;
    public Deque<Bitmap> redoStack = new LinkedList<>();
    public Stack<Bitmap> undoStack = new Stack<>();
    float xPad1,yPad1,xPad2,yPad2;
    int curColor = Color.BLACK;
    int thickness = 1;
    Brushes brushes = Brushes.EMPTY;
    public ImageEdit(Context context) {
        super(context);
        this.setDrawingCacheEnabled(true);

    }
    void setCurColor(int color){
        curColor = color;
    }
    void setThickness(int thickness) {
        this.thickness = thickness;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }
    public void setBrushes(Brushes brushes){
        this.brushes=brushes;
    }
    private void fillWhite(Bitmap bm){
        Canvas bmCanv = new Canvas(bm);
        bmCanv.drawColor(Color.WHITE);
    }

    public int getThickness() {
        return thickness;
    }

    public Brushes getBrushes() {
        return brushes;
    }

    public int getCurColor() {
        return curColor;
    }
    private void setAfterReturning(){
        bitmap = Bitmap.createBitmap(BitMs.bms[BitMs.index]);
        //bitmapTmp = Bitmap.createBitmap(BitMs.bmsTmps[BitMs.index]);
        thickness = BitMs.thicknes[BitMs.index];
        brushes = BitMs.brushes[BitMs.index];
        curColor = BitMs.curColor[BitMs.index];
        redoStack = BitMs.redoStack[BitMs.index];
        undoStack = BitMs.undoStack[BitMs.index];
        //SeekBar seekBar = (SeekBar) findViewById(R.id.thicckness);
        //seekBar.setProgress(thickness);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint p=new Paint(Paint.ANTI_ALIAS_FLAG);
       if(brushes==Brushes.EMPTY) {
           Bitmap bm = Bitmap.createBitmap(canvas.getWidth(), canvas.getHeight(), Bitmap.Config.RGB_565);
           if (BitMs.bms[BitMs.index] == null) {
              fillWhite(bm);
               brushes = Brushes.PEN;
               canvas.drawBitmap(bm, 10, 20, p);

               bitmap = Bitmap.createBitmap(bm);
               bitmapTmp = bitmap.copy(Bitmap.Config.RGB_565,true);
               mCanvasTmp = new Canvas(bitmapTmp);
           } else {
               setAfterReturning();
               bitmapTmp = bitmap.copy(Bitmap.Config.RGB_565,true);
               mCanvasTmp = new Canvas(bitmapTmp);
               canvas.drawBitmap(bitmap, 10, 20, p);
           }
           mCanvas.setBitmap(bitmap);
       }
           else{
                if(bitmap==null) return;
                if(brushes==Brushes.CLEAR) {
                    fillWhite(bitmap);
                    setBrushes(Brushes.PEN);
                    System.out.println("aaaaas");
                }

           if(brushes==Brushes.PEN&&redo==false){canvas.drawBitmap(bitmapTmp, 10, 20, p);}
                else {
                    canvas.drawBitmap(bitmap, 10, 20, p);
                    redo = false;
           }
           //mCanvas.setBitmap(bitmap);
        }

    }

    @Override
    public boolean onDrag(View v, DragEvent event) {
        /*curColor=Color.RED;
        xPad2=xPad1;
        yPad2 = yPad1;
        xPad1 = event.getX();
        yPad1 = event.getY();
        invalidate();*/
        xPad2=xPad1;
        yPad2 = yPad1;
        xPad1 = event.getX();
        yPad1 = event.getY();
        return true;
    }
    Path path = new Path();

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        xPad2=xPad1;
        yPad2 = yPad1;
        xPad1 = event.getX();
        yPad1 = event.getY();
        Paint p=new Paint(Paint.ANTI_ALIAS_FLAG);
        p.setColor(curColor);
        p.setStrokeWidth(thickness);
        p.setStrokeJoin(Paint.Join.ROUND);    // set the join to round you want
        p.setStrokeCap(Paint.Cap.ROUND);      // set the paint cap to round too
        p.setAntiAlias(true);
        p.setStyle(Paint.Style.FILL);
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:

                if (brushes == Brushes.PEN) {
                    p.setStyle(Paint.Style.STROKE);
                    mCanvasTmp.drawBitmap(bitmap,0,0,null);
                    path.lineTo(xPad2,yPad2);
                    //path.close();
                    mCanvasTmp.drawPath(path,p);

                    //mCanvas.drawLine(xPad1,yPad1,xPad2,yPad2,p);
                    invalidate();
                }
                if (brushes == Brushes.RECT) {
                    mCanvas.drawRect(xPad1,yPad1,xPad1+thickness,yPad1+thickness,p);
                    invalidate();
                }
                if (brushes == Brushes.OVAL) {

                    mCanvas.drawOval(xPad1,yPad1,xPad1+thickness,yPad1+thickness,p);
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_DOWN:
                if(brushes==Brushes.PEN) {
                    path.reset();
                    path.moveTo(xPad1, yPad1);
                    //mCanvas.drawPoint(event.getX(), event.getY(), p);
                    //invalidate();
                }
                if(redoStack.size()>7)  redoStack.removeLast();
                redoStack.addFirst(Bitmap.createBitmap(bitmap));
                break;
            case MotionEvent.ACTION_UP:
                if(brushes==Brushes.PEN)mCanvas.drawBitmap(bitmapTmp,0,0,null);
                invalidate();
                break;
        }
        return  true;
    }

}
enum  Brushes{
    EMPTY,
    PEN,
    RECT,
    OVAL,
    CLEAR
}

