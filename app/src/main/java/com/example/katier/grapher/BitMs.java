package com.example.katier.grapher;

import android.graphics.Bitmap;
import android.graphics.Color;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class BitMs {
        static Bitmap bms [] = new Bitmap[2];
        static Bitmap bmsTmps [] = new Bitmap[2];
        static int thicknes [] = new int[2];
        static int curColor  [] = {Color.BLACK,Color.BLACK};
        static Brushes brushes [] = new Brushes[2];
        static Deque<Bitmap> redoStack [] = new Deque [2];
        static Stack<Bitmap> undoStack [] = new Stack [2];
        static int index = 0;
    }

