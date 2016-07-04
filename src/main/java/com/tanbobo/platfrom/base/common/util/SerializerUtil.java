package com.tanbobo.platfrom.base.common.util;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Protostuff 序列化工具类
 */
public class SerializerUtil {

    /**
     * 序列化对象
     *
     * @param obj, 对象
     * @return
     */
    public static <T> byte[] serialize(T obj) throws Exception {
        if (obj == null) {
            throw new RuntimeException("序列化对象(" + obj + ")!");
        }
        @SuppressWarnings("unchecked")
        Schema<T> schema = (Schema<T>) RuntimeSchema.getSchema(obj.getClass());
        LinkedBuffer buffer = LinkedBuffer.allocate(1024 * 1024);
        byte[] protostuff = null;
        try {
            protostuff = ProtostuffIOUtil.toByteArray(obj, schema, buffer);
        } catch (Exception e) {
            throw new RuntimeException("序列化(" + obj.getClass() + ")对象(" + obj + ")发生异常!", e);
        } finally {
            buffer.clear();
        }
        return protostuff;
    }

    /**
     * 反序列化对象
     *
     * @param bytes,      序列化数据
     * @param targetClass
     * @return
     */
    public static <T> T deserialize(byte[] bytes, Class<T> targetClass) throws Exception {
        if (bytes == null || bytes.length == 0) {
            throw new RuntimeException("反序列化对象发生异常,byte序列为空!");
        }
        T instance = null;
        try {
            instance = targetClass.newInstance();
        } catch (Exception e) {
            throw new RuntimeException("反序列化过程中依据类型创建对象失败!", e);
        }
        Schema<T> schema = RuntimeSchema.getSchema(targetClass);
        ProtostuffIOUtil.mergeFrom(bytes, instance, schema);
        return instance;
    }

    /**
     * 序列化集合
     *
     * @param objList, 集合
     * @return
     */
    public static <T> byte[] serializeList(List<T> objList) throws Exception {
        if (objList == null || objList.isEmpty()) {
            throw new RuntimeException("序列化对象列表(" + objList + ")参数异常!");
        }
        @SuppressWarnings("unchecked")
        Schema<T> schema = (Schema<T>) RuntimeSchema.getSchema(objList.get(0).getClass());
        LinkedBuffer buffer = LinkedBuffer.allocate(1024 * 1024);
        byte[] protostuff = null;
        ByteArrayOutputStream bos = null;
        try {
            bos = new ByteArrayOutputStream();
            ProtostuffIOUtil.writeListTo(bos, objList, schema, buffer);
            protostuff = bos.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("序列化对象列表(" + objList + ")发生异常!", e);
        } finally {
            buffer.clear();
            try {
                if (bos != null) {
                    bos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return protostuff;
    }

    /**
     * 反序列化集合
     *
     * @param bytes,      序列化数据
     * @param targetClass
     * @return
     */
    public static <T> List<T> deserializeList(byte[] bytes, Class<T> targetClass) throws Exception {
        if (bytes == null || bytes.length == 0) {
            throw new RuntimeException("反序列化对象发生异常,byte序列为空!");
        }

        Schema<T> schema = RuntimeSchema.getSchema(targetClass);
        List<T> result = null;
        try {
            result = ProtostuffIOUtil.parseListFrom(new ByteArrayInputStream(bytes), schema);
        } catch (IOException e) {
            throw new RuntimeException("反序列化对象列表发生异常!", e);
        }
        return result;
    }
}
