package com.example.myapplication;

public interface ILoginView extends IView {

    String getUserName();

    String getPassword();

    void onLoginSuccess();

    void onLoginFailed();
}
