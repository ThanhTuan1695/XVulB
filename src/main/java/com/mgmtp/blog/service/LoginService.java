package com.mgmtp.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.mgmtp.blog.service.UserServiceImpl;
import com.mgmtp.blog.model.User;


@Service
public class LoginService {
	@Autowired
	UserServiceImpl UserServiceImpl;
		
    public boolean validateUser(String username, String password) {
    		User user = (User) UserServiceImpl.findByUsername(username);
    		
    		return user.getPassword() == password;
    }
    public String findPasswordbyUsername(String username) {
		User user = (User) UserServiceImpl.findByUsername(username);
		
		return user.getPassword();
    }

}