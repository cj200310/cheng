package com.wms.db_test;

import com.wms.db_test.entity.MQTTConfigBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DbTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(DbTestApplication.class, args);

    }

}
