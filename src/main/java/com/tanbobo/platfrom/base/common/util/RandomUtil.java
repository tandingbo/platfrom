package com.tanbobo.platfrom.base.common.util;

import java.util.Random;

/**
 * 随机数生成类
 */
public class RandomUtil {

    public RandomUtil() {
    }

    /**
     * 生成数字类型随机数
     *
     * @param figures 随机数位数
     * @return
     */
    private static String randomNumber(int figures) {
        // 最大为10位
        if (figures > 10) {
            figures = 10;
        }

        int[] array = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        Random rand = new Random();
        for (int i = 10; i > 1; i--) {
            int index = rand.nextInt(i);
            int tmp = array[index];
            array[index] = array[i - 1];
            array[i - 1] = tmp;
        }
        String result = "";
        for (int i = 0; i < figures; i++) {
            result += "" + array[i];
        }
        return result;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            String rn = randomNumber(11);
            System.out.println(rn);
        }

    }
}
