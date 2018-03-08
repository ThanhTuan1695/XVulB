package com.mgmtp.blog.model;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

    public User() {
    }

    public User(Long id, String username, String password, String firstname, String lastname) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return username;
    }
    public String getFirstname() {
        return username;
    }
    public String getLastname() {
        return username;
    }
    public Long getId() {
        return id;
    }
/*
    public String getUsername() {
        return username;
    }
    public 

    public void setUsername(String username) {
        this.username = username;
    }
*/
    
    
    @Override
    public String toString() {
        return "User{" + "id=" + id + ", username=" + username
                + ", password=" + password + ", firstname=" + firstname
                + ", lastname=" + lastname
                + '}';
    }
}
