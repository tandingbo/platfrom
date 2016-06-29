package com.tanbobo.platfrom.base.common.util;


import java.util.concurrent.*;

/**
 *
 */
public class ThreadPoolUtil {
    /**
     * 可缓存线程池(线程池无大小限制)，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程
     */
    private ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    /**
     * 定长线程池，可控制线程最大并发数，超出的线程会在队列中等待
     */
    public static ExecutorService fixedThreadPool = Executors.newFixedThreadPool(20);
    /**
     * 定长线程池，支持定时及周期性任务执行
     */
    private ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
    /**
     * 单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行
     */
    private ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();

    /**
     * 添加一个无返回值的线程
     *
     * @param task
     */
    public static void execute(Runnable task) {
        fixedThreadPool.execute(task);
    }

    /**
     * 添加一个有返回值的线程
     *
     * @param task
     * @return
     */
    public static Future<Object> submit(Callable<Object> task) {
        return fixedThreadPool.submit(task);
    }

}
