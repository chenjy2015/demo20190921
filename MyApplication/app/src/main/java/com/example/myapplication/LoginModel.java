package com.example.myapplication;

public class LoginModel implements IModel {

    protected String mUserName = "zhangshan";
    protected String mPassword = "123";


    public void login(String username, String password, LoginListener loginListener) {
        if (mUserName.endsWith(username) && mPassword.endsWith(password)) {
            loginListener.success();
        } else {
            loginListener.failed();
        }
    }

}
