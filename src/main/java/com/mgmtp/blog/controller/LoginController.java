package com.mgmtp.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.ModelAttribute;


import com.mgmtp.blog.model.User;
import com.mgmtp.blog.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.mgmtp.blog.service.LoginService;
import javax.servlet.http.HttpServletRequest;





@Controller
public class LoginController {
	@RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showLoginPage() {
        return "login";
    }
	
	@Autowired
	LoginService loginService;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	//@PostMapping("/login")
	public String showHomePage(@RequestParam("username") String username, 
			@RequestParam("password") String password, Model model) {

		
		System.out.println(username);

		/*boolean isValidUser =  loginService.validateUser(username, password);
		model.addAttribute("valid", isValidUser);
		if (!isValidUser) {
            //model.put("errorMessage", "Invalid Credentials");
            return "index";
        }
        */
		String pass = loginService.findPasswordbyUsername(username);
		model.addAttribute("pass", pass);
        return "index";
    }
	
	@Autowired
    UserService userService;
	@RequestMapping("/home")
    public String home(Model model) {
		List<User> users = (List<User>) userService.findAll();
        
        model.addAttribute("users", users);
        return "home";
    }
	@RequestMapping("/index")
    public String index() {
        return "index";
    }
}
