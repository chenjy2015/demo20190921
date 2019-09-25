package com.example.myapplication;

import android.content.Intent;
import android.util.Log;

import com.example.baselibrary.DoubleClickObservableTransformer;
import com.example.baselibrary.ui.BaseUIActivity;
import com.example.mqtt.MQTTBean;
import com.example.mqtt.MQTTContacts;
import com.example.mqtt.MQTTManager;
import com.example.myapplication.databinding.ActivityLoginBinding;
import com.example.myapplication.mqtt.Server;
import com.example.myapplication.viewmodel.LoginViewModel;
import com.jakewharton.rxbinding3.view.RxView;

import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;

import java.util.Objects;

public class LoginActivity extends BaseUIActivity<ActivityLoginBinding, LoginViewModel> implements IMqttActionListener {

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void init() {
        viewModel.init(this, new MQTTBean(MQTTContacts.TOPIC125, MQTTContacts.CLIENT_ID, "admin", "password"));
        viewModel.setMessageHandlerCallBack((topicName, topicMsg) -> {
            if (topicMsg != null) {
                dataBinding.msgInput.setText(topicMsg);
            }
        });
    }

    @Override
    public void initEvent() {
        addDisposable(
                RxView.clicks(dataBinding.start)
                        .compose(new DoubleClickObservableTransformer())
                        .subscribe(o -> viewModel.connect(this))
        );

        addDisposable(
                RxView.clicks(dataBinding.service)
                        .compose(new DoubleClickObservableTransformer())
                        .subscribe(o -> startActivity(new Intent(LoginActivity.this, ServiceActivity.class)))
        );

        addDisposable(
                RxView.clicks(dataBinding.send)
                        .compose(new DoubleClickObservableTransformer())
                        .subscribe(o -> {
                            viewModel.send(Server.CLIENT_ID, Objects.requireNonNull(dataBinding.msgInput.getText()).toString());
                        })
        );
    }

    @Override
    public void onSuccess(IMqttToken asyncActionToken) {
        //注册要监听的主题
        viewModel.register(Server.TOPIC, MQTTContacts.QoS.QoSAtLeastOnce.type);
        viewModel.register(Server.TOPIC125, MQTTContacts.QoS.QoSAtLeastOnce.type);
        Log.d(MQTTManager.TAG, "connect success");
    }

    @Override
    public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
        Log.d(MQTTManager.TAG, "connect failed ---" + asyncActionToken.toString() + exception.getMessage());
    }

}
