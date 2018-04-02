package com.mgmtp.blog.service;

import com.mgmtp.blog.model.Session;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Cookie;

public interface SessionService {
	
	List<Session> findByUsername(String username);
	
	List<Session> checkSessionId(String sessionid);
	
	void delBySessionId(String sessionid);
	
	void addSession(String username, String sessionid);
	
	Cookie checkLoginCookie(HttpServletRequest request);
	
	String getRandomSessionId();
	
}