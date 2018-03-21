package com.mgmtp.blog.service;

import com.mgmtp.blog.dao.PostRepository;
import com.mgmtp.blog.model.Post;
import com.mgmtp.blog.model.PostDTO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Override
	public List<PostDTO> findByTitle(String title) {
		List<PostDTO> result = new ArrayList<>();
		for (Post item : postRepository.findByTitle(title)) {
			result.add(PostDTO.fromPostModel(item));
		}
		return result;
	}
    
    @Override
    public List<PostDTO> findAll() {
    	List<PostDTO> result = new ArrayList<>();
		for (Post item : postRepository.findAll()) {
			result.add(PostDTO.fromPostModel(item));
		}
    		return result;
    }

	@Override
	public boolean addPost(PostDTO post) {
		try {
//			postRepository.addPost(post);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
    
}