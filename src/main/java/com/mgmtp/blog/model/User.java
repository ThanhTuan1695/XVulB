package com.mgmtp.blog.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Users")
public class User {
    
    private Long id;
    
    @Id
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String salt;
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Post> posts;
   
    public User() {
    }

    public User(Long id, String username, String password, String firstname, String lastname) {
    		this.id = id;
    		this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;   
    }
    
    public User(Long id, String username, String password, String firstname, String lastname, String salt) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.salt = salt;
    }
    
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public String getFirstname() {
        return firstname;
    }
    public String getLastname() {
        return lastname;
    }
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
		this.id = id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	@Override
    public String toString() {
        return "User{" + "id=" + id + ", username=" + username
                + ", password=" + password + ", firstname=" + firstname
                + ", lastname=" + lastname
                + '}';
    }

	public String getSalt() {	
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}
}
