package com.example.katier.grapher;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;

import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;

import android.widget.FrameLayout;
import android.view.View.OnTouchListener;

import android.widget.ImageView;
import android.widget.SeekBar;
import android.support.v4.app.DialogFragment;


public class MainActivity extends AppCompatActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageEdit ie = new ImageEdit(this);
        findViewById(R.id.panel).setOnDragListener(ie);
        FrameLayout f = findViewById(R.id.panel);
        f.addView(ie);
        initButtons(ie);
        initSeekBarsI(ie);
    }

    protected void onSaveInstanceState(Bundle outState) {

        super.onSaveInstanceState(outState);
    }

    private void initButtons(final ImageEdit ie){
        findViewById(R.id.color).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                com.example.katier.grapher.Color color = new com.example.katier.grapher.Color();
                color.show(getFragmentManager(), "login");
                color.setIe(ie);
                //ie.setCurColor(((ColorDrawable)findViewById(R.id.color).getBackground()).getColor());
            }
        });
        findViewById(R.id.brushes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Attributes attrs = new Attributes();

                attrs.show(getFragmentManager(), "login");
                attrs.setIe(ie);

            }
        });
        findViewById(R.id.red).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ie.setCurColor(Color.RED);
            }
        });
        findViewById(R.id.blue).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ie.setCurColor(Color.BLUE);
            }
        });
        findViewById(R.id.black).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ie.setCurColor(Color.BLACK);
            }
        });
        findViewById(R.id.yellow).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ie.setCurColor(Color.YELLOW);
            }
        });
        findViewById(R.id.white).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ie.setCurColor(Color.WHITE);
            }
        });
        findViewById(R.id.green).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ie.setCurColor(Color.GREEN);
            }
        });
        findViewById(R.id.next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BitMs.bms[0] = ie.getBitmap();
                BitMs.index = 1;
                Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                startActivity(intent);
            }
        });

    }
    private void initSeekBarsI(final ImageEdit ie) {
        ((SeekBar) findViewById(R.id.thicckness)).setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                ie.setThickness(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        }

}


