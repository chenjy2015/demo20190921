package com.example.myapplication;

import android.app.Application;
import android.content.Context;

import com.blankj.utilcode.util.Utils;

public class MyApplication extends Application {

    public static Context app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        Utils.init(this);
    }
}
