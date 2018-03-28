package com.mgmtp.blog.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.apache.commons.codec.binary.Hex;
import org.springframework.stereotype.Service;

@Service
public class PasswordServiceImpl implements PasswordService{
	
	// The following constants may be changed without breaking existing hashes.
    
    public static final int PBKDF2_ITERATIONS = 10000;
    public static final int KEY_LENGTH = 256;
	
	//get random password with only lower-case letters
	public String getRandomString(int length) {
        try {
        		int leftLimit = 97; // letter 'a'
        		int rightLimit = 122; // letter 'z'
                Random random = new SecureRandom();
                StringBuilder buffer = new StringBuilder(length);
                for (int i = 0; i < length; i++) {
                    int randomLimitedInt = leftLimit + (int) 
                      (random.nextFloat() * (rightLimit - leftLimit + 1));
                    buffer.append((char) randomLimitedInt);
                }
                String generatedString = buffer.toString();
                return generatedString;
        } catch (Exception e) {
        		e.printStackTrace();
        }
        return null;
    }
	
	public String sha256(String password) {
		try {
			MessageDigest mDigest = MessageDigest.getInstance("SHA-256");
	        byte[] result = mDigest.digest(password.getBytes());
	        StringBuffer sb = new StringBuffer();
	        for (int i = 0; i < result.length; i++) {
	        		System.out.println(result[i]);
	        		String hex = Integer.toHexString(0xff & result[i]);
	            if(hex.length() == 1) sb.append('0');
	            	sb.append(hex);
	        } 
	        return sb.toString();
	    } catch (Exception e) {
	    		e.printStackTrace();
	    }
		return null;
	}

	public List<String> getInitialPassword(int numberOfPass, int passwordLength) {
		try {
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
	    } catch (Exception e) {
	    		e.printStackTrace();
	    }
		return null;
	}
	
	public String pbkdf2 (String password, String salt) {
        try {
            SecretKeyFactory skf = SecretKeyFactory.getInstance( "PBKDF2WithHmacSHA1" );
            char[] pass = password.toCharArray();
            PBEKeySpec spec = new PBEKeySpec(pass, salt.getBytes(), PBKDF2_ITERATIONS, KEY_LENGTH );
            SecretKey key = skf.generateSecret( spec );
            byte[] res = key.getEncoded();
            return Hex.encodeHexString(res);
        } catch ( NoSuchAlgorithmException | InvalidKeySpecException e ) {
            throw new RuntimeException( e );
        }
    }

}
