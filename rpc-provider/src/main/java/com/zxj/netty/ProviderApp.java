package com.zxj.netty;

import com.zxj.netty.server.AppInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * spring应用启动类
 */
@Configuration
@ComponentScan("com.zxj")
public class ProviderApp
{
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppLancher.class);
        AppInitializer.start();
    }
}
