package com.wms.db_test;


import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;


@Slf4j
@Configuration
public class fasong extends MQTTClientUtils{

    //这里是初始化方法
    @Resource
    private MessageCallbackListenerqianduan messageCallbackListenerqianduan;

    @PostConstruct
    public void initMqttClient(){
        //创建连接
        //MQTTClientUtils mqttClientUtils = this.createDevOpsMQTTClient().connect();
        mqttClientUtils.subscribe("qianduan", 2, messageCallbackListenerqianduan);

    }
}
