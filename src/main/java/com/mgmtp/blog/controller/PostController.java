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

import com.mgmtp.blog.model.Comment;
import com.mgmtp.blog.model.Post;
import com.mgmtp.blog.model.PostDTO;
import com.mgmtp.blog.model.Session;
import com.mgmtp.blog.model.User;
import com.mgmtp.blog.service.CommentService;
import com.mgmtp.blog.service.PostService;
import com.mgmtp.blog.service.SessionService;
import com.mgmtp.blog.service.UserService;
import com.mgmtp.blog.setting.SecuritySettings;

@Controller
public class PostController {


	@Autowired
	PostService postService;
	
	@Autowired
	CommentService commentService;

	@Autowired
	SecuritySettings securitySettings;

	@Autowired
	UserService userService;
	
	@Autowired
	SessionService sessionService;

	@RequestMapping(value = "/post", method = RequestMethod.GET)
	public String getPostDetail(Model model, HttpServletRequest request) {
		String id = request.getParameter("id");

		// redirect to index with empty query
		if (id.length() == 0)
			return "redirect:/";

		PostDTO post;

		// check SQL injection security setting
		switch (securitySettings.getSqlInjection()) {
		
			case True:
				post = postService.findById(id, true);
				break;
				
			default:
				post = postService.findById(id, false);

		}
		model.addAttribute("post", post);
		
		//check XSS prevention security setting
		switch (securitySettings.getXssPrevention()) {
			case True:
				model.addAttribute("xssPrevention", "True");				
				break;
			default:
				break;
		}
		
		List<Comment> comments = commentService.findAllByPostId(id);
		model.addAttribute("comments", comments);

		// check session
		Cookie loginCookie = sessionService.checkLoginCookie(request);
		if (loginCookie != null)
			sessionService.checkSessionId(loginCookie.getValue());
		return "blog-post";
	}
	
	@RequestMapping(value = "/new-post", method = { RequestMethod.GET, RequestMethod.POST } )
	public String addNewPost(HttpServletRequest request, 
			HttpServletResponse response, Model model) {
		try {
			String postTitle = request.getParameter("post-title"); 
			String postContent = request.getParameter("post-content"); 
			
			List<User> users;
			switch (securitySettings.getVerticalEscalation()) {
			
				case True:
					Cookie loginCookie = sessionService.checkLoginCookie(request);
					List<Session> sessions = loginCookie != null ? sessionService.checkSessionId(loginCookie.getValue()) : null;
					if (loginCookie == null || sessions.isEmpty()) {
						return "redirect:/";
					}
					
					switch (securitySettings.getCsrfProtection()) {
						case Token:
						case Both:
							String csrfToken = !request.getParameter("csrfToken").isEmpty() ? request.getParameter("csrfToken") : "";
							if(!sessions.get(0).getCsrfToken().equals(csrfToken)) {
								model.addAttribute("isSuccess", false);
								return "redirect:/";
							}
							break;
						default:
							//do nothing
							break;
					}
					
					users = userService.findByUsername(sessions.get(0).getUsername());
					break;
					
				default:
					String username = request.getParameter("username"); 
					users = userService.findByUsername(username);
					break;
			}
			
	    		Post post = new Post(postTitle, postContent, users.get(0));
	    		if(postService.addPost(post)) {
	    			model.addAttribute("isSuccess", true);
	    		}
	    		else {
	    			model.addAttribute("isSuccess", false);
	    		}
	    		
		} catch (Exception e) {
			e.printStackTrace();
		}
	    	return "redirect:/home";
	}
	
	@RequestMapping(value = "/post", params = "id", method = RequestMethod.POST)
	public String getPostDetail(HttpServletRequest request, 
			HttpServletResponse response, Model model) {
		String id = request.getParameter("id"); 
		String comment = request.getParameter("comment"); 
		String author = request.getParameter("author"); 
		
		if(comment.isEmpty() || author.isEmpty()) 
			return "redirect:/post?id="+id;
		
		Post post = postService.findById(id);
		
		Comment c = new Comment(author, comment, post);
		commentService.addComment(c);
		
		return "redirect:/post?id="+id;
	

	}
}
