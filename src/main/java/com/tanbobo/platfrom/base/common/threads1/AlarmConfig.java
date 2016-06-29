package com.tanbobo.platfrom.base.common.threads1;

import com.tanbobo.platfrom.base.common.threads.CachePool;

/**
 * 公用的一个报警配置类
 */
public class AlarmConfig {
    //FINDENABLE 启用检测
    public static final boolean FIND_ENABLE = true;

    //FINDUNABLE 关闭检测  当发生线程开启时，侦测线程是关闭的。
    public static final boolean FIND_UNABLE = false;

    //ReceiveFind 接收检测    初始状态是关闭的
    public static boolean ReceiveFind = FIND_UNABLE;

    //QueueArray 类里放这一个循环队列
    public static QueueArray queueArray = new QueueArray();

    public static CachePool<String, Object> mapPool = CachePool.getInstance();

    public AlarmConfig() {

    }
}
