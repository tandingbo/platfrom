package com.tanbobo.platfrom.base.common.threads1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * 测试类
 */
public class TestMainThread {
    public TestMainThread() {
        // TODO Auto-generated constructor stub
    }

    private static final Logger logger = LoggerFactory.getLogger(TestMainThread.class);

    public static void main(String[] args) {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("qihuOrder", "360充值订单1");
        logger.info("测试");

        // 发生器
        ThreadA treadA = new ThreadA();
        treadA.strat();
//        treadA.stop();

        //
        MonitorThread monitorThread = new MonitorThread();
        monitorThread.strat();
//        monitorThread.stop();
    }
}
