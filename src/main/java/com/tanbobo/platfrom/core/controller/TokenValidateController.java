package com.tanbobo.platfrom.core.controller;

import com.tanbobo.platfrom.base.common.interceptor.token.RepeatSubmitToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 */
@Controller
@RequestMapping("/token")
public class TokenValidateController {

    @RequestMapping(value = "/pager", method = RequestMethod.GET)
    @RepeatSubmitToken(save = true)
    public ModelAndView gonext(String id) {
        ModelAndView mv = new ModelAndView();

        mv.setViewName("token");
        mv.addObject("token", id);
        return mv;
    }

    @RequestMapping(value = "/paysave", method = RequestMethod.POST)
    @RepeatSubmitToken(remove = true)
    public ModelAndView paysave(String id) {
        ModelAndView mv = new ModelAndView();
        return mv;
    }
}
