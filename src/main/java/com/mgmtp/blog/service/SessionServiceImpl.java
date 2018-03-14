package com.mgmtp.blog.service;

import com.mgmtp.blog.dao.SessionRepository;
import com.mgmtp.blog.model.Session;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SessionServiceImpl implements SessionService {

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
	public void updateSession(String username, String sessionid) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public Cookie checkLoginCookie(HttpServletRequest request) {
		Cookie loginCookie = null;
		Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("JSESSIONID"))
                    loginCookie = cookie;
                		break;
            }
        }
		return loginCookie;
	}
   
    
}