package com.zxj.spring.common;

import com.baidu.aip.imageclassify.AipImageClassify;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;

import java.util.HashMap;

/**
 * @Description:
 * @Author:zhangxiaojun
 * @Date:Created in 16:53 2018/6/6
 */
public class ImageClassifyUtils {

    private static AipImageClassify client;

    private static ImageClassifyUtils instance;

    private ImageClassifyUtils(){
//        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

//        // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
//        client.setHttpProxy("proxy_host", proxy_port);  // 设置http代理
//        client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理

    }

    public static ImageClassifyUtils getInstance(String appId, String appKey, String seceretKey){
        if (client == null) {
            client = new AipImageClassify(appId, appKey, seceretKey);
            // 可选：设置网络连接参数
            client.setConnectionTimeoutInMillis(2000);
            client.setSocketTimeoutInMillis(60000);
        }

        if (instance == null) {
            instance = new ImageClassifyUtils();
        }

        return instance;

    }
    public static ImageClassifyUtils getInstance() {

        if (instance == null) {
            instance = new ImageClassifyUtils();
        }

        return instance;
    }

    public String plantDetect(String path) {
        JSONObject jsonObject = client.plantDetect(path, new HashMap<String, String>());
        return jsonObject.toString(2);
    }
}
