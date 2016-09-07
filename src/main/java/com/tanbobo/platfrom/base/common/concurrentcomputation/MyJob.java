package com.tanbobo.platfrom.base.common.concurrentcomputation;

/**
 * Created by tanbobo on 2016/9/7.
 */
public class MyJob extends Job {
    @Override
    public Object execute() {
        Long currentTime = System.currentTimeMillis();
        return currentTime;
    }
}
