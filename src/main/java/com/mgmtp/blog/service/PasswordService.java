package com.mgmtp.blog.service;

import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface PasswordService {
	
	public String getRandomString(int length);
	
	public String sha256(String password) throws NoSuchAlgorithmException;
	
	public String getNextSalt();
	
	public List<String> getInitialPassword(int numberOfPass, int passwordLength);
	
}
