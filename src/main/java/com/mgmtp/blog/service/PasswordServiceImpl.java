package com.mgmtp.blog.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class PasswordServiceImpl implements PasswordService{
	private static final Random RANDOM = new SecureRandom();
	
	public String getRandomString(int length) {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomLimitedInt = leftLimit + (int) 
              (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        String generatedString = buffer.toString();
        return generatedString;
    }
	
	public String sha256(String password) throws NoSuchAlgorithmException {
		MessageDigest mDigest = MessageDigest.getInstance("SHA-256");
        byte[] result = mDigest.digest(password.getBytes());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < result.length; i++) {
        		String hex = Integer.toHexString(0xff & result[i]);
            if(hex.length() == 1) sb.append('0');
            	sb.append(hex);
        } 
        return sb.toString();
	}
	
	public String getNextSalt() {
	    byte[] salt = new byte[8];
	    RANDOM.nextBytes(salt);
	    return new String(salt);
	}

	public List<String> getInitialPassword(int numberOfPass, int passwordLength) {
		List<String> result = new ArrayList<>();
		result.add("admin123");
		result.add("world");
		result.add("billionaire");
		result.add("black");
		result.add("green");
		for (int i=0; i<numberOfPass-5;i++) {
			result.add(getRandomString(passwordLength));
		}
		return result;
	}

}
