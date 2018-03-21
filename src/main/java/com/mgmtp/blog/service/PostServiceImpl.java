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
	public List<PostDTO> findByTitle(String title, boolean safe) {
		List<PostDTO> result = new ArrayList<>();
		for (Post item : postRepository.findByTitle(title,safe)) {
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

	@Override
	public PostDTO findById(String id) {
		List<Post> posts = postRepository.findById(id);
		if(posts.size()==0) {
			return null;
		}
		return PostDTO.fromPostModel(posts.get(0));
	}
    
}