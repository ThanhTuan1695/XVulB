package com.mgmtp.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mgmtp.blog.dao.CommentRepository;
import com.mgmtp.blog.model.Comment;

@Service
public class CommentServiceImpl implements CommentService {
	@Autowired
	private CommentRepository commentRepository;
	
	@Override
	public List<Comment> findAllByPostId(String id) {
		return commentRepository.findAllByPostId(id);
	}
	
	@Override
	public boolean addComment(Comment comment) {
		return commentRepository.addComment(comment);
		
	}
}
