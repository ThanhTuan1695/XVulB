package com.mgmtp.blog.service;

import com.mgmtp.blog.dao.SessionRepository;
import com.mgmtp.blog.model.Session;
import com.mgmtp.blog.setting.SecuritySettings;

import java.security.SecureRandom;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SessionServiceImpl implements SessionService {
	
	private static final int LENGTH = 32;
	private static SecureRandom RANDOM = new SecureRandom();

    @Autowired
    private SessionRepository sessionRepository;
    
    @Autowired
	SecuritySettings securitySettings;
    
    @Override
	public List<Session> findByUsername(String username) {
		return sessionRepository.findByUsername(username);
	}
    
    // Check session and refresh
    @Override
	public List<Session> checkSessionId(String sessionid) {
    		List<Session> result = sessionRepository.findBySessionId(sessionid);    		
    		if(!result.isEmpty())
    			sessionRepository.refreshSession(sessionid);
		return result;
	}
    
    @Override
	public void delBySessionId(String sessionid) {
		sessionRepository.delBySessionId(sessionid);
	}
    
	@Override
	public void addSession(String username, String sessionid) {
		String csrfToken = "";
		switch (securitySettings.getCsrfProtection()) {
			case Token:
			case Both:
				csrfToken = getRandomSessionId();
				break;
			default:
				//do nothing
				break;
		}
		sessionRepository.addSession(username, sessionid, csrfToken);
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
		try {
			byte[]  resBuf = new byte[LENGTH];
			RANDOM.nextBytes(resBuf);
			return Hex.encodeHexString(resBuf);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
   
    
}