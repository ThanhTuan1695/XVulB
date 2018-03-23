package com.mgmtp.blog.service;

import java.util.List;

public interface PasswordService {
	
	public String getRandomString(int length);
	
	public String sha256(String password);
	
	public List<String> getInitialPassword(int numberOfPass, int passwordLength);
	
	public String pbkdf2 (String password, String salt);
	
}