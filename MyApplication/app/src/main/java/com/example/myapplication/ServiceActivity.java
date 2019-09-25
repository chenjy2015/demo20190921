package com.example.myapplication;

import com.example.baselibrary.DoubleClickObservableTransformer;
import com.example.baselibrary.ui.BaseUIActivity;
import com.example.myapplication.databinding.ActivityServiceBinding;
import com.example.myapplication.mqtt.Server;
import com.example.myapplication.viewmodel.LoginViewModel;
import com.jakewharton.rxbinding3.view.RxView;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class ServiceActivity extends BaseUIActivity<ActivityServiceBinding, LoginViewModel> {

    Server server;

    @Override
    public int getLayoutId() {
        return R.layout.activity_service;
    }

    @Override
    public void init() {
        try {
            server = new Server();
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initEvent() {
        addDisposable(
                RxView.clicks(dataBinding.send)
                        .compose(new DoubleClickObservableTransformer())
                        .subscribe(o -> {

                            server.message = new MqttMessage();
                            server.message.setQos(2);
                            server.message.setRetained(true);
                            server.message.setPayload("给客户端124推送的信息".getBytes());
                            server.publish(server.topic, server.message);

                            server.message = new MqttMessage();
                            server.message.setQos(2);
                            server.message.setRetained(true);
                            server.message.setPayload("给客户端125推送的信息".getBytes());
                            server.publish(server.topic125, server.message);

                            System.out.println(server.message.isRetained() + "------ratained状态");
                        })
        );
    }

}
