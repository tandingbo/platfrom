package com.tanbobo.platfrom.core.strategy.impl;

import com.tanbobo.platfrom.core.strategy.IRechargeStrategy;

/**
 * 充值策略-奇虎充值实现
 */
public class QihuRechargeStrategyImpl implements IRechargeStrategy {
    private static final String identify = "奇虎";

    @Override
    public String getStrategyInfo(String strategy) {
        return "奇虎" + strategy;
    }

    @Override
    public String getIdentify() {
        return identify;
    }
}
