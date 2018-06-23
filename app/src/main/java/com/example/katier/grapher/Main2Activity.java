package com.example.katier.grapher;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.SeekBar;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ImageEdit ie = new ImageEdit(this);
        findViewById(R.id.panel).setOnDragListener(ie);
        FrameLayout f = findViewById(R.id.panel);
        f.addView(ie);
        initButtons(ie);
        initSeekBarsI(ie);
        setSeekBar();
    }
    public void setSeekBar(){
        SeekBar seekBar = (SeekBar) findViewById(R.id.thicckness);
        seekBar.setProgress(BitMs.thicknes[1]);
        findViewById(R.id.color).setBackgroundColor(BitMs.curColor[1]);
    }
    private void initButtons(final ImageEdit ie){

        findViewById(R.id.brushes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Attributes attrs = new Attributes();

                attrs.show(getFragmentManager(), "login");
                attrs.setIe(ie);

            }
        });

        findViewById(R.id.color).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                com.example.katier.grapher.Color color = new com.example.katier.grapher.Color();
                color.show(getFragmentManager(), "login");
                color.setIe(ie);
            }
        });

        findViewById(R.id.red).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ie.setCurColor(Color.RED);
                findViewById(R.id.color).setBackgroundColor(Color.RED);
            }
        });
        findViewById(R.id.blue).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ie.setCurColor(Color.BLUE);
                findViewById(R.id.color).setBackgroundColor(Color.BLUE);

            }
        });
        findViewById(R.id.black).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ie.setCurColor(Color.BLACK);
                findViewById(R.id.color).setBackgroundColor(Color.BLACK);

            }
        });
        findViewById(R.id.yellow).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ie.setCurColor(Color.YELLOW);
                findViewById(R.id.color).setBackgroundColor(Color.YELLOW);

            }
        });
        findViewById(R.id.white).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ie.setCurColor(Color.WHITE);
                findViewById(R.id.color).setBackgroundColor(Color.WHITE);

            }
        });
        findViewById(R.id.green).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ie.setCurColor(Color.GREEN);
                findViewById(R.id.color).setBackgroundColor(Color.GREEN);

            }
        });

        findViewById(R.id.prev).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BitMs.bms[1] = ie.getBitmap();
                BitMs.thicknes[1] = ie.getThickness();
                BitMs.curColor[1] = ie.getCurColor();
                BitMs.brushes[1] = ie.brushes;
                BitMs.redoStack[1] = ie.redoStack;
                BitMs.undoStack[1] = ie.undoStack;
                BitMs.index = 0;
                Intent intent = new Intent(Main2Activity.this,MainActivity.class);
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

