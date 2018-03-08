package com.mgmtp.blog.service;

import com.mgmtp.blog.dao.UserRepository;
import com.mgmtp.blog.model.User;

import java.util.List;

//import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
	public List<User> findByUsername(String username) {
		return userRepository.findByUsername(username);
	}
    
    @Override
    public List<User> findAll() {
    		return userRepository.findAll();
    }
    
    
    
}