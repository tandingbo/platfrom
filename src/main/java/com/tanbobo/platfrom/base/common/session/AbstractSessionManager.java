package com.tanbobo.platfrom.base.common.session;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tanbobo on 2016/6/30.
 */
public abstract class AbstractSessionManager {
    private Map sessions = new HashMap();

    public AbstractSessionManager() {
    }

    public HttpSession getSession(String sid) {
        return (HttpSession) sessions.get(sid);
    }

    public void addSession(HttpSession session, String sid) {
        this.sessions.put(sid, session);
    }

    public abstract void loadSession();

    public Map getAllSession() {
        return sessions;
    }

    public String getSessionIdByCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return null;
        }
        for (int i = 0; i < cookies.length; i++) {
            Cookie cookie = cookies[i];
            if ("sid".equals(cookie.getName())) {
                String sid = cookie.getValue();
                return sid;
            }
        }
        return null;
    }
}
