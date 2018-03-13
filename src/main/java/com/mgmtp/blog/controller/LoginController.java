package com.mgmtp.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.mgmtp.blog.model.User;
import com.mgmtp.blog.model.Session;
import com.mgmtp.blog.service.UserService;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import com.mgmtp.blog.service.CaptchaService;
import com.mgmtp.blog.service.LoginService;
import com.mgmtp.blog.setting.SecuritySettings;
import com.mgmtp.blog.service.SessionIdGenerator;
import com.mgmtp.blog.service.SessionService;

@Controller
public class LoginController {
	@RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showLoginPage(Model model, HttpServletRequest request) {
		String existCookie = null;
        Cookie[] existcookies = request.getCookies();
        if (existcookies != null) {
            for (Cookie cookie : existcookies) {
                if (cookie.getName().equals("JSESSIONID"))
                    existCookie = cookie.getValue();
            }
        }
        if (existCookie != null)
        		if (!sessionService.findBySessionId(existCookie).isEmpty()) 
        			return "redirect:/home";
        
		
		switch (securitySettings.getPwbruteforce()) {
			case Captcha:
				model.addAttribute("isCaptchaEnabled", true);					
				break;
			case Userlockout:
				// TODO:
				break;
			case False:
				// TODO:
				break;
		}
        return "login";
    }
	
	@Autowired
	LoginService loginService;
	
	@Autowired
	SessionService sessionService;
	
	@Autowired
	CaptchaService captchaService;
	
	@Autowired
	SecuritySettings securitySettings;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String showHomePage(HttpServletRequest request, 
			HttpServletResponse response, Model model) {
		String username = request.getParameter("username");  
		String password = request.getParameter("password"); 
		String g_recaptcha_response = request.getParameter("g-recaptcha-response");
		
		switch (securitySettings.getPwbruteforce()) {
			case Captcha:
				model.addAttribute("isCaptchaEnabled", true);
				if(!captchaService.verifyResponse(g_recaptcha_response)) {
					model.addAttribute("errorMessage", "You're going too fast");
					return "login";
				}
				break;
			case Userlockout:
				// TODO:
				break;
			case False:
				// TODO:
				break;
		}
		
		boolean isValidUser =  loginService.validateUser(username, password);
		
		
		if (!isValidUser) {
			model.addAttribute("errorMessage", "Invalid Credentials");
            return "login";
        }
		String sessionid = SessionIdGenerator.getSessionId();
		Cookie loginCookie = new Cookie("JSESSIONID", sessionid);
		loginCookie.setMaxAge(30*60);
		response.addCookie(loginCookie);
		//delete session of Tomcat server
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("JSESSIONID")) {
                		cookie.setMaxAge(0);
                }
            }
        }
		sessionService.addSession(username, sessionid);
		return "redirect:/home";
        
    }
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String LogoutPage(HttpServletRequest request, HttpServletResponse response) {
		Cookie loginCookie = null;
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("JSESSIONID")) {
                    loginCookie = cookie;
                    break;
                }
            }
        }
		if (loginCookie != null) {
            loginCookie.setMaxAge(0);
            response.addCookie(loginCookie);
            sessionService.delBySessionId(loginCookie.getValue());
        }
		
		return "redirect:/";
	}
	
	@Autowired
    UserService userService;
	@RequestMapping("/home")
    public String home(Model model, HttpServletRequest request) {
		String loginCookie = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("JSESSIONID"))
                    loginCookie = cookie.getValue();
            }
        }
        
        List<Session> session = sessionService.findBySessionId(loginCookie);
        if (session.isEmpty() || loginCookie == null) 
        		return "redirect:/";
        model.addAttribute("username", session.get(0).getUsername());
        
		List<User> users = (List<User>) userService.findAll();
        
        model.addAttribute("users", users);
        return "home";
    }
	
}
