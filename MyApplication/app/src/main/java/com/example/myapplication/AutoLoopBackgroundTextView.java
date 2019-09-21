package com.example.myapplication;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

import androidx.appcompat.widget.AppCompatTextView;

public class AutoLoopBackgroundTextView extends AppCompatTextView {

    public AutoLoopBackgroundTextView(Context context) {
        super(context);
    }

    public AutoLoopBackgroundTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, -1);
    }

    public AutoLoopBackgroundTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private int colorsId = 0;
    private int imageId = 1;

    int[] textColors;
    int[] backgrounds;


    public void init(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.auto_loop_background_text);
        colorsId = typedArray.getResourceId(R.styleable.auto_loop_background_text_text_colors, 0);

        //init text colorIds
        textColors = typedArray.getResources().getIntArray(colorsId);

        //init img resourceIds
        imageId = typedArray.getResourceId(R.styleable.auto_loop_background_text_text_backgrounds,0);
        typedArray = typedArray.getResources().obtainTypedArray(imageId);
        backgrounds = new int[typedArray.length()];
        for (int i=0; i<typedArray.length(); i++){
            backgrounds[i] = typedArray.getResourceId(i,0);
        }

        // recycler
        typedArray.recycle();

    }


    private int count = 0;

    private int getCount(int length) {
        int temp = count;
        if (count < length - 1) {
            count++;
        } else {
            count = 0;
        }
        return temp;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == KeyEvent.ACTION_UP){
            int color = textColors[getCount(textColors.length)];
            setTextColor(color);
            Log.d("AutoAppCompatTextView", String.valueOf(color));

            int background = backgrounds[getCount(backgrounds.length)];
            setBackgroundResource(background);
            Log.d("AutoAppCompatTextView", String.valueOf(background));
        }
        return super.onTouchEvent(event);
    }

}
