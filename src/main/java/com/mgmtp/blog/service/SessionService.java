package com.mgmtp.blog.service;

import com.mgmtp.blog.model.Session;
import java.util.List;

public interface SessionService {
	List<Session> findByUsername(String username);
	List<Session> findBySessionId(String sessionid);
	void delBySessionId(String sessionid);
	void addSession(String username, String sessionid);
	void updateSession(String username, String sessionid);
}