package com.mgmtp.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mgmtp.blog.model.User;
import com.mgmtp.blog.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.mgmtp.blog.service.LoginService;
import com.mgmtp.blog.service.SessionService;



@Controller
public class LoginController {
	@RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showLoginPage() {
        return "login";
    }
	
	@Autowired
	LoginService loginService;
	
	@Autowired
	SessionService sessionService;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String showHomePage(@RequestParam("username") String username, 
			@RequestParam("password") String password, HttpServletResponse response, HttpSession session, Model model) {
		
		boolean isValidUser =  loginService.validateUser(username, password);
		
		
		if (!isValidUser) {
			model.addAttribute("errorMessage", "Invalid Credentials");
            return "login";
        }
		String sessionid = session.getId();
		Cookie loginCookie = new Cookie("SESSIONID", sessionid);
		loginCookie.setMaxAge(30*60);
		response.addCookie(loginCookie);
		sessionService.addSession(username, sessionid);
		return "redirect:/home";
        
    }
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String LogoutPage(HttpServletRequest request, HttpServletResponse response) {
		Cookie loginCookie = null;
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("SESSIONID")) {
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
                if (cookie.getName().equals("SESSIONID"))
                    loginCookie = cookie.getValue();
            }
        }
        
        
        if (sessionService.findBySessionId(loginCookie).isEmpty() || loginCookie == null) return "redirect:/";
        
		List<User> users = (List<User>) userService.findAll();
        
        model.addAttribute("users", users);
        return "home";
    }
	
}
