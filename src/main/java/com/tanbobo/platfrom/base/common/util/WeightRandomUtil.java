package com.tanbobo.platfrom.base.common.util;

import java.util.*;

/**
 * 权重计算工具类
 */
public class WeightRandomUtil {

    /**
     * 计算权重总和
     *
     * @param weights
     * @return
     */
    private static double weightSum(Set<Double> weights) {
        double weightSum = 0;
        for (double weightValue : weights) {
            weightSum += weightValue;
        }
        return weightSum;
    }

    /**
     * @param weightValueMapping
     * @param <T>
     * @return 返回数据
     */
    public static <T> T getWeightRandom(Map<Double, T> weightValueMapping) {
        double weightSum = weightSum(weightValueMapping.keySet());
        double stepWeightSum = 0;
        List<Double> list = new ArrayList<>(weightValueMapping.keySet());
        Collections.sort(list, (o1, o2) -> (int) (o2 - o1));
        double r = Math.random();
        for (double weight : list) {
            // 计算权重值
            stepWeightSum += weight;
            // 如果随机数落在了权重区间则返回索引值
            if (r <= stepWeightSum / weightSum) {
                return weightValueMapping.get(weight);
            }
        }
        return null;
    }


    private static Map<Double, String> weightMapping = new HashMap<>();

    static {
        weightMapping.put(30d, "A");
        weightMapping.put(20D, "B");
        weightMapping.put(50D, "C");
    }

    public static void main(String[] args) {
        int c = 0;
        int a = 0;
        int b = 0;
        for (int i = 0; i < 2000000; i++) {
            String str = WeightRandomUtil.getWeightRandom(weightMapping);
            if ("A".equals(str)) {
                a++;
            } else if ("B".equals(str)) {
                b++;
            } else if ("C".equals(str)) {
                c++;
            }
        }

        System.out.println(c / 2000000d);
        System.out.println(b / 2000000d);
        System.out.println(a / 2000000d);
    }
}
