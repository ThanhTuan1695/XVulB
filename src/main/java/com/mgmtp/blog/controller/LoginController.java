package com.mgmtp.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.mgmtp.blog.model.*;
import com.mgmtp.blog.service.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.mgmtp.blog.setting.SecuritySettings;

@Controller
public class LoginController {
	
	@Autowired
	SessionService sessionService;
	
	@Autowired
	CaptchaService captchaService;
	
	@Autowired
	SecuritySettings securitySettings;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showLoginPage(Model model, HttpServletRequest request) {
		Cookie loginCookie = sessionService.checkLoginCookie(request);
		
        if (loginCookie != null)
        		if (!sessionService.findBySessionId(loginCookie.getValue()).isEmpty()) 
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
		
		boolean isValidUser =  userService.validateUser(username, password);
		
		
		if (!isValidUser) {
			model.addAttribute("errorMessage", "Invalid Credentials");
            return "login";
        }
		
		String sessionid = "";
		switch (securitySettings.getSsFixation()) {
			case True:
				sessionid = sessionService.getRandomSessionId();
				break;
			case False:
				Cookie loginCookie =  sessionService.checkLoginCookie(request);
				if (loginCookie != null) {
		            sessionid = loginCookie.getValue();
		        }
				else {
					sessionid = sessionService.getRandomSessionId();
				}
				break;
		}
		
		Cookie loginCookie = new Cookie("SESSIONID", sessionid);
		loginCookie.setMaxAge(30*60);
		response.addCookie(loginCookie);
		sessionService.addSession(username, sessionid);
		return "redirect:/home";
        
    }
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String LogoutPage(HttpServletRequest request, HttpServletResponse response) {
		Cookie loginCookie =  sessionService.checkLoginCookie(request);
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
		Cookie loginCookie = sessionService.checkLoginCookie(request);
		System.out.println("TEST: "+loginCookie);
		List<Session> sessions;
		if (loginCookie != null) {
			sessions = sessionService.findBySessionId(loginCookie.getValue());
    			if (!sessions.isEmpty()) {
    				model.addAttribute("username", sessions.get(0).getUsername());	
	    			List<User> users = (List<User>) userService.findAll();
	    			model.addAttribute("users", users);
	    	        return "home";
    			}
		}
		return "redirect:/";
    }
	
}
