package com.wms.db_test;

import com.wms.db_test.service.MQTTService;
import com.wms.db_test.service.TemperaturesService;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;

/**
 * @author by Guoshun
 * @version 1.0.0
 * @description 消息回调返回
 * @date 2023/12/12 17:27
 */
@Component
public class MessageCallbackListener implements IMqttMessageListener {

    @Autowired
    private TemperaturesService temperaturesService;

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        String messageBody = new String(message.getPayload(), StandardCharsets.UTF_8);
        temperaturesService.add(messageBody);
        System.out.println("收到消息："+topic+", 温度是："+ messageBody);
    }
}

