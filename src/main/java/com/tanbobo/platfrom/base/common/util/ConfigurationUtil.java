package com.tanbobo.platfrom.base.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by tanbobo on 2016/6/29.
 */
public class ConfigurationUtil extends Properties {
    private static final long serialVersionUID = 2769509836596566841L;

    private static ConfigurationUtil instance = null;

    public static synchronized ConfigurationUtil getInstance() {
        if (instance == null) {
            instance = new ConfigurationUtil();
        }
        return instance;
    }

    public String getProperty(String key, String defaultValue) {
        String val = getProperty(key);
        return (val == null || val.isEmpty()) ? defaultValue : val;
    }

    public String getString(String name, String defaultValue) {
        return this.getProperty(name, defaultValue);
    }

    public int getInt(String name, int defaultValue) {
        String val = this.getProperty(name);
        return (val == null || val.isEmpty()) ? defaultValue : Integer.parseInt(val);
    }

    public long getLong(String name, long defaultValue) {
        String val = this.getProperty(name);
        return (val == null || val.isEmpty()) ? defaultValue : Integer.parseInt(val);
    }

    public float getFloat(String name, float defaultValue) {
        String val = this.getProperty(name);
        return (val == null || val.isEmpty()) ? defaultValue : Float.parseFloat(val);
    }

    public double getDouble(String name, double defaultValue) {
        String val = this.getProperty(name);
        return (val == null || val.isEmpty()) ? defaultValue : Double.parseDouble(val);
    }

    public byte getByte(String name, byte defaultValue) {
        String val = this.getProperty(name);
        return (val == null || val.isEmpty()) ? defaultValue : Byte.parseByte(val);
    }

    public ConfigurationUtil() {
        InputStream in = ClassLoader.getSystemClassLoader().getResourceAsStream("config.xml");
        try {
            this.loadFromXML(in);
            in.close();
        } catch (IOException e) {
        }
    }
}
