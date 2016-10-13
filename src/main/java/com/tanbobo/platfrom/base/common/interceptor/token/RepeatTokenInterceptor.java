package com.tanbobo.platfrom.base.common.interceptor.token;


import io.netty.util.internal.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.UUID;

/**
 * 防止下单页重复提交
 */
public class RepeatTokenInterceptor extends HandlerInterceptorAdapter {
    private static Logger log = Logger.getLogger(RepeatTokenInterceptor.class);

    /***
     * 用于前端页面接收服务器端的token
     */
    private static final String SESSION_KEY_REPEATTOKEN = "repeattoken";

    /***
     * 用于session存储n个token
     */
    private static final String SESSION_KEY_REPEATTOKEN_POOL = "tokenpool";

    private void addRepeatToken(HttpServletRequest request, HttpServletResponse response) {
        HttpSession httpSession = request.getSession(true);
        String token = (String) httpSession.getAttribute(SESSION_KEY_REPEATTOKEN_POOL);
        System.out.println("addRepeatToken token:" + token);
        String createdToken = UUID.randomUUID().toString();
        httpSession.setAttribute(SESSION_KEY_REPEATTOKEN, createdToken);//给前端页面用的
        if (StringUtil.isNullOrEmpty(token)) {
            httpSession.setAttribute(SESSION_KEY_REPEATTOKEN_POOL, createdToken);
        } else {
            httpSession.setAttribute(SESSION_KEY_REPEATTOKEN_POOL, createdToken + "###" + token);
        }
    }

    private void removeRepeatToken(HttpServletRequest request, HttpServletResponse response) {
        HttpSession httpSession = request.getSession(true);
        String token = (String) httpSession.getAttribute(SESSION_KEY_REPEATTOKEN_POOL);
        System.out.println("removeRepeatToken token:" + token);
        if (!StringUtil.isNullOrEmpty(token)) {
            String clientToken = request.getParameter(SESSION_KEY_REPEATTOKEN);
            System.out.println("removeRepeatToken serverToken:" + clientToken);
            if (clientToken == null) {
                return;
            }
            token = token.replace(clientToken, "").replace("######", "###");
            System.out.println("token:" + token);
            httpSession.setAttribute(SESSION_KEY_REPEATTOKEN_POOL, token);
        }
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            RepeatSubmitToken annotation = method.getAnnotation(RepeatSubmitToken.class);
            if (annotation != null) {
                boolean needSaveSession = annotation.save();
                if (needSaveSession) {
                    addRepeatToken(request, response);
                }
                boolean needRemoveSession = annotation.remove();
                if (needRemoveSession) {
                    if (isRepeatSubmit(request)) {
                        log.warn("please don't repeat submit,url:" + request.getServletPath());
                        response.sendRedirect("/warn.html?code=repeatSubmitOrder&orgId=" + request.getParameter("orgId"));
                        return false;
                    }
                    removeRepeatToken(request, response);
                }
            }
            return true;
        } else {
            return super.preHandle(request, response, handler);
        }
    }

    private boolean isRepeatSubmit(HttpServletRequest request) {
        //从池子里面获取token
        String serverToken = (String) request.getSession(true).getAttribute(SESSION_KEY_REPEATTOKEN_POOL);
        if (serverToken == null || "###".equals(serverToken)) {
            return true;
        }
        String clinetToken = request.getParameter(SESSION_KEY_REPEATTOKEN);//请求要素
        if (StringUtils.isBlank(clinetToken)) {
            return true;
        }
        System.out.println("clinetToken:" + clinetToken);
        if (!serverToken.contains(clinetToken)) {
            return true;
        }
        return false;
    }
}
