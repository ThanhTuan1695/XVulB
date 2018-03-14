package com.mgmtp.blog.service;

import com.mgmtp.blog.dao.SessionRepository;
import com.mgmtp.blog.model.Session;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SessionServiceImpl implements SessionService {
	
	private static final int LENGTH = 32;
	private static SecureRandom random = new SecureRandom();

    @Autowired
    private SessionRepository sessionRepository;

    @Override
	public List<Session> findByUsername(String username) {
		return sessionRepository.findByUsername(username);
	}
    
    @Override
	public List<Session> findBySessionId(String sessionid) {
		return sessionRepository.findBySessionId(sessionid);
	}
    
    @Override
	public void delBySessionId(String sessionid) {
		sessionRepository.delBySessionId(sessionid);
	}
    
	@Override
	public void addSession(String username, String sessionid) {
		sessionRepository.addSession(username, sessionid);
	}

	@Override
	public Cookie checkLoginCookie(HttpServletRequest request) {
		Cookie loginCookie = null;
		Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("SESSIONID"))
                    loginCookie = cookie;
                		break;
            }
        }
		return loginCookie;
	}

	@Override
	public String getRandomSessionId() {
		
		BigInteger bigInteger = new BigInteger(130, random);
		String sessionId = String.valueOf(bigInteger.toString(LENGTH));
		return sessionId.toUpperCase();
		
	}
   
    
}