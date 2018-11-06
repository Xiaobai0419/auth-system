package com.xiaobai.controller;

import com.xiaobai.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.UUID;
import java.util.concurrent.ConcurrentMap;

@Slf4j
@Controller
@RequestMapping("")
public class LoginController {

    @Autowired
    @Qualifier("userMap")
    private ConcurrentMap<String,Object> userMap;

    //认证登录页面
    @GetMapping("/login")
    public String login(HttpServletRequest req, HttpServletResponse resp) throws UnsupportedEncodingException {
        //被重定向到auth的用户浏览器请求，使用auth session保存该用户Cookie中的原始请求url信息
//        Cookie[] cookies = req.getCookies();
//        for(Cookie cookie : cookies) {
//            System.out.println(cookie.getName() + "/" + cookie.getValue());
//            if("redirectUrl".equals(cookie.getName())) {
//                System.out.println("------------------------->Cookie:" + cookie.getValue());
//                req.getSession().setAttribute("redirectUrl",cookie.getValue());
//                break;
//            }
//        }
        String redirectUrl = req.getParameter("redirectUrl");
        //解码
        redirectUrl = URLDecoder.decode(redirectUrl,"UTF-8");
        log.info("------------------------->redirectUrl:" + redirectUrl);
        //存储redirectUrl，以求在提交认证后获取，进行跳转：分布式环境可存储在分布式缓存中
        req.getSession().setAttribute("redirectUrl",redirectUrl);
        return "login";
    }

    //认证登录处理接口
    @PostMapping("/authentication")
    public String authentication(HttpServletRequest req, HttpServletResponse resp) {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if("xiaobai".equals(username) && "111111".equals(password)) {
            log.info("-------------------->user authenticated!!<------------------------");
            String accessToken = UUID.randomUUID().toString();//生成accessToken并存储
            log.info("-------------------->accessToken:" + accessToken);
            userMap.put(accessToken,username);//以用户accessToken为key,存储认证状态：分布式环境可存储在分布式缓存中
            log.info("-------------------->redirect:" + req.getSession().getAttribute("redirectUrl") + "?accessToken=" + accessToken);
            return "redirect:" + req.getSession().getAttribute("redirectUrl") + "?accessToken=" + accessToken;//认证成功，重定向回原始访问路径，带上accessToken
        }
        req.setAttribute("error","Login failed!Either your username or password is incorrect.");
        return "login";
    }

    //验证认证信息有效性接口
    @PostMapping("/validate")
    @ResponseBody
    public String validate(HttpServletRequest req, HttpServletResponse resp) {
        //获取accessToken,验证是否存在、有效（在有效期内）
        String accessToken = req.getParameter("accessToken");
        if(accessToken != null && userMap.containsKey(accessToken)) {
            return "true";
        }
        return "false";
    }

//    @RequestMapping("/loginSub")
//    public String sub(HttpServletRequest req, HttpServletResponse resp) {
//        String username = req.getParameter("username");
//        String password = req.getParameter("password");
//        if("xiaobai".equals(username) && "111111".equals(password)) {
//            User user = new User();
//            user.setUsername(username);
//            user.setPassword(password);
//            req.getSession().setAttribute("user",user);
//            return "redirect:hello";//只能重定向到Controller路径
//        }
//        req.setAttribute("error","Login failed!Either your username or password is incorrect.");
//        return "login";
//    }

    @RequestMapping("/hello")
    public String hello(HttpServletRequest req, HttpServletResponse resp) {
        return "hello";//转发到jsp页面
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest req, HttpServletResponse resp) {
        req.getSession().removeAttribute("user");
        req.getSession().invalidate();
        req.setAttribute("lgout","Logged Out!");
        return "login";
    }
}
