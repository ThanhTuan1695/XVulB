package com.mgmtp.blog.controller;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BaseController {


    @RequestMapping("/")
    public String showIndex(HttpServletRequest request, HttpServletResponse response) {
    		Cookie[] cookies = request.getCookies();
//		if (cookies != null) {
//            for (Cookie cookie : cookies) {
//                if (cookie.getName().equals("JSESSIONID")) {
//                    cookie.setSecure(true);
//                    response.addCookie(cookie);
//                    break;
//                }
//            }
//        }
        return "index";
    }
}
