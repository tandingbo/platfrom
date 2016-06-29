package com.tanbobo.platfrom.core.model;

import com.tanbobo.platfrom.base.model.BaseModel;

/**
 * 定义消息类接收消息内容和设置消息的下标
 */
public class Message extends BaseModel {
    private static final long serialVersionUID = -8108438650860057317L;

    private int id;
    private String content;

    public Message() {
    }

    public Message(int id, String content) {
        this.id = id;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
