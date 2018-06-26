package com.example.katier.grapher;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.ColorSpace;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import static java.lang.String.valueOf;

public class Color extends DialogFragment {
    boolean rgb = true;
    int a = 0;
    int r = 0;
    int g = 0;
    int b = 0;
    String col="#"+getColorFromInt(r)+getColorFromInt(g)+getColorFromInt(b);
    ImageEdit ie;

    String getCol(){
        System.out.println(col);
        return col;
    }

    public void setIe(ImageEdit ie) {
        this.ie = ie;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final View view = getActivity().getLayoutInflater().inflate(R.layout.coldi, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Choose color");
        //final String col = "#"+getColorFromInt(r)+getColorFromInt(g)+getColorFromInt(b);

        view.findViewById(R.id.col).setBackgroundColor(android.graphics.Color.parseColor(col));
        builder.setView(view);
        builder.setCancelable(true);
        builder.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        builder.setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if(ie==null)return;
                        col = "#"+getColorFromInt(a)+getColorFromInt(r)+getColorFromInt(g)+getColorFromInt(b);
                        if(rgb==false) {
                            int hsvCol = (getHsvColor()&(0x00ffffff))|(a<<24);
                            getActivity().findViewById(R.id.color).setBackgroundColor(hsvCol);
                            ie.setCurColor(hsvCol);                        }
                        else {
                            getActivity().findViewById(R.id.color).setBackgroundColor(android.graphics.Color.parseColor(col));
                            ie.setCurColor(android.graphics.Color.parseColor(col));
                        }
                        dialog.cancel();
                    }
                });
        view.findViewById(R.id.rgb).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tv =(view.findViewById(R.id.textView));
                tv.setText("red");
                tv = (view.findViewById(R.id.textView2));
                tv.setText("green");
                tv = (view.findViewById(R.id.blueText));
                tv.setText("blue");
                col = "#"+getColorFromInt(a)+getColorFromInt(r)+getColorFromInt(g)+getColorFromInt(b);
                view.findViewById(R.id.col).setBackgroundColor(android.graphics.Color.parseColor(col));
                rgb = true;
            }
        });
        view.findViewById(R.id.hsv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tv =(view.findViewById(R.id.textView));
                tv.setText("hue");
                tv = (view.findViewById(R.id.textView2));
                tv.setText("sat");
                tv = (view.findViewById(R.id.blueText));
                tv.setText("value");
                int hsvCol = (getHsvColor()&(0x00ffffff))|(a<<24);
                view.findViewById(R.id.col).setBackgroundColor(hsvCol);
                rgb = false;
            }
        });


        ((SeekBar) view.findViewById(R.id.alfa)).setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int hsvCol;
                a = progress;
                col = "#"+getColorFromInt(a)+getColorFromInt(r)+getColorFromInt(g)+getColorFromInt(b);
                if(!rgb) {
                    hsvCol = (getHsvColor()&(0x00ffffff))|(a<<24);
                    view.findViewById(R.id.col).setBackgroundColor(hsvCol);
                }
                else view.findViewById(R.id.col).setBackgroundColor(android.graphics.Color.parseColor(col));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        ((SeekBar) view.findViewById(R.id.red)).setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int hsvCol;
                r = progress;
                col = "#"+getColorFromInt(a)+getColorFromInt(r)+getColorFromInt(g)+getColorFromInt(b);
                if(!rgb) {
                    hsvCol = (getHsvColor()&(0x00ffffff))|(a<<24);
                    view.findViewById(R.id.col).setBackgroundColor(hsvCol);
                }
                else view.findViewById(R.id.col).setBackgroundColor(android.graphics.Color.parseColor(col));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        ((SeekBar) view.findViewById(R.id.green)).setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int hsvCol;
                g = progress;
                col = "#"+getColorFromInt(a)+getColorFromInt(r)+getColorFromInt(g)+getColorFromInt(b);
                if(rgb==false) {
                    hsvCol = (getHsvColor()&(0x00ffffff))|(a<<24);
                    view.findViewById(R.id.col).setBackgroundColor(hsvCol);
                }
                else view.findViewById(R.id.col).setBackgroundColor(android.graphics.Color.parseColor(col));            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        ((SeekBar) view.findViewById(R.id.blue)).setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int hsvCol;
                b = progress;
                col = "#"+getColorFromInt(a)+getColorFromInt(r)+getColorFromInt(g)+getColorFromInt(b);
                if(rgb==false) {
                    hsvCol = (getHsvColor()&(0x00ffffff))|(a<<24);
                    view.findViewById(R.id.col).setBackgroundColor(hsvCol);
                }
                else view.findViewById(R.id.col).setBackgroundColor(android.graphics.Color.parseColor(col));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        return builder.create();
    }

    private String getColorFromInt(int c){
        String secondStr,firstStr;
        int second = c % 16;
        if(second>=10) {
            secondStr = (new StringBuilder().append((char) ('a'+second-10)).toString());
        }
            else secondStr=String.valueOf(second);
        c=c/16;
        int first = c % 16;
        if(c>=10)
            firstStr = (new StringBuilder().append((char) ('a'+first-10)).toString());

        else firstStr=String.valueOf(first);
        return firstStr+secondStr;

    }
    int getHsvColor(){
        return android.graphics.Color.HSVToColor(new float[]{r*360/255f,g/255f,b/255f});
    }
}