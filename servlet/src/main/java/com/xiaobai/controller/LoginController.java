package com.xiaobai.controller;

import com.xiaobai.utils.HttpClientUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/login")
public class LoginController {//应改为一个过滤器

    @RequestMapping("")
    public String login(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        //使用Cookie保存req原始路径
//        System.out.println("------------------------------>" + req.getRequestURI());
//        System.out.println("------------------------------>" + req.getRequestURL());
//        System.out.println("------------------------------>" + req.getPathInfo());
//        System.out.println("------------------------------>" + req.getServletPath());
//        System.out.println("------------------------------>" + req.getRemoteUser());
//        Cookie cookie = new Cookie("redirectUrl",req.getRequestURL().toString());
//        resp.addCookie(cookie);
//        resp.setHeader("Access-Control-Allow-Origin", "*");
//        resp.setHeader("Access-Control-Allow-Methods",
//                "POST, GET, OPTIONS, DELETE");
//        resp.setHeader("Access-Control-Max-Age", "3600");
//        resp.setHeader("Access-Control-Allow-Headers", "x-requested-with");


//        //先验证：该用户是否在远程auth服务器存在有效认证信息
//        String accessToken = req.getParameter("accessToken");//改为带accessToken访问
//        log.info("--------------------------->accessToken:" + accessToken);
//        Map<String,String> paramMap = new HashMap<String,String>();
//        paramMap.put("accessToken",accessToken);
//        String authInfo = HttpClientUtil.doPost("http://localhost:8081/auth/validate",paramMap);
//        if("true".equals(authInfo)) {
//            //认证通过，放行
//            log.info("--------------------------->accessToken authenticated!!<----------------------------------");
//            return "hello";//暂时均放行为hello页面，实际是用户原始访问资源页面
//        }else {
//            resp.sendRedirect("http://localhost:8081/auth/login?redirectUrl=" + URLEncoder.encode(req.getRequestURL().toString(),"UTF-8"));//URL参数编码
//            return null;
//        }
        log.info("---------------------->Welcome!<----------------------");
        return "hello";
    }
}
