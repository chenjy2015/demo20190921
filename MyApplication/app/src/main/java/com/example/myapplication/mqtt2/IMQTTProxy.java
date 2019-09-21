package com.example.myapplication.mqtt2;

import android.content.Context;

import javax.net.ssl.SSLContext;

public interface IMQTTProxy<T extends IMQTTProxy> {

    T init(Context context, String topic, String userName, String password);

    void connect();

    void disconnect();

    void release();

    boolean isConnected();

    void subscribeMsg(String topic, int qos);

    void publish(String topic, String msg, boolean isRetained, int qos);

    void setMessageHandlerCallBack(MessageHandlerCallBack callBack);

    SSLContext sslContextFromStream(String assetName);
}
