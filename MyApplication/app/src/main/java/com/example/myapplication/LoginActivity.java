package com.example.myapplication;

import com.example.myapplication.databinding.ActivityLoginBinding;
import com.example.myapplication.viewmodel.LoginViewModel;
import com.jakewharton.rxbinding3.view.RxView;

public class LoginActivity extends BaseUIActivity<ActivityLoginBinding, LoginViewModel> {

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void init() {

    }

    @Override
    public void initEvent() {
        addDisposable(
                RxView.clicks(dataBinding.start)
                        .compose(new DoubleClickObservableTransformer())
                        .subscribe(o -> viewModel.login())
        );
    }
}
