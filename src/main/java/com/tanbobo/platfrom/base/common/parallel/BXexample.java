package com.tanbobo.platfrom.base.common.parallel;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.dao.ConcurrencyFailureException;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.*;

/**
 * 并行调度封装类
 */
public class BXexample {

    private static ExecutorService createCustomExecutorService(int poolSize, final String method) {
        int coreSize = Runtime.getRuntime().availableProcessors();//返回系统CUP数量
        if (poolSize < coreSize) {
            coreSize = poolSize;
        }
        ThreadFactory tf = r -> {
            Thread t = new Thread(r, "thread created at BXexample method [" + method + "]");
            t.setDaemon(true);
            return t;
        };
        BlockingQueue<Runnable> queueToUse = new LinkedBlockingQueue<Runnable>();
        final ThreadPoolExecutor executor = new ThreadPoolExecutor(coreSize, poolSize, 60, TimeUnit.SECONDS, queueToUse, tf, new ThreadPoolExecutor.CallerRunsPolicy());

        return executor;
    }

    public static <T> List<T> getSubListPage(List<T> list, int skip, int pageSize) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        int startIndex = skip;
        int endIndex = skip + pageSize;
        if (startIndex > endIndex || startIndex > list.size()) {
            return null;
        }
        if (endIndex > list.size()) {
            endIndex = list.size();
        }
        return list.subList(startIndex, endIndex);
    }

    public static void BXfunction(Collection<?> paramCollection, final ExectueCallBack ecb) {
        //构建执行器
        ExecutorService executor = createCustomExecutorService(Runtime.getRuntime().availableProcessors(), "batchExecuteProjection");
        try {
            //监视器
            final CountDownLatch latch = new CountDownLatch(paramCollection.size());
            final StringBuffer exceptionStaktrace = new StringBuffer();
            Iterator<?> iter = paramCollection.iterator();
            while (iter.hasNext()) {
                final Object entity = iter.next();
                Runnable task = () -> {
                    try {
                        ecb.doExectue(entity);
                    } catch (Throwable t) {
                        exceptionStaktrace.append(ExceptionUtils.getFullStackTrace(t));
                    } finally {
                        latch.countDown();
                    }
                };
                executor.execute(task);//并行调度
            }

            try {
                latch.await();//监视器等待所有线程执行完毕
            } catch (InterruptedException e) {
                //调度异常
                throw new ConcurrencyFailureException("unexpected interruption when re-arranging parameter collection into sub-collections ", e);
            }
            if (exceptionStaktrace.length() > 0) {
                //业务异常
                throw new ConcurrencyFailureException("unpected exception when re-arranging parameter collection, check previous log for details.\n" + exceptionStaktrace);
            }
        } finally {
            executor.shutdown();//执行器关闭
        }
    }
}
