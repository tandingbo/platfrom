package com.tanbobo.platfrom.base.common.session;

import java.util.UUID;

/**
 * Created by tanbobo on 2016/6/30.
 */
public class SidGenerator {
    public static String generateSid() {
        return UUID.randomUUID().toString();
    }
}
