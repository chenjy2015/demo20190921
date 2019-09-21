package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements ILoginView {

    private LoginPresenter loginPresenter = new LoginPresenter(this);

    private AutoLoopBackgroundTextView mLoginBtn;

    private AutoLoopBackgroundImage mImg;

    @Override
    protected void onDestroy() {
        super.onDestroy();

         new ImageModel().requestUrls();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLoginBtn = findViewById(R.id.login);
        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                loginPresenter.login();
//                RxJavaDemo.getInstance().init();
                startService(new Intent(MainActivity.this, MyService.class));
            }
        });
        mImg = findViewById(R.id.login_out);
        mImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "login out success !", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public String getUserName() {
        return "";
    }

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public void onLoginSuccess() {
        Toast.makeText(MainActivity.this, "login success !", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoginFailed() {
        Toast.makeText(MainActivity.this, "login failed !", Toast.LENGTH_SHORT).show();
    }
}
