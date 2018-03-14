package com.mgmtp.blog.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BaseController {


    @RequestMapping("/")
    public String showIndex(HttpServletRequest request, HttpServletResponse response) {
        return "index";
    }
}
