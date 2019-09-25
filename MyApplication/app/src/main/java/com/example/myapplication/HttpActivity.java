package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.baselibrary.ui.BaseActivity;
import com.example.myapplication.retrofit.HttpProxy;
import com.example.myapplication.retrofit.ThrowableHandler;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Response;

public class HttpActivity extends BaseActivity {

    Button requestBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http);

        requestBtn = findViewById(R.id.request);

//       requestBtn.setOnClickListener(v -> HttpRequestManager.getInstance().init().request());
        requestBtn.setOnClickListener(this::onClick);
    }

    public void onClick(View view) {
        addDisposable(
                HttpProxy.getInstance()
//                        .getBook(10)
                        .getInfo("type", "1002")
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnError(this::log)
                        .subscribe(this::log)
        );
    }

    public void log(Throwable throwable) {
        log(ThrowableHandler.handleThrowable(throwable).toString());
    }

    public void log(String content) {
        Log.d("getBook", "" + content);
    }

    public void log(Response<ResponseBody> response) {
        Log.d("getBook", "body : " + response);
    }
}
