package com.example.myapplication;

import com.example.myapplication.databinding.ActivityLoginBinding;
import com.example.myapplication.viewmodel.LoginViewModel;
import com.jakewharton.rxbinding3.view.RxView;

import java.util.Objects;

public class LoginActivity extends BaseUIActivity<ActivityLoginBinding, LoginViewModel> {


    private static final String topic = "toclient/125";

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
                        .subscribe(o -> viewModel.connect(LoginActivity.this, topic,"admin","password"))
        );

        addDisposable(
                RxView.clicks(dataBinding.send)
                .compose(new DoubleClickObservableTransformer())
                .subscribe(o -> {
                    viewModel.send(topic, Objects.requireNonNull(dataBinding.msgInput.getText()).toString());
                })
        );
    }
}
