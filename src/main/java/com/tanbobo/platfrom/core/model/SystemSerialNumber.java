package com.tanbobo.platfrom.core.model;

import com.tanbobo.platfrom.base.model.BaseModel;

/**
 * 序列号表模型
 */
public class SystemSerialNumber extends BaseModel {
    private static final long serialVersionUID = 8764920560806421990L;

    /**
     * 主键
     */
    private String id;
    /**
     * 模块名称
     */
    private String moduleName;
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


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

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

    public SystemSerialNumber(String id) {
        this.id = id;
    }

    public SystemSerialNumber(String id, String moduleCode) {
        this.id = id;
        this.moduleCode = moduleCode;
    }

    public SystemSerialNumber() {
    }
}
