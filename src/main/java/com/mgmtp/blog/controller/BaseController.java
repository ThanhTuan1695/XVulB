package com.mgmtp.blog.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mgmtp.blog.model.Post;
import com.mgmtp.blog.service.PostService;

@Controller
public class BaseController {
	
	@Autowired
	PostService postService;

    @RequestMapping("/")
    public String showIndex(Model model, HttpServletRequest request, HttpServletResponse response) {
    		List<Post> posts = (List<Post>) postService.findAll();
		model.addAttribute("posts", posts);
        return "index";
    }
}
