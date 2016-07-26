package com.tanbobo.test;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.Map;

/**
 * 去if else, 使用策略模式 + 工厂模式 + 单例模式
 */
public class StrategyTest {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/config/applicationContext.xml");
        String result = ObtainStrategyInfo.getInstance().getStrategyInfo("策略一");
        System.out.println(result);
    }
}

/* （单例模式） */
/*这种单例模式在类一加载的时候就将单例对象创建完毕，总是这个对象存在内存中，避免了通过线程同步来生成对象，线程安全的创建方式。*/
class ObtainStrategyInfo {

    private static final ObtainStrategyInfo obtainStrategyInfo = new ObtainStrategyInfo();
    private StrategyFactory strategyFactory = new StrategyFactory();

    public static ObtainStrategyInfo getInstance(){
        return obtainStrategyInfo;
    }

    public String getStrategyInfo(String strategy){
        StrategyInfo strategyInfo = strategyFactory.getStrategyInfoClass(strategy);
        return strategyInfo.getStrategyInfo(strategy);
    }
}

/* 其实最终的if else判断逻辑都在这里了 （工厂模式）*/
class StrategyFactory {
    private static Map<String, StrategyInfo> strategyInfoMap = new HashMap<String, StrategyInfo>();

    public StrategyInfo getStrategyInfoClass(String strategy){
        return strategyInfoMap.get(strategy);
    }

    /* 被策略调用实现自动注册 */
    public static void addStrategyForFactory(String strategyName, StrategyInfo strategyInfo) {
        strategyInfoMap.put(strategyName, strategyInfo);
    }
}

/* (策略模式) */
interface StrategyInfo {
    String getStrategyInfo(String strategy);
}

class Strategy1 implements StrategyInfo, InitializingBean {

    private static final String identify = "策略一";

    public String getStrategyInfo(String strategy) {
        return "策略一 " + strategy;
    }

    /* Strategy2的对象在初始化完成后会调用这个生命周期函数 */
    public void afterPropertiesSet() throws Exception {
        StrategyFactory.addStrategyForFactory(identify, this);
    }
}

class Strategy2 implements StrategyInfo, InitializingBean {

    private static final String identify = "策略一";
    public String getStrategyInfo(String strategy) {
        return "策略二 " + strategy;
    }

    public void afterPropertiesSet() throws Exception {
        StrategyFactory.addStrategyForFactory(identify, this);
    }
}