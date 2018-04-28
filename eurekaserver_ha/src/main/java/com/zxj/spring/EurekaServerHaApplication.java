package com.zxj.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @Description:
 * @Author:zhangxiaojun
 * @Date:Created in 16:44 2018/4/28
 */
@EnableEurekaServer
@SpringBootApplication
public class EurekaServerHaApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaServerHaApplication.class, args);
    }
}
