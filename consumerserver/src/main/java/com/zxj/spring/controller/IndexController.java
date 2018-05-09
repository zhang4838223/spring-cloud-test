package com.zxj.spring.controller;

import com.zxj.spring.service.IConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class IndexController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private IConsumerService consumerService;
    //这个@Value会根据配置的配置中心地址找到git仓库对应的配置和本地服务的配置文件
    @Value("${path}")
    private String path;

    @GetMapping("/index")
    public String index(){
        return "zhangxj:" + path + "; result: " + consumerService.doConsum();
    }
}
