package com.xiaobai.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/controller")
public class HttpXController {

    @RequestMapping("/xiaobai")
    @ResponseBody
    public String xiaobai(HttpServletRequest req, HttpServletResponse resp) {
        return "frameHi!";
    }

    @RequestMapping("/frame")
    public String frame(HttpServletRequest req, HttpServletResponse resp) {
        req.setAttribute("frame","spring5");
        return "mvc";
    }
}
