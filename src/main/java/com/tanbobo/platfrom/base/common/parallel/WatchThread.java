package com.tanbobo.platfrom.base.common.parallel;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 测试监控类
 */
public class WatchThread {
    /**
     * 测试函数
     *
     * @throws InterruptedException
     */
    public void testThread() throws InterruptedException {
        int threadNum = 10;
        // 初始化countDown
        CountDownLatch threadSignal = new CountDownLatch(threadNum);
        // 创建固定长度的线程池
//      Executor executor = Executors.newFixedThreadPool(threadNum);
        //此处不可以用接口 需要使用Executor的实现类 ExecutorService  Executor未提供shutdown等方法
        ExecutorService executor = Executors.newFixedThreadPool(threadNum);
        for (int i = 0; i < threadNum; i++) { // 开threadNum个线程
            Runnable task = new TestThread(threadSignal);
            // 执行
            executor.execute(task);

        }
        threadSignal.await(); // 等待所有子线程执行完
        //固定线程池执行完成后 将释放掉资源 退出主进程
        executor.shutdown();//并不是终止线程的运行，而是禁止在这个Executor中添加新的任务
        // do work end
        //退出主进程
        System.out.println(Thread.currentThread().getName() + "+++++++结束.");
    }

    /**
     * 测试函数
     */
    public static void main(String[] args) throws InterruptedException {
        WatchThread test = new WatchThread();
        test.testThread();
    }
}
