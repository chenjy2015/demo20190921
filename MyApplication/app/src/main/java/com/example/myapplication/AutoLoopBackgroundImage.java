package com.example.myapplication;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;

import androidx.appcompat.widget.AppCompatImageView;

public class AutoLoopBackgroundImage extends AppCompatImageView {
    public AutoLoopBackgroundImage(Context context) {
        super(context);
    }

    public AutoLoopBackgroundImage(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, -1);
    }

    public AutoLoopBackgroundImage(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }


    private int id = 0;
    int[] backgrounds;

    public void init(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.auto_loop_background_img);
        id = typedArray.getResourceId(R.styleable.auto_loop_background_img_img_backgrounds, 0);
        try {
            typedArray = typedArray.getResources().obtainTypedArray(id);
            backgrounds = new int[typedArray.length()];
            for (int i=0; i<typedArray.length(); i++){
                backgrounds[i] = typedArray.getResourceId(i,0);
            }
            setImageResource(backgrounds[count]);
        }catch (Resources.NotFoundException e){
            e.printStackTrace();
        }
        typedArray.recycle();
    }


    private int count = 0;
    private int getCount(int length) {
        if (count < length - 1) {
            count++;
        } else {
            count = 0;
        }
        int temp = count;
        return temp;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == KeyEvent.ACTION_UP){
            if (backgrounds != null) {
                int background = backgrounds[getCount(backgrounds.length)];
                setImageResource(background);
                Log.d("AutoAppCompatTextView", String.valueOf(background));
            }
        }
        return super.onTouchEvent(event);
    }
}
