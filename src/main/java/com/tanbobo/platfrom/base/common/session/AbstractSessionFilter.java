package com.tanbobo.platfrom.base.common.session;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by tanbobo on 2016/6/30.
 */
public abstract class AbstractSessionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        doFilterInternal((HttpServletRequest) request, (HttpServletResponse) response);
        chain.doFilter(request, response);
    }

    protected abstract void doFilterInternal(HttpServletRequest request, HttpServletResponse response);

    @Override
    public void destroy() {

    }

}
