package com.mgmtp.blog.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mgmtp.blog.model.PostDTO;

import com.mgmtp.blog.service.PostService;
import com.mgmtp.blog.service.SessionService;

@Controller
public class BaseController {

	@Autowired
	PostService postService;
	
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

}
