package com.example.myapplication;

import android.view.View;

public interface ISwitchView {

    void switchFirst();

    void switchPreview();

    void switchNext();

    void switchLast();

    void showAll();

    void show(int id);

    void hideAll();

    void hide(int id);

    boolean hasChild(int id);

    void addView(View view);

    <T> T findChild(int index);

    int getCurrentVisibleIndex();

}
