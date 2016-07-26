package com.tanbobo.platfrom.core.common.spring;

import com.tanbobo.platfrom.core.strategy.IRechargeStrategy;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
@Component
public class RechargeStrategyLoader implements InitializingBean, ApplicationContextAware {
    private ApplicationContext applicationContext;
    private static Map<String, IRechargeStrategy> strategyInfoMap = new HashMap<String, IRechargeStrategy>();

    public void afterPropertiesSet() throws Exception {
        Map<String, IRechargeStrategy> rechargeStrategys = applicationContext.getBeansOfType(IRechargeStrategy.class);
        if (CollectionUtils.isEmpty(rechargeStrategys)) {
            return;
        }

        for (String key : rechargeStrategys.keySet()) {
            strategyInfoMap.put(rechargeStrategys.get(key).getIdentify(), rechargeStrategys.get(key));
        }
    }

    public String getStrategyInfo(String strategy) {
        IRechargeStrategy rechargeStrategy = strategyInfoMap.get(strategy);
        if (rechargeStrategy != null) {
            return rechargeStrategy.getStrategyInfo(strategy);
        }
        return "";
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
