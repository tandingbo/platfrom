package com.tanbobo.platfrom.core.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 */
@Controller
public class TestLogbackController {
    //定义一个全局的记录器，通过LoggerFactory获取
    private final static Logger logger = LoggerFactory.getLogger(TestLogbackController.class);

    @RequestMapping("/logback/test")
    @ResponseBody
    public void testLogback() {

        logger.info("KUAIFA_CREATE_PACK_POST_INFO，id={}，查询打包结果信息：{}", 1, "操作成功！");

    }
}
