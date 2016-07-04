package com.tanbobo.test;

import com.tanbobo.platfrom.base.common.redis.JedisUtil;
import com.tanbobo.platfrom.base.common.util.SerializerUtil;
import com.tanbobo.platfrom.core.model.Message;
import redis.clients.jedis.Response;

import java.util.List;

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

            int length = 20;
            byte[] keys = "collections".getBytes();

            long s = System.currentTimeMillis();

            for (int i = 0; i < length; i++) {
                try {
                    byte[] value = SerializerUtil.serialize("tanbobo" + (i + 1));
                    JedisUtil.pipelineLpush(keys, value);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            long count = JedisUtil.llen("collections".getBytes());
            System.out.println("列表key 的长度:" + count);

            List<Response<byte[]>> list = JedisUtil.pipelineRpop(length + 1, keys);
            if (list != null) {
                for (Response<byte[]> aList : list) {
                    if (aList.get() != null) {
                        try {
                            System.out.println(SerializerUtil.deserialize(aList.get(), String.class));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

            long e = System.currentTimeMillis();
            System.out.println("总耗时：" + (e - s) / 1000);
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
