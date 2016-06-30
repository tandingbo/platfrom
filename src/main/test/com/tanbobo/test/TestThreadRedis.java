package com.tanbobo.test;

import com.tanbobo.platfrom.base.common.redis.JedisUtil;
import com.tanbobo.platfrom.base.common.util.SerializerUtil;
import com.tanbobo.platfrom.base.common.util.ThreadPoolUtil;
import com.tanbobo.platfrom.core.model.Message;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 *
 */
public class TestThreadRedis {

    private static final String key = "tkey";
    private static List<Future<Object>> resultList = new ArrayList<Future<Object>>();

    public static void main(String[] args) {
//        for (int i = 0; i < 200; i++) {
//            final int index = i + 1;
//            ThreadPoolUtil.execute(new Runnable() {
//                @Override
//                public void run() {
//                    Message message = new Message(index, "tanbobo" + index);
//                    JedisUtil.set((key + index).getBytes(), SerializerUtil.serialize(message));
//                }
//            });
//        }


        for (int i = 0; i < 200; i++) {
            final int index = i + 1;
            Future<Object> taskResult = ThreadPoolUtil.submit(new Callable<Object>() {
                @Override
                public Object call() throws Exception {
                    byte[] bytes = JedisUtil.get((key + index).getBytes());

                    Message message1 = SerializerUtil.deserialize(bytes, Message.class);
                    System.out.println(message1.getId() + "   ---   " + message1.getContent());
                    return index;
                }
            });
            resultList.add(taskResult);
        }

        long b = System.currentTimeMillis();
        //提交的任务运行结束后关闭线程池
        ThreadPoolUtil.fixedThreadPool.shutdown();
        while (true) {
            /**
             * 通过不断运行ExecutorService.isTerminated()方法检测全部的线程是否都已经运行结束
             */
            if (ThreadPoolUtil.fixedThreadPool.isTerminated()) {
                System.out.println("所有任务执行完毕");
                System.out.println("时间差=" + String.valueOf(System.currentTimeMillis() - b));
                break;
            }
            try {
                //milliseconds
                TimeUnit.MILLISECONDS.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (resultList.size() > 0) {
            for (Future<Object> future : resultList) {
                try {
                    /**
                     *  V get() throws InterruptedException, ExecutionException;
                     *  会抛出异常，可以捕获异常，当发生异常时，可以选择立即shutdown其他任务
                     */
                    System.out.println("本次任务的执行结果：" + future.get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    //立即shutdown其他任务
                    ThreadPoolUtil.fixedThreadPool.shutdownNow();
                    e.printStackTrace();
                }
            }
        }
    }
}
