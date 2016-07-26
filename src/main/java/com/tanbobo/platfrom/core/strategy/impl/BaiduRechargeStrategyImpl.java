package com.tanbobo.platfrom.core.strategy.impl;

import com.tanbobo.platfrom.core.strategy.IRechargeStrategy;

/**
 * 充值策略-百度充值实现
 */
public class BaiduRechargeStrategyImpl implements IRechargeStrategy {

    private static final String identify = "百度";

    @Override
    public String getStrategyInfo(String strategy) {
        return "百度" + strategy;
    }

    @Override
    public String getIdentify() {
        return identify;
    }
}
