package com.example.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class SwitchViewGroup extends FrameLayout implements ISwitchView {

    private SparseArray<View> sparseArray;
    private int index = 0;

    public SwitchViewGroup(@NonNull Context context) {
        super(context);
    }

    public SwitchViewGroup(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int count = getChildCount();
        Log.d("SwitchViewGroup", "count : " + count);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        int count = getChildCount();
        Log.d("SwitchViewGroup", "count : " + count);
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int count = getChildCount();
        Log.d("SwitchViewGroup", "count : " + count);
    }

    public void init() {
        sparseArray = new SparseArray();
        for (int i = 0; i < getChildCount(); i++) {
            sparseArray.put(i, getChildAt(i));
        }
    }

    @Override
    public void switchFirst() {
        if (sparseArray.size() < 1) {
            return;
        }
        for (int i = 0; i < sparseArray.size(); i++) {
            if (i == 0) {
                sparseArray.get(i).setVisibility(VISIBLE);
            } else {
                sparseArray.get(i).setVisibility(GONE);
            }
        }
        index = 0;
        Log.d("SwitchViewGroup", "index : " + index);
    }

    public void switchPreview() {
        if (sparseArray.size() < 1) {
            return;
        }
        if (index >= 0) {
            if (index < sparseArray.size()) {
                if (index - 1 > 0) {
                    index--;
                } else {
                    index = 0;
                }
            } else {
                index = 0;
            }
            hideAll();
            sparseArray.get(index).setVisibility(VISIBLE);
        }
        Log.d("SwitchViewGroup", "index : " + index);
    }

    @Override
    public void switchNext() {
        if (sparseArray.size() < 1) {
            return;
        }
        if (index >= 0) {
            if (index < sparseArray.size()) {
                if (index + 1 < sparseArray.size()) {
                    index++;
                } else {
                    index = 0;
                }
            } else {
                index = 0;
            }
            hideAll();
            sparseArray.get(index).setVisibility(VISIBLE);
        }
        Log.d("SwitchViewGroup", "index : " + index);
    }

    @Override
    public void switchLast() {
        if (sparseArray.size() < 1) {
            return;
        }
        for (int i = 0; i < sparseArray.size(); i++) {
            if (i == sparseArray.size() - 1) {
                sparseArray.get(i).setVisibility(VISIBLE);
            } else {
                sparseArray.get(i).setVisibility(GONE);
            }
        }
        index = sparseArray.size() - 1 > 0 ? sparseArray.size() - 1 : 0;
        Log.d("SwitchViewGroup", "index : " + index);
    }

    @Override
    public void showAll() {
        for (int i = 0; i < sparseArray.size(); i++) {
            sparseArray.get(i).setVisibility(VISIBLE);
        }
    }

    @Override
    public void show(int id) {
        findViewById(id).setVisibility(VISIBLE);
    }

    @Override
    public void hideAll() {
        for (int i = 0; i < sparseArray.size(); i++) {
            sparseArray.get(i).setVisibility(GONE);
        }
    }

    @Override
    public void hide(int id) {
        findViewById(id).setVisibility(GONE);
    }

    @Override
    public boolean hasChild(int id) {
        return findViewById(id) != null;
    }

    @Override
    public <T> T findChild(int index) {
        return (T) sparseArray.get(index);
    }

    @Override
    public int getCurrentVisibleIndex() {
        return index;
    }

}
