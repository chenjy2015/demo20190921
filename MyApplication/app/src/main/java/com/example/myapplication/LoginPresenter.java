package com.example.myapplication;

import java.lang.ref.SoftReference;

public class LoginPresenter extends IPresenter {

    LoginPresenter(ILoginView iLoginView){
        this.iModel = new LoginModel();
        this.viewSoftReference = new SoftReference<IView>(iLoginView);
    }

    public void login(String pass){
        ILoginView loginView = (ILoginView) this.viewSoftReference.get();
        String username = loginView.getUserName();
        String password = loginView.getPassword();
        loginView = null;
        ((LoginModel)iModel).login(username, password, new LoginListener() {
            @Override
            public void success() {
                if (viewSoftReference.get() != null){
                    ((ILoginView) viewSoftReference.get()).onLoginSuccess();
                }
            }

            @Override
            public void failed() {
                if (viewSoftReference.get() != null){
                    ((ILoginView) viewSoftReference.get()).onLoginFailed();
                }
            }
        });

    }
}
