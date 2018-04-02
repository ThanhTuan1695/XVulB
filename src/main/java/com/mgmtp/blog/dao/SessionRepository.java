package com.mgmtp.blog.dao;

import com.mgmtp.blog.model.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class SessionRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<Session> findByUsername(String username) {
		try {
			List<Session> result = jdbcTemplate.query("SELECT * FROM Sessions WHERE username=?",
					(rs, rowNum) -> new Session(rs.getString("username"), rs.getString("sessionid")), username);
			return result;
		} catch (Exception e) {
			e.getStackTrace();
		}
		return null;
	}

	public List<Session> findBySessionId(String sessionid) {
		try {
			List<Session> result = jdbcTemplate.query("SELECT * FROM Sessions WHERE sessionid=? AND NOW() < expiretime",
					(rs, rowNum) -> new Session(rs.getString("username"), rs.getString("sessionid")), sessionid);
			return result;
		} catch (Exception e) {
			e.getStackTrace();
		}
		return null;
	}

	public void delBySessionId(String sessionid) {
		try {
			jdbcTemplate.update("DELETE FROM Sessions WHERE sessionid=?", sessionid);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	// Add new session
	public void addSession(String username, String sessionid) {
		try {
			jdbcTemplate.update("INSERT INTO sessions(username, sessionid, expiretime) VALUES (?,?, NOW()+time'00:30')", username,
					sessionid);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Refresh session
	// Delete if current time is greater than expire time that means the session is
	// already expired
	// Update expire time
	public void refreshSession(String sessionid) {
		try {
			jdbcTemplate.update("UPDATE sessions SET expiretime=NOW()+time'00:30' WHERE sessionid=?", sessionid);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}