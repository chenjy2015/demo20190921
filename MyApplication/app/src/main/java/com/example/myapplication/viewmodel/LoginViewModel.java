package com.example.myapplication.viewmodel;

import android.util.Log;

public class LoginViewModel implements BaseViewModel {
    @Override
    public void destory() {

    }

    public void login() {
        Log.d("login", "login !!!");
    }
}
