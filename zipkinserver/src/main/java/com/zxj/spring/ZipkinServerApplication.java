package com.zxj.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import zipkin.server.EnableZipkinServer;

/**
 * @Description:
 * @Author:zhangxiaojun
 * @Date:Created in 11:03 2018/5/16
 */
@SpringBootApplication
@EnableZipkinServer
@EnableAutoConfiguration
public class ZipkinServerApplication {

    public static void main (String[] args) {
        SpringApplication.run(ZipkinServerApplication.class, args);
    }
}
