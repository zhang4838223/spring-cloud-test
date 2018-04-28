package com.zxj.spring.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.zxj.spring.service.IConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ConsumerServiceImpl implements IConsumerService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    @HystrixCommand(fallbackMethod = "fallback")
    public String doConsum() {
        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://provider-server/user/list", String.class);
        System.out.println(forEntity);
        return "zhangxj:" + "; result: " + forEntity;
    }

    public String fallback () {
        return "fallback";
    }
}
