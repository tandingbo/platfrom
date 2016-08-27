package com.tanbobo.platfrom.core.service.impl;

import com.tanbobo.platfrom.base.common.util.ImageUtil;
import com.tanbobo.platfrom.core.service.UserService;
import org.springframework.stereotype.Service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * 用户信息处理serviceImpl
 */
@Service
public class UserServiceImpl implements UserService {

    /**
     * 用户上传图片处理
     */
    public String uploadUserImage(String base64Code, int x1, int y1, int x2, int y2) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String dateStr = sdf.format(new Date());
        String path = "D:/user_image/" + dateStr + "/";
        String url = "http://127.0.0.1:8080/user_image/" + dateStr + "/";
        File f = new File(path);
        if (!f.exists()) {
            f.mkdirs();
        }
        String fileName = UUID.randomUUID().toString().replaceAll("-", "") + ".jpg";
        String temp_fileName = path + "t_" + fileName;
        String b_fileName = path + "b_" + fileName;
        String m_fileName = path + "m_" + fileName;
        String s_fileName = path + "s_" + fileName;
        String result = "";
        try {
            //创建原始文件(先强制修改为jpg格式)
            boolean isCreate = ImageUtil.base64ToImage(base64Code, path + fileName);
            if (isCreate) {
                float scale = ImageUtil.getScaleCutImage(path + fileName);//比例
                int width = (int) ((x2 - x1) * scale);
                int height = width;
                int start_x = (int) (x1 * scale);
                int start_y = (int) (y1 * scale);
                //剪切图片
                ImageUtil.cutImage(path + fileName, temp_fileName, start_x, start_y, width, height);
                //剪切以后的图片压缩到固定大小的图片
                ImageUtil.reduceImageByWidthHeight(temp_fileName, b_fileName, 200, 200);//200*200大图
                ImageUtil.reduceImageByWidthHeight(temp_fileName, m_fileName, 120, 120);//120*120中图
                ImageUtil.reduceImageByWidthHeight(temp_fileName, s_fileName, 70, 70);//70*70小图
                result = url + fileName;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
