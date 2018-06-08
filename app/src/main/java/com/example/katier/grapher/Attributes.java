package com.example.katier.grapher;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

public class Attributes extends DialogFragment{
    Brushes mode = Brushes.PEN;
    ImageEdit ie;
    Button tmp;// = (Button) findViewById(R.id.pen);
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final View view = getActivity().getLayoutInflater().inflate(R.layout.attrs, null);

        builder.setTitle("Attrs");
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
                        ie.setBrushes(mode);
                        dialog.cancel();
                    }
                });

        view.findViewById(R.id.pen).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mode=Brushes.PEN;
                view.findViewById(R.id.pen).setBackgroundColor(android.graphics.Color.parseColor("#4633b5e5"));
                if(tmp!=null&&tmp!=view.findViewById(R.id.pen)) tmp.setBackgroundColor(android.graphics.Color.parseColor("#33b5e5"));
                 tmp = view.findViewById(R.id.pen);

            }
        });

        view.findViewById(R.id.rect).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mode=Brushes.RECT;
                view.findViewById(R.id.rect).setBackgroundColor(android.graphics.Color.parseColor("#4633b5e5"));
                if(tmp!=null&&tmp!=view.findViewById(R.id.rect)) tmp.setBackgroundColor(android.graphics.Color.parseColor("#33b5e5"));
                tmp = view.findViewById(R.id.rect);
            }
        });
        view.findViewById(R.id.oval).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mode=Brushes.OVAL;
                view.findViewById(R.id.oval).setBackgroundColor(android.graphics.Color.parseColor("#4633b5e5"));
                if(tmp!=null&&tmp!=view.findViewById(R.id.oval)) tmp.setBackgroundColor(android.graphics.Color.parseColor("#33b5e5"));
                tmp = view.findViewById(R.id.oval);
            }
        });
        view.findViewById(R.id.clear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ie.setBrushes(Brushes.CLEAR);
                view.findViewById(R.id.clear).setBackgroundColor(android.graphics.Color.parseColor("#4633b5e5"));
                if(tmp!=null&&tmp!=view.findViewById(R.id.clear)) tmp.setBackgroundColor(android.graphics.Color.parseColor("#33b5e5"));
                tmp = view.findViewById(R.id.clear);
                ie.invalidate();
            }
        });
        Dialog dialog = builder.create();
        final Drawable drawable = new ColorDrawable(000000);
        drawable.setAlpha(100);
        dialog.getWindow().setBackgroundDrawable(drawable);
        dialog.getWindow().requestFeature(android.view.Window.FEATURE_NO_TITLE);

        return dialog;

    }
    public void setIe(ImageEdit ie){
        this.ie=ie;
    }

}
