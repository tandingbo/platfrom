package com.tanbobo.platfrom.base.common.parallel;

import java.util.UUID;
import java.util.concurrent.CountDownLatch;

/**
 * Created by tanbobo on 2016/9/13.
 */
public class TestThread implements Runnable {
    private String name = UUID.randomUUID().toString();
    private CountDownLatch threadsSignal;

    public TestThread(CountDownLatch threadsSignal) {
        this.threadsSignal = threadsSignal;
    }


    public void run() {
        System.out.println(Thread.currentThread().getName() + "开始..." + name);
        System.out.println("开始了线程：：：：" + threadsSignal.getCount());

        // do shomething

        //核心处理逻辑

        //  用到成员变量name作为参数

        // 线程结束时计数器减1
        threadsSignal.countDown();//必须等核心处理逻辑处理完成后才可以减1
        System.out.println(Thread.currentThread().getName() + "结束. 还有" + threadsSignal.getCount() + " 个线程");
    }
}
