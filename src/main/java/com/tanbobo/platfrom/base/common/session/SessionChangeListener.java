package com.tanbobo.platfrom.base.common.session;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by tanbobo on 2016/6/30.
 */
public abstract class SessionChangeListener implements ServletContextListener {
    private static long timeout;

    public static long getTimeout() {
        return timeout;
    }

    public void contextInitialized(ServletContextEvent sce) {
        String timeoutStr = sce.getServletContext().getInitParameter("sessionTimeout");
        if (timeoutStr != null && !timeoutStr.equals("")) {
            timeout = Long.parseLong(timeoutStr);
        } else {
            timeout = 3600000;
        }
        subscribeSession();
    }

    public void contextDestroyed(ServletContextEvent sce) {
        release();
    }

    protected abstract void subscribeSession();

    protected abstract void release();
}
