package com.zxj.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    DiscoveryClient discoveryClient;

    @GetMapping("/user/list")
    public String users() {
        System.out.println(discoveryClient.getServices());
        return "zxj";
    }
}
