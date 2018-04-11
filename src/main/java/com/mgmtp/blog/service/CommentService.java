package com.mgmtp.blog.service;

import java.util.List;

import com.mgmtp.blog.model.Comment;

public interface CommentService {

	List<Comment> findAllByPostId(String id);
	
	boolean addComment(Comment comment);
}
