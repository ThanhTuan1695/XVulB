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
	public static final int SALT_BYTES = 8;

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
		
		// Encode password before validating
		try {
			switch (securitySettings.getPwStorage()) {
			case Clear:
				// DO NOTHING		
				break;
			case Hashed:
				password = passwordService.sha256(password);
				break;
			case SaltHashed:
				password = passwordService.sha256(users.get(0).getSalt() + password);
				break;
			case PBKDF2:
				password = passwordService.pbkdf2(password, users.get(0).getSalt());
				break;
			}
		} catch (Exception e) {
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
					userRepository.resetAllSalt();
					break;
				case Hashed:
					for(String item: passwords) {
						hashedPasswords.add(passwordService.sha256(item));
					}
					userRepository.resetAllPassword(hashedPasswords);
					userRepository.resetAllSalt();
					break;
				case SaltHashed:
					List<String> salts = new ArrayList<>();
					for(String item: passwords) {
						String salt = passwordService.getRandomString(SALT_BYTES);
						hashedPasswords.add(passwordService.sha256(salt + item));
						salts.add(salt);
					}
					userRepository.updateSaltColumn(salts);
					userRepository.resetAllPassword(hashedPasswords);
					break;
				case PBKDF2:
					List<String> saltList = new ArrayList<>();
					for(String item: passwords) {
						String salt = passwordService.getRandomString(SALT_BYTES);
						hashedPasswords.add(passwordService.pbkdf2(item, salt));
						saltList.add(salt);
					}
					userRepository.updateSaltColumn(saltList);
					userRepository.resetAllPassword(hashedPasswords);
					break;
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}
    
}