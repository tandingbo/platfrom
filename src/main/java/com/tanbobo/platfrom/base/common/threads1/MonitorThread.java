package com.tanbobo.platfrom.base.common.threads1;

/**
 * 侦测线程
 */
public class MonitorThread implements Runnable {
    private Thread thread;  // 分配新的 线程 对象。
    private boolean stop = true;

    public void strat() {//开启方法
        if (stop) {//如果stop等于true 那么就给stop赋值false，然后开启一个线程
            thread = new Thread(this);// this 是其 run 方法被调用的对象
            stop = false;
            thread.start(); // 开始一个线程
        }
    }

    public void stop() {//停止方法
        stop = true;//给stop 赋值为true
        thread = null;
    }

    public void run() {
        while (!stop) {
            if (AlarmConfig.ReceiveFind) {
                Object num = AlarmConfig.queueArray.dequeue();
                System.out.println("检测接收:" + num);
                look(num);
                AlarmConfig.ReceiveFind = AlarmConfig.FIND_UNABLE;
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public void look(Object ob) {
        if (ob.equals(2) || ob.equals(12)) {
            System.out.println("报警!  发现错误报告! ======");
        }
    }
}
