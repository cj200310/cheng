/* Includes ------------------------------------------------------- */
#define LED1 12
#define LED2 13
#define PUMP_PIN 7
#define THRESHOLD 2500
#define SOIL_MOISTURE_PIN A0

#include "AITA_DHT.h"

/* Object definition ---------------------------------------------- */
AITA_DHT dht11;

#include <Arduino.h>
#include <WiFi.h>
#include <HTTPClient.h>
#include <PubSubClient.h>
#include <Ticker.h>

#ifndef STASSID
#define STASSID SSID
#define STAPSK PASS
#endif

const char *ssid = "CJ";
const char *pass = "20031019qweq";
const char *mqttServer = "192.168.107.76";
const char *mqtt_username = "admin";
const char *mqtt_password = "20031019qwe";
const int mqtt_port = 1883;

Ticker ticker;
WiFiClient wifiClient;
PubSubClient mqttClient(wifiClient);

int count;    // Ticker计数用变量

// ESP8266连接wifi
void connectWifi(){
  WiFi.begin(ssid, pass);
  //等待WiFi连接,成功连接后输出成功信息
  while (WiFi.status() != WL_CONNECTED) {
    delay(1000);
    Serial.print(".");
  }
  Serial.println("");
  Serial.println("WiFi Connected!");  
  Serial.println(""); 
}

// 连接MQTT服务器并订阅信息
void connectMQTTServer(){
  // 根据ESP8266的MAC地址生成客户端ID（避免与其它ESP8266的客户端ID重名）
  String clientId = "esp32C3-" + WiFi.macAddress();
 
  // 连接MQTT服务器
  if (mqttClient.connect(clientId.c_str(), mqtt_username, mqtt_password)) { 
    Serial.println("MQTT Server Connected.");
    Serial.println("Server Address: ");
    Serial.println(mqttServer);
    Serial.println("ClientId:");
    Serial.println(clientId);
    subscribeTopic(); // 订阅指定主题
  } else {
    Serial.print("MQTT Server Connect Failed. Client State:");
    Serial.println(mqttClient.state());
    delay(5000);
  }   
}

// 收到信息后的回调函数
void receiveCallback(char* topic, byte* payload, unsigned int length) {
  Serial.print("Message Received [");
  Serial.print(topic);
  Serial.print("] ");
  for (int i = 0; i < length; i++) {
    Serial.print((char)payload[i]);
  }
  Serial.println("");
  Serial.print("Message Length(Bytes) ");
  Serial.println(length);
 
  if ((char)payload[8] == 't') {     // 如果收到的信息以“1”为开始
    digitalWrite(PUMP_PIN, HIGH);
    digitalWrite(LED2, HIGH);  // 则点亮LED。
    Serial.println("LED ON");
  } else {                           
    digitalWrite(LED2, LOW); // 否则熄灭LED。
    Serial.println("LED OFF");
  }
}

// 订阅指定主题
void subscribeTopic(){
 
  // 建立订阅主题。主题名称以Taichi-Maker-Sub为前缀，后面添加设备的MAC地址。
  // 这么做是为确保不同设备使用同一个MQTT服务器测试消息订阅时，所订阅的主题名称不同
  String topicString = "Taichi-Maker-Sub-" + WiFi.macAddress();
  char subTopic[topicString.length() + 1];  
  strcpy(subTopic, topicString.c_str());
  
  // 通过串口监视器输出是否成功订阅主题以及订阅的主题名称
  if(mqttClient.subscribe(subTopic)){
    Serial.println("Subscrib Topic:");
    Serial.println(subTopic);
  } else {
    Serial.print("Subscribe Fail...");
  }  
}

void tickerCount(){
  count++;
}

// 发布信息
void pubMQTTmsg(){
  static int value; // 客户端发布信息用数字
 
  // 建立发布主题。主题名称以Taichi-Maker-为前缀，后面添加设备的MAC地址。
  // 这么做是为确保不同用户进行MQTT信息发布时，ESP8266客户端名称各不相同，
  String topicString = "Taichi-Maker-Pub-" + WiFi.macAddress();
  char publishTopic[topicString.length() + 1];  
  strcpy(publishTopic, topicString.c_str());

  String topicString1 = "Taichi-Maker-Pub1-" + WiFi.macAddress();
  char publishTopic1[topicString1.length() + 1];  
  strcpy(publishTopic1, topicString1.c_str());

 
  // 建立发布信息。信息内容以Hello World为起始，后面添加发布次数。
  uint8_t temperature, humidity;
  if(!dht11.dht11Read(&temperature, &humidity)){
    Serial.printf("The temp:%d,The humi:%d\r\n", temperature, humidity);
  }
  String messageString = String((float)temperature, 1); 
  char publishMsg[messageString.length() + 1];   
  strcpy(publishMsg, messageString.c_str());

  String messageString1 = String((float)humidity, 1); 
  char publishMsg1[messageString1.length() + 1];   
  strcpy(publishMsg1, messageString1.c_str());
  //
  // 实现ESP8266向主题发布信息
  if(mqttClient.publish(publishTopic, publishMsg)&&mqttClient.publish(publishTopic1, publishMsg1)){
    Serial.println("Publish Topic:");Serial.println(publishTopic);
    Serial.println("Publish message:");Serial.println(publishMsg);    
    Serial.println("Publish Topic1:");Serial.println(publishTopic1);
    Serial.println("Publish message1:");Serial.println(publishMsg1);    
  } else {
    Serial.println("Message Publish Failed."); 
  }
}

void setup() {
  pinMode(PUMP_PIN, OUTPUT);
  pinMode(SOIL_MOISTURE_PIN, INPUT);
  pinMode(LED2, OUTPUT);
  pinMode(LED1, OUTPUT);     // 设置板上LED引脚为输出模式
  digitalWrite(LED1, HIGH);  // 启动后关闭板上LED
  Serial.begin(115200);
  WiFi.mode(WIFI_STA);
  // 连接WiFi
  connectWifi();
  Serial.print("RRSI: ");
  Serial.println(WiFi.RSSI());
  // 设置MQTT服务器和端口号
  mqttClient.setServer(mqttServer, mqtt_port);
  // 设置MQTT订阅回调函数
  mqttClient.setCallback(receiveCallback);
  // 连接MQTT服务器
  connectMQTTServer();
  // Ticker定时对象
  ticker.attach(1, tickerCount);  
}

void loop() {
  if ((WiFi.status() == WL_CONNECTED)) {
    if (mqttClient.connected()) { // 如果开发板成功连接服务器    
        // 每隔3秒钟发布一次信息
        if (count >= 3){
        pubMQTTmsg();
        count = 0;
        }    
        mqttClient.loop();          // 保持客户端心跳
        //uint8_t temperature, humidity;
        //if(!dht11.dht11Read(&temperature, &humidity)){
        //Serial.printf("The temp:%d,The humi:%d\r\n", temperature, humidity);
        //}
    } else {                  // 如果开发板未能成功连接服务器
        connectMQTTServer();    // 则尝试连接服务器
    }
    int soilMoisture = analogRead(SOIL_MOISTURE_PIN);
    Serial.print("Soil Humidity: ");
    Serial.println(soilMoisture);

    if (soilMoisture > THRESHOLD) {

    digitalWrite(PUMP_PIN, HIGH); // Turn on the pump
    Serial.println("Water Pump ON");
    } else {
    
    digitalWrite(PUMP_PIN, LOW); // Turn off the pump
    Serial.println("Water Pump OFF");
    }
    delay(1000);
    }
}
