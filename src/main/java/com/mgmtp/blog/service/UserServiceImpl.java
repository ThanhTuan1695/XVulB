package com.mgmtp.blog.service;

import com.mgmtp.blog.dao.UserRepository;
import com.mgmtp.blog.model.User;
import com.mgmtp.blog.setting.SecuritySettings;

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
    
    @Autowired
	SecuritySettings securitySettings;

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

	@Override
	public boolean resetAllPassword() {
		try {
			switch (securitySettings.getPwStorage()) {
				case Clear:
					List<String>passwords = passwordService.getInitialPassword();
					userRepository.resetAllPassword(passwords);
					break;
				case Hashed:
					// TODO:
					break;
				case SaltHashed:
					// TODO:
					break;
				case PBKDF2:
					break;
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}
    
}