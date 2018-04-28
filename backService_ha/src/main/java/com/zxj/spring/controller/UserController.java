package com.zxj.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author:zhangxiaojun
 * @Date:Created in 18:03 2018/4/28
 */
@RestController
public class UserController {

    @Autowired
    DiscoveryClient discoveryClient;

    @GetMapping("/user/list")
    public String users() {
        System.out.println("server2222" + discoveryClient.getServices());
        return "zhangxj";
    }
}
