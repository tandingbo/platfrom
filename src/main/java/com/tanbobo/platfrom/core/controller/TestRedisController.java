package com.tanbobo.platfrom.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by tanbobo on 2016/6/29.
 */
@Controller
@RequestMapping("/redis")
public class TestRedisController {

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public void testRedis(){


    }
}
