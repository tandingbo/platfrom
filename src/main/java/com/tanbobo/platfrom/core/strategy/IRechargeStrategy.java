package com.tanbobo.platfrom.core.strategy;

/**
 * 充值策略接口
 */
public interface IRechargeStrategy {
    String getStrategyInfo(String strategy);

    String getIdentify();
}
