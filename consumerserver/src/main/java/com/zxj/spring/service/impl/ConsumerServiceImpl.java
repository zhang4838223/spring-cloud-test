package com.zxj.spring.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.zxj.spring.service.IConsumerService;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class ConsumerServiceImpl implements IConsumerService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    @HystrixCommand(fallbackMethod = "fallback")
    public String doConsum() {
        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://provider-server/user/list", String.class);
        Map<String, Object> map = new HashMap<>();
        map.put("name", "zhangsan");
        map.put("time", new Date());
        restTemplate.put("http://localhost:9200/test/spring/1", map);
        System.out.println(forEntity);
        return "zhangxj:" + "; result: " + forEntity;
    }

    public String fallback () {
        return "fallback";
    }
}
