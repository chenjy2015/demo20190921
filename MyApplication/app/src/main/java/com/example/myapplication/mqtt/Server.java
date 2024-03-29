package com.example.myapplication.mqtt;


import com.example.mqtt.MQTTManager;
import com.example.mqtt.PushCallback;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 * Title:Server
 * Description: 服务器向多个客户端推送主题，即不同客户端可向服务器订阅相同主题
 *
 * @author chenrl
 * 2016年1月6日下午3:29:28
 */
public class Server {

    public static final String HOST = "tcp://192.168.10.48:61613";
    public static final String TOPIC = "toclient/124";
    public static final String TOPIC125 = "toclient/125";
    public static final String CLIENT_ID = "server12";

    private MqttClient client;
    public MqttTopic topic;
    public MqttTopic topic125;
    private String userName = "admin";
    private String passWord = "password";

    public MqttMessage message;

    public Server() throws MqttException {
        // MemoryPersistence设置clientid的保存形式，默认为以内存保存
        client = new MqttClient(HOST, CLIENT_ID, new MemoryPersistence());
        connect();
    }

    private void connect() {
        MqttConnectOptions options = new MqttConnectOptions();
        options.setCleanSession(false);
        options.setUserName(userName);
        options.setPassword(passWord.toCharArray());
        // 设置超时时间
        options.setConnectionTimeout(5);
        // 设置会话心跳时间
        options.setKeepAliveInterval(20);
        try {
            client.setCallback(new PushCallback(MQTTManager.getInstance()));
            client.connect(options);
            topic = client.getTopic(TOPIC);
            topic125 = client.getTopic(TOPIC125);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void publish(MqttTopic topic, MqttMessage message) throws MqttPersistenceException,
            MqttException {
        if (topic != null) {
            MqttDeliveryToken token = topic.publish(message);
            token.waitForCompletion();
            System.out.println("message is published completely! "
                    + token.isComplete());
        }else {
            System.out.println(message.toString());
        }
    }

    public static void main(String[] args) throws MqttException {
        Server server = new Server();

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
    }
}