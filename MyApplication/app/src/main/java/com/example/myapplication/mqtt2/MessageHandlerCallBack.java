package com.example.myapplication.mqtt2;

public interface MessageHandlerCallBack {
    void messageSuccess(String topicName, String topicMsg);
}
