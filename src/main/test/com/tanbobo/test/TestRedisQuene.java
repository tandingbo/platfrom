package com.tanbobo.test;

import com.tanbobo.platfrom.base.common.redis.JedisUtil;
import com.tanbobo.platfrom.base.common.redis.RedisCacheUtil;
import com.tanbobo.platfrom.base.common.util.SerializerUtil;
import com.tanbobo.platfrom.core.model.Message;

import java.util.Set;

/**
 * redis队列测试类
 */
public class TestRedisQuene {
    public static byte[] redisKey = "key".getBytes();

    static {
        init();
    }

    public static void main(String[] args) {
        pop();
    }

    private static void pop() {
        try {
            byte[] bytes = JedisUtil.rpop(redisKey);
            if (bytes != null && bytes.length > 0) {
                Message msg = SerializerUtil.deserialize(bytes, Message.class);
                if (msg != null) {
                    System.out.println(msg.getId() + "   " + msg.getContent());
                }

                JedisUtil.flushdb();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void init() {
        try {
            Message msg1 = new Message(1, "内容1");
            JedisUtil.lpush(redisKey, SerializerUtil.serialize(msg1));
            Message msg2 = new Message(2, "内容2");
            JedisUtil.lpush(redisKey, SerializerUtil.serialize(msg2));
            Message msg3 = new Message(3, "内容3");
            JedisUtil.lpush(redisKey, SerializerUtil.serialize(msg3));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
