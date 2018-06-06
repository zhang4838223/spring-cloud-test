package com.zxj.spring;

import com.zxj.spring.common.ImageClassifyUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker
public class ConsumerApplication {

    @Value("${APP_ID}")
    private String APP_ID;

    @Value("${API_KEY}")
    private String API_KEY;

    @Value("${SECRET_KEY}")
    private String SECRET_KEY;

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate () {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }

    @Bean
    public ImageClassifyUtils imageClassifyUtils() {
        return ImageClassifyUtils.getInstance(APP_ID, API_KEY, SECRET_KEY);
    }
}
