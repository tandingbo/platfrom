package com.tanbobo.test;

import com.tanbobo.platfrom.core.common.spring.RechargeStrategyLoader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by tanbobo on 2016/7/26.
 */
public class RechargeStrategyTesst {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        RechargeStrategyLoader rechargeStrategyLoader = applicationContext.getBean(RechargeStrategyLoader.class);
        String result = rechargeStrategyLoader.getStrategyInfo("百度");
        System.out.println(result);

    }
}
