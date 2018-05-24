package com.zxj.log.model;

/**
 * @Description:
 * @Author:zhangxiaojun
 * @Date:Created in 9:27 2018/5/24
 */
public class AppLog {
    private String serviceId;
    private String title;
    private String topic;
    private String content;
    private int level;

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
