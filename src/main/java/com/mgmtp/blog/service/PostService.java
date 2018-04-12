package com.mgmtp.blog.service;

import com.mgmtp.blog.model.PostDTO;
import com.mgmtp.blog.model.Post;

import java.util.List;

public interface PostService {
	
	List<PostDTO> findAll();
	
	List<PostDTO> findByTitle(String title, boolean safe);
	
	PostDTO findById(String id, boolean safe);
	Post findById(String id);
	
	boolean addPost(Post post);
	
}