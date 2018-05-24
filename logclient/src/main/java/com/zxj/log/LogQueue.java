package com.zxj.log;

import com.zxj.log.model.AppLog;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @Description:
 * @Author:zhangxiaojun
 * @Date:Created in 9:38 2018/5/24
 */
public class LogQueue {

    private static ConcurrentLinkedQueue<AppLog> logQueue = new ConcurrentLinkedQueue();
    private static LogThread logThread = new LogThread();

    public static void addLog (AppLog log) {
        logQueue.add(log);
    }

    static  {
        logThread.setDaemon(true);
        logThread.start();
    }

    public static AppLog dequeAppLog() {
        return logQueue.poll();
    }


}
