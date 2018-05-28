package com.example.katier.grapher;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;

import java.util.Arrays;

public class ImageEdit extends View implements View.OnDragListener {
    Bitmap bitmap;
    Canvas mCanvas = new Canvas();
    Paint p;
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

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint p=new Paint(Paint.ANTI_ALIAS_FLAG);
        switch (brushes) {
            case EMPTY:
                Bitmap bm;
                if(BitMs.bms[BitMs.index]==null) {
                     bm = Bitmap.createBitmap(canvas.getWidth(), canvas.getHeight(), Bitmap.Config.RGB_565);
                    Canvas bmCanv = new Canvas(bm);
                    bmCanv.drawColor(Color.WHITE);
                }
                else  bm = BitMs.bms[BitMs.index];
                brushes=Brushes.PEN;
                canvas.drawBitmap(bm, 10, 20, p);
                bitmap = Bitmap.createBitmap(bm);
                mCanvas.setBitmap(bitmap);
                break;
            case PEN:
                if(bitmap==null) return;
                canvas.drawBitmap(bitmap, 10, 20, p);
                mCanvas.setBitmap(bitmap);
                break;
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

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        xPad2=xPad1;
        yPad2 = yPad1;
        xPad1 = event.getX();
        yPad1 = event.getY();

        Paint p=new Paint(Paint.ANTI_ALIAS_FLAG);
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                if (brushes == Brushes.PEN) {
                    p.setColor(curColor);
                    p.setStrokeWidth(thickness);
                    p.setStrokeJoin(Paint.Join.ROUND);    // set the join to round you want
                    p.setStrokeCap(Paint.Cap.ROUND);      // set the paint cap to round too
                    p.setAntiAlias(true);
                    mCanvas.drawLine(xPad1,yPad1,xPad2,yPad2,p);
                    invalidate();
                }
                break;
        }
        return  true;
    }

}
enum  Brushes{
    EMPTY,
    PEN
}

