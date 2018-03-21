package com.mgmtp.blog.service;

import com.mgmtp.blog.model.PostDTO;

import java.util.List;

public interface PostService {
	
	List<PostDTO> findAll();
	
	List<PostDTO> findByTitle(String title);
	
	PostDTO findById(int id);
	
	boolean addPost(PostDTO post);
	
}