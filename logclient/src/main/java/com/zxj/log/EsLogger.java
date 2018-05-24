package com.zxj.log;

import com.zxj.log.model.AppLog;

/**
 * @Description:
 * @Author:zhangxiaojun
 * @Date:Created in 10:48 2018/5/24
 */
public class EsLogger {
    public static EsLogger logger = new EsLogger();
    private EsLogger(){}
    public void info(String title, String topic, String content){
        AppLog log = new AppLog();
        log.setContent(content);
        log.setLevel(1);
        log.setServiceId("consumer");
        log.setTitle(title);
        log.setTopic(topic);

        LogQueue.addLog(log);
    }
}
