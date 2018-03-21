package com.mgmtp.blog.service;

import com.mgmtp.blog.dao.PostRepository;
import com.mgmtp.blog.model.Post;

import java.util.List;

//import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Override
	public List<Post> findByTitle(String title) {
		return postRepository.findByTitle(title);
	}
    
    @Override
    public List<Post> findAll() {
    		return postRepository.findAll();
    }

	@Override
	public boolean addPost(Post post) {
		try {
			postRepository.addPost(post);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
    
}