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
	
	private static final int LENGTH_OF_RANDOM_PASS = 5;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordServiceImpl passwordService;
    
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
			int numberOfUsers = findAll().size();
			List<String> passwords = passwordService.getInitialPassword(numberOfUsers, LENGTH_OF_RANDOM_PASS);
			List<String> hashedPasswords = new ArrayList<>();
			switch (securitySettings.getPwStorage()) {
				case Clear:
					userRepository.resetAllPassword(passwords);
					break;
				case Hashed:
					
					for(String item: passwords) {
						hashedPasswords.add(passwordService.sha256(item));
					}
					userRepository.resetAllPassword(hashedPasswords);
					break;
				case SaltHashed:
					List<String> salts = new ArrayList<>();
					String salt;
					for(String item: passwords) {
						salt = passwordService.getNextSalt();
						hashedPasswords.add(passwordService.sha256(salt + item));
						salts.add(salt);
					}
					userRepository.updateSaltColumn(salts);
					userRepository.resetAllPassword(hashedPasswords);
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