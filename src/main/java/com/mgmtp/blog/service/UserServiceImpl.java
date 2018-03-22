package com.mgmtp.blog.service;

import com.mgmtp.blog.dao.UserRepository;
import com.mgmtp.blog.model.User;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordService passwordService;

    @Override
	public List<User> findByUsername(String username) {
		return userRepository.findByUsername(username);
	}
    
    @Override
    public List<User> findAll() {
    		return userRepository.findAll();
    }

	@Override
	public boolean validateUser(String username, String password) {
		List<User> users = (List<User>) findByUsername(username);
		if(users == null || users.isEmpty()) {
			return false;
		}
		return password.equals(users.get(0).getPassword());
	}

	public boolean resetUsersTable() {
		try {
			if(!findAll().isEmpty()) {
				userRepository.deleteAll();
			}
			List <User> users = get10InitialUsers();
			userRepository.addListUser(users);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	private List<User> get10InitialUsers(){
		List<User> result = new ArrayList<>();
		result.add(new User("admin","admin123","Bob","Builder"));
		result.add(new User("spiderman","world","Peter","Parker"));
		result.add(new User("batman","billionaire","Bruce","Wayne"));
		result.add(new User("blackwidow","black","Natalia","Romanova"));
		result.add(new User("hulk","green","Bruce","Banner"));
		result.add(new User("random1",passwordService.getRandomString(5),"anonymous","Xvulb"));
		result.add(new User("random2",passwordService.getRandomString(5),"anonymous","Xvulb"));
		result.add(new User("random3",passwordService.getRandomString(5),"anonymous","Xvulb"));
		result.add(new User("random4",passwordService.getRandomString(5),"anonymous","Xvulb"));
		result.add(new User("random5",passwordService.getRandomString(5),"anonymous","Xvulb"));
		return result;
	}
    
}