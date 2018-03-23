package com.mgmtp.blog.service;

import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface PasswordService {
	String getRandomString(int length);
	String sha256(String password) throws NoSuchAlgorithmException;
	List<String> getInitialPassword();
}
