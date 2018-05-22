package com.example.katier.grapher;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.view.View.OnTouchListener;

import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageEdit ie = new ImageEdit(this);
        findViewById(R.id.panel).setOnDragListener(ie);
        FrameLayout f = findViewById(R.id.panel);
        f.addView(ie);
        ie.initButtons();
    }

}

class ImageEdit extends View implements View.OnDragListener{
    Bitmap bitmap;
    Path path=new Path();
    Canvas mCanvas = new Canvas();
    Paint p;
    float xPad1,yPad1,xPad2,yPad2;
    int curColor = Color.BLACK;
    Brushes brushes = Brushes.EMPTY;
    public ImageEdit(Context context) {
        super(context);
        this.setDrawingCacheEnabled(true);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint p=new Paint(Paint.ANTI_ALIAS_FLAG);
        Bitmap bm=Bitmap.createBitmap(canvas.getWidth(),canvas.getHeight(), Bitmap.Config.RGB_565);
        Canvas bmCanv=new Canvas(bm);
        switch (brushes) {
            case EMPTY:
                bmCanv.drawColor(Color.WHITE);
                brushes=Brushes.PEN;
                canvas.drawBitmap(bm, 10, 20, p);
                bitmap = Bitmap.createBitmap(bm);
                //bmCanv.save();
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
        curColor=Color.RED;
        xPad2=xPad1;
        yPad2 = yPad1;
        xPad1 = event.getX();
        yPad1 = event.getY();

        Paint p=new Paint(Paint.ANTI_ALIAS_FLAG);
    switch (event.getAction()) {
        case MotionEvent.ACTION_MOVE:
            if (brushes == Brushes.PEN) {
                p.setColor(curColor);
                mCanvas.drawLine(xPad1,yPad1,xPad2,yPad2,p);
                invalidate();
            }
            break;
    }
        return  true;
    }
  public  void initButtons(){
      findViewById(R.id.red).setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
          }
      });
        findViewById(R.id.blue).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                curColor = Color.BLUE;
            }
        });

    }

}
enum  Brushes{
    EMPTY,
    PEN
}