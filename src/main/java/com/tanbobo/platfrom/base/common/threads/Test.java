package com.tanbobo.platfrom.base.common.threads;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class Test {
    public static void main(String[] args) {


        Map<String, Object> newParams = new HashMap<String, Object>();
        newParams.put("task", "tanbobo");
        newParams.put("orderNo", "yw-00000001");

        TradeOper.mapPool.put(newParams.get("orderNo").toString(), newParams);
    }
}
