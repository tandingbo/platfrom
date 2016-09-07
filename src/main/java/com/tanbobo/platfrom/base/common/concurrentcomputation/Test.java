package com.tanbobo.platfrom.base.common.concurrentcomputation;

import java.util.List;

/**
 * Created by tanbobo on 2016/9/7.
 */
public class Test {
    public static void main(String[] args) {
        //初始化任务池
        Executer exe = new Executer(5);
        //初始化任务
        long time = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            exe.fork(new MyJob());//派发任务
        }

        //汇总任务结果
        List<Object> list = exe.join();
        System.out.println("Result: " + list);
        System.out.println("time: " + (System.currentTimeMillis() - time));
    }
}
