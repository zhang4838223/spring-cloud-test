package com.zxj.log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zxj.log.model.AppLog;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.core.DocumentResult;
import io.searchbox.core.Index;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

/**
 * @Description:
 * @Author:zhangxiaojun
 * @Date:Created in 9:39 2018/5/24
 */
public class LogThread extends Thread{
    private JestClient jestClient;
    private Gson gson = new Gson();

    public LogThread(){
        JestClientFactory factory = new JestClientFactory();
        factory.setHttpClientConfig(new HttpClientConfig
                .Builder("http://localhost:9200")
                .gson(new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create())
                .multiThreaded(true)
                .readTimeout(10000)
                .build());
        jestClient = factory.getObject();
    }

    @Override
    public void run() {
        while (!this.isInterrupted()) {
            try {
                AppLog appLog = LogQueue.dequeAppLog();
                if (null != appLog) {
                    sendToEs(appLog);
                } else {
                    Thread.sleep(10L);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    private void sendToEs(AppLog log) {
        Index index = new Index.Builder(gson.toJson(log)).index("test").type("spring").build();
        try {
            DocumentResult result = jestClient.execute(index);
        } catch (IOException e) {

        }
    }
}
