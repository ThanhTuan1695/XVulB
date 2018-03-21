package com.mgmtp.blog.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mgmtp.blog.model.PostDTO;
import com.mgmtp.blog.service.PostService;

@Controller
public class BaseController {
	
	@Autowired
	PostService postService;

    @RequestMapping("/")
    public String showIndex(Model model) {
    		List<PostDTO> posts = postService.findAll();
		model.addAttribute("posts", posts);
        return "index";
    }
    
    @RequestMapping(value = "/post", method = RequestMethod.GET)
    public String LogoutPage(Model model, HttpServletRequest request) {
    		String query = request.getParameter("id");	
    		PostDTO post = postService.findById(query);
		model.addAttribute("post", post);
		return "blog-post";
	}
}
