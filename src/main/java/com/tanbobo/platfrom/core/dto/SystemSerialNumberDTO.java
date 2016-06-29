package com.tanbobo.platfrom.core.dto;

/**
 * Created by tanbobo on 2016/6/28.
 */
public class SystemSerialNumberDTO {

    /**
     * 模块编码
     */
    private String moduleCode;
    /**
     * 流水号配置模板
     */
    private String configTemplet;
    /**
     * 序列号最大值
     */
    private String maxSerial;
    /**
     * 是否自动增长标示
     */
    private String isAutoIncrement;
    /**
     * 预生成流水号数量
     */
    private String preMaxNum;

    public String getModuleCode() {
        return moduleCode;
    }

    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    public String getConfigTemplet() {
        return configTemplet;
    }

    public void setConfigTemplet(String configTemplet) {
        this.configTemplet = configTemplet;
    }

    public String getMaxSerial() {
        return maxSerial;
    }

    public void setMaxSerial(String maxSerial) {
        this.maxSerial = maxSerial;
    }

    public String getIsAutoIncrement() {
        return isAutoIncrement;
    }

    public void setIsAutoIncrement(String isAutoIncrement) {
        this.isAutoIncrement = isAutoIncrement;
    }

    public String getPreMaxNum() {
        return preMaxNum;
    }

    public void setPreMaxNum(String preMaxNum) {
        this.preMaxNum = preMaxNum;
    }
}
