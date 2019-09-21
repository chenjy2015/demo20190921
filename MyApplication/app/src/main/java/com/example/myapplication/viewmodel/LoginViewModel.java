package com.example.myapplication.viewmodel;

import android.content.Context;
import android.util.Log;

import com.example.myapplication.mqtt2.MQTTContacts;
import com.example.myapplication.mqtt2.MQTTProxy;

public class LoginViewModel implements BaseViewModel {
    @Override
    public void destory() {

    }

    public void login() {
        Log.d("login", "login !!!");
    }

    public void connect(Context context, String topic, String userName, String password) {
        MQTTProxy.getInstance().init(context, topic, userName, password).connect();
    }

    public void send(String topic, String msg){
        MQTTProxy.getInstance().publish(topic, msg, true, MQTTContacts.QoS.QoSForOnce.type);
    }
}
