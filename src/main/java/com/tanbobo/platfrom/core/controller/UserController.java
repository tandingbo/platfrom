package com.tanbobo.platfrom.core.controller;

import com.tanbobo.platfrom.base.common.response.ResponseData;
import com.tanbobo.platfrom.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.Writer;

/**
 * 用户信息处理
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/uploadImage", method = RequestMethod.POST)
    public void uploadUserImage(
            @RequestParam(value = "image", required = true) String base64Code,
            @RequestParam(value = "x1", required = true) int x1,
            @RequestParam(value = "y1", required = true) int y1,
            @RequestParam(value = "x2", required = true) int x2,
            @RequestParam(value = "y2", required = true) int y2) {
        String result = userService.uploadUserImage(base64Code, x1, y1, x2, y2);
        if (result.equals("")) {
            ResponseData.buildFailResponseWithMsg("上传用户图像失败");
        } else {
            ResponseData.buildSuccessResponse(result);
        }
    }
}
