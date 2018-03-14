package com.mgmtp.blog.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Sessions")
public class Session {
    

    @Id
    private String username;
    private String sessionid;

    public Session() {}

    public Session(String username, String sessionid) {
        this.username = username;
        this.sessionid = sessionid;     
    }

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSessionid() {
		return sessionid;
	}

	public void setSessionid(String sessionid) {
		this.sessionid = sessionid;
	}
    
}
