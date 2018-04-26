package com.zxj.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class IndexController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/index/")
    public String index(){
        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://provider-server/user/list", String.class);
        System.out.println(forEntity);
        return "zhangxj";
    }
}
