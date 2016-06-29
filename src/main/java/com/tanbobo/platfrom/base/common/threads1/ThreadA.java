package com.tanbobo.platfrom.base.common.threads1;

/**
 * 发生器线程
 */
public class ThreadA implements Runnable {
    private Thread thread;// 分配新的 线程 对象。
    private boolean stop = true;
    private int num = 1;

    public ThreadA() {// 无参的构造方法
        //自动生成构造函数存根
    }

    public void strat() {//开启方法
        if (stop) {//如果stop等于true 那么就给stop赋值false，然后开启一个线程
            thread = new Thread(this);// this 是其 run 方法被调用的对象
            stop = false;
            thread.start(); // 开始一个线程
            System.out.println("**当前stop为*******" + stop);
        }
    }

    public void stop() {//停止方法
        stop = true;//给stop 赋值为true
        System.out.println(" stop true");
        thread = null;
    }

    public void run() {
        System.out.println("*test**");
        while (!stop) {
            if (!AlarmConfig.ReceiveFind) {
                System.out.println(num + " 发生器: " + AlarmConfig.queueArray.enqueue(num));
                AlarmConfig.ReceiveFind = AlarmConfig.FIND_ENABLE;
                num++;
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
