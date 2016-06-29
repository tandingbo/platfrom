package com.tanbobo.platfrom.base.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by tanbobo on 2016/6/28.
 */
public class DateUtils {

    /**
     * 格式化日期
     *
     * @param date
     * @param str
     * @return
     */
    public static String format(Date date, String str) {
        SimpleDateFormat sdf = new SimpleDateFormat(str);
        return sdf.format(date);
    }
}
