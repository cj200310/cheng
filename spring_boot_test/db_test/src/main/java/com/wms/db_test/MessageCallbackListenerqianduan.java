package com.wms.db_test;


import com.wms.db_test.service.MQTTService;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
public class MessageCallbackListenerqianduan implements IMqttMessageListener {

    @Autowired
    private MQTTService mqttService;

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        String messageBody = new String(message.getPayload(), StandardCharsets.UTF_8);
        mqttService.sendMessage("Taichi-Maker-Sub-60:55:F9:7B:78:4C",2,messageBody);
        System.out.println("收到消息："+ messageBody);
    }
}
