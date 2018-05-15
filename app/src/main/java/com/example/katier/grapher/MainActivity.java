package com.example.katier.grapher;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FrameLayout f = findViewById(R.id.panel);
        f.addView(new ImageEdit(this));

    }

}

class ImageEdit extends View {
    Bitmap bitmap;
    Canvas canvas;
    Paint p;
    public ImageEdit(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint p=new Paint(Paint.ANTI_ALIAS_FLAG);
        Bitmap bm=Bitmap.createBitmap(canvas.getWidth(),canvas.getHeight(), Bitmap.Config.RGB_565);
        Canvas bmCanv=new Canvas(bm);
        bmCanv.drawColor(Color.WHITE);
        bmCanv.save();
        canvas.drawBitmap(bm,10,20,p);
    }

}