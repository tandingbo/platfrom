package com.tanbobo.platfrom.base.common.threads;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 *
 */
public class TradeOper {
    /**
     * 缓存对象 map
     */
    public static CachePool<String, Object> mapPool = CachePool.getInstance();

    private static final int NTHREADS = 5;
    // 使用线程池来避免 为每个请求创建一个线程。
    private static final Executor threadPool = Executors.newFixedThreadPool(NTHREADS);



    public void startThread() {
        threadPool.execute(new Runnable() {
            public void run() {
                executeCodeOper();
            }
        });
    }

    public void executeCodeOper() {
        String key = "";
        Map param = null;
        synchronized (mapPool) {
            System.out.println(Thread.currentThread().getName() + "进来了。。。。");
            System.out.println("现在队列中共有----" + mapPool.size() + "---条数据");
            Iterator it = mapPool.keySet().iterator();
            //缓存不为空时,取出一个值
            while (it.hasNext()) {
                key = (String) it.next();
                param = (Map) mapPool.get(key);
            }
            if (null != param) {
                //为防止重复,将其移除
                mapPool.remove(key);
            }
        }
        if (null != param) {
            boolean result = ticketTradeOperator(param);
            System.out.println("此条数据处理========" + result);
            if (!result) {
                //若处理失败,重新放回队列
                mapPool.put(key, param);
            }
        }
    }


    public boolean ticketTradeOperator(Map<String, String> params) {
        //具体的处理工作
        String task = params.get("task");
        if ("tanbobo".equals(task)) {
            return true;
        }
        return false;
    }
}
