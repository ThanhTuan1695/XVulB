package com.mgmtp.blog.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mgmtp.blog.model.Post;
import com.mgmtp.blog.model.PostDTO;
import com.mgmtp.blog.model.Session;
import com.mgmtp.blog.model.User;
import com.mgmtp.blog.service.PostService;
import com.mgmtp.blog.service.SessionService;
import com.mgmtp.blog.setting.SecuritySettings;
import com.mgmtp.blog.service.UserService;

@Controller
public class BaseController {

	@Autowired
	PostService postService;

	@Autowired
	SecuritySettings securitySettings;

	@Autowired
	UserService userService;
	
	@Autowired
	SessionService sessionService;

	@RequestMapping("/")
	public String showIndex(Model model, HttpServletRequest request) {
		List<PostDTO> posts = postService.findAll();
		model.addAttribute("posts", posts);

		// check session
		Cookie loginCookie = sessionService.checkLoginCookie(request);
		if (loginCookie != null)
			sessionService.checkSessionId(loginCookie.getValue());
		return "index";
	}

	@RequestMapping(value = "/post", method = RequestMethod.GET)
	public String getPostDetail(Model model, HttpServletRequest request) {
		String query = request.getParameter("id");

		// redirect to index with empty query
		if (query.length() == 0)
			return "redirect:/";

		PostDTO post;

		// check sql injection security setting
		switch (securitySettings.getSqlInjection()) {
		case True:
			post = postService.findById(query, true);
			break;
		default:
			post = postService.findById(query, false);

		}
		model.addAttribute("post", post);

		// check session
		Cookie loginCookie = sessionService.checkLoginCookie(request);
		if (loginCookie != null)
			sessionService.checkSessionId(loginCookie.getValue());
		return "blog-post";
	}
	
	@RequestMapping(value = "/post", method = RequestMethod.POST)
	public String showHomePage(HttpServletRequest request, 
			HttpServletResponse response, Model model) {
		String postTitle = request.getParameter("post-title"); 
		String postContent = request.getParameter("post-content"); 
		Cookie loginCookie = sessionService.checkLoginCookie(request);
		List<Session> sessions;
		if (loginCookie != null) {
			sessions = sessionService.checkSessionId(loginCookie.getValue());
    			if (!sessions.isEmpty()) {
    				List<User> users = userService.findByUsername(sessions.get(0).getUsername());
    				Post post = new Post(postTitle, postContent, users.get(0));
    				if(postService.addPost(post)) {
    					model.addAttribute("isSuccess", true);
    				}
    				else {
    					model.addAttribute("isSuccess", false);
    				}
	    	        return "redirect:/home";
    			}
		}
		return "redirect:/";
	

	}
}
