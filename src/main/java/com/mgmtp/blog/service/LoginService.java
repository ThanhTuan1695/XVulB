package com.mgmtp.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mgmtp.blog.service.UserServiceImpl;
import com.mgmtp.blog.model.User;
import java.util.List;


@Service
public class LoginService {
	@Autowired
	UserServiceImpl UserServiceImpl;
		
    public boolean validateUser(String username, String password) {
    		List<User> users = (List<User>) UserServiceImpl.findByUsername(username);
    		
    		if(users == null || users.isEmpty()) {
    			return false;
    		}
    		
    		return password.equals(users.get(0).getPassword());
    }
    

}