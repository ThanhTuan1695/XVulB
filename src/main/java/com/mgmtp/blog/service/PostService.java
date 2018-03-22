package com.mgmtp.blog.service;

import com.mgmtp.blog.model.PostDTO;

import java.util.List;

public interface PostService {
	
	List<PostDTO> findAll();
	
	List<PostDTO> findByTitle(String title, boolean safe);
	
	PostDTO findById(String id, boolean safe);
	
	boolean addPost(PostDTO post);
	
}