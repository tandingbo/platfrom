package com.tanbobo.platfrom.core.service;

/**
 * 用户信息处理service
 */
public interface UserService {
    /**
     * 上传并处理用户图片
     */
    public String uploadUserImage(String base64Code, int x1, int y1, int x2, int y2);
}
