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

    @RequestMapping("/")
    public String showIndex(Model model) {
    		List<PostDTO> posts = postService.findAll();
		model.addAttribute("posts", posts);
        return "index";
    }
    
    @RequestMapping(value = "/post", method = RequestMethod.GET)
    public String getPostDetail(Model model, HttpServletRequest request) {
    		String query = request.getParameter("id");
    		
    		//redirect to index with empty query
    		if(query.length() == 0) return "redirect:/";
    		
    		PostDTO post;
    		
    		//check sqlinjection security setting
    		switch (securitySettings.getSqlInjection()) {
			case True:
				post = postService.findById(query,true);			
				break;
			default:
				post = postService.findById(query,false);
				
		}
		model.addAttribute("post", post);
		return "blog-post";
	}
}
