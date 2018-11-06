package com.xiaobai.filters;

import com.xiaobai.utils.HttpClientUtil;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@WebFilter(filterName = "authFilter",urlPatterns = {"/*"})//拦截所有请求
public class AuthFilter implements Filter{
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
        //先验证：该用户是否在远程auth服务器存在有效认证信息
        String accessToken = req.getParameter("accessToken");//改为带accessToken访问
        log.info("--------------------------->accessToken:" + accessToken);
        Map<String,String> paramMap = new HashMap<String,String>();
        paramMap.put("accessToken",accessToken);
        String authInfo = HttpClientUtil.doPost("http://localhost:8081/auth/validate",paramMap);
        if("true".equals(authInfo)) {
            //认证通过，放行
            log.info("--------------------------->accessToken authenticated!!<----------------------------------");
            filterChain.doFilter(req,resp);
        }else {
            ((HttpServletResponse) resp).sendRedirect("http://localhost:8081/auth/login?redirectUrl=" + URLEncoder.encode(((HttpServletRequest) req).getRequestURL().toString(),"UTF-8"));//URL参数编码
        }
    }

    public void destroy() {

    }
}
