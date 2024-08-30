package com.wms.db_test;

import com.wms.db_test.entity.MQTTConfigBuilder;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;

@Slf4j
@Primary
@Configuration
public class MQTTClientUtils {

    @Autowired
    private MQTTConfigBuilder mqttConfig;

    private MqttClient mqttClient;

    public MQTTClientUtils createDevOpsMQTTClient() {
        this.createMQTTClient();
        return this;
    }

    public MQTTClientUtils connect() {
        try {
            this.mqttClient.connect(mqttConfig.getOptions());
            log.info("MQTTClient连接成功！");
        }catch (MqttException mqttException){
            mqttException.printStackTrace();
            log.error("MQTTClient连接失败！");
        }
        return this;
    }

    public MqttClient createMQTTClient() {
        try{
            this.mqttClient = new MqttClient( mqttConfig.getHost(), mqttConfig.getClientId());
            log.info("MQTTClient创建成功！");
            return this.mqttClient;
        }catch (MqttException exception){
            exception.printStackTrace();
            log.error("MQTTClient创建失败！");
            return null;
        }
    }

    /**
     * 消息发送
     * @param topicName
     * @param message
     * @return
     */
    public boolean publish(String topicName, String message) {
        log.info("订阅主题名:{}, message:{}", topicName, message);
        MqttMessage mqttMessage = new MqttMessage(message.getBytes(StandardCharsets.UTF_8));
        try {
            this.mqttClient.publish(topicName, mqttMessage);
            return true;
        }catch (MqttException exception){
            exception.printStackTrace();
            return false;
        }
    }

    /**
     * 消息发送 : retained 默认为 false
     * "retained message" 指的是 Broker 会保留的最后一条发布到某个主题的消息。
     * 当新的订阅者连接到该主题时，Broker 会将这条保留消息立即发送给订阅者，即使在订阅者订阅时该消息并未被重新发布。
     * 这对于一些需要初始状态或者最后一次已知状态的应用场景非常有用。
     * @param topicName
     * @param message
     * @param qos
     * @return
     */
    public boolean publish(String topicName, int qos, String message) {

        MqttMessage mqttMessage = new MqttMessage(message.getBytes(StandardCharsets.UTF_8));
        try {
            this.mqttClient.publish(topicName, mqttMessage.getPayload(), qos, false);
            log.info("主题名:{}, qos:{}, message:{}", topicName, qos, message);
            return true;
        }catch (MqttException exception){
            exception.printStackTrace();
            return false;
        }
    }

    /**
     * 订阅某个主题
     *
     * @param topicName
     * @param qos
     */
    public void subscribe(String topicName, int qos) {
        log.info("订阅主题名:{}, qos:{}", topicName, qos);
        try {
            this.mqttClient.subscribe(topicName, qos);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    /**
     * 订阅某个主题
     *
     * @param topicName
     * @param qos
     */
    public void subscribe(String topicName, int qos, IMqttMessageListener messageListener) {
        log.info("订阅主题名:{}, qos:{}, Listener类:{}", topicName, qos, messageListener.getClass());
        try {
            this.mqttClient.subscribe(topicName, qos, messageListener);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    /**
     * 取消订阅主题
     * @param topicName 主题名称
     */
    public void cleanTopic(String topicName) {
        log.info("取消订阅主题名:{}", topicName);
        try {
            this.mqttClient.unsubscribe(topicName);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    //这里是初始化方法
    @Resource
    private MessageCallbackListener messageCallbackListener;
    @Resource
    private MessageCallbackListenertwo messageCallbackListenertwo;
    public static MQTTClientUtils mqttClientUtils;

    @PostConstruct
    public void initMqttClient(){
        //创建连接
        mqttClientUtils = this.createDevOpsMQTTClient().connect();
        //这里主要是项目启动时订阅一些主题。看个人需要使用
        //mqttClientUtils.subscribe("test/#", 2, new HeartBeatListener());
        //MessageCallbackListener订阅主题，接受到该主题消息后交给MessageCallbackListener去处理
        //mqttClientUtils.subscribe("message/call/back", 2, new MessageCallbackListener());
        //需要注意的是new MessageCallbackListener()虽然会接收到消息，但这么做不对。
        //举个简单列子：就是你有切面对MessageCallbackListener中重写的方法做一些其他操作，
        //那么接收到消息后该切面并不会生效，所以不建议这么做,以下是修改过后的。

        //上面两句放到外面好吧!!!评论的大哥想的有点多，还要写个上下文获取bean...
        mqttClientUtils.subscribe("Taichi-Maker-Pub-60:55:F9:7B:78:4C", 2, messageCallbackListener);
        mqttClientUtils.subscribe("Taichi-Maker-Pub1-60:55:F9:7B:78:4C", 2, messageCallbackListenertwo);

    }
}

