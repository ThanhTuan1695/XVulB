package com.mgmtp.blog.dao;

import com.mgmtp.blog.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<User> findAll() {

        List<User> result = jdbcTemplate.query(
                "SELECT * FROM Users",
                (rs, rowNum) -> new User(rs.getLong("id"),
                        rs.getString("username"), rs.getString("password"), rs.getString("firstname"), rs.getString("lastname"))
        );

        return result;

    }
    
    public List<User> findByUsername(String username) {

        List<User> result = jdbcTemplate.query("SELECT * FROM Users WHERE username=?", (rs, rowNum) -> new User(rs.getLong("id"),
                        rs.getString("username"), rs.getString("password"), rs.getString("firstname"), rs.getString("lastname")), username
                        
        		);

        return result;

    }

	// Add new user
    public void addUser(Long id, String username, String password, String firstname, String lastname) {

        jdbcTemplate.update("INSERT INTO Users(id, username, password, firstname, lastname) VALUES (?,?,?,?,?)",
                id, username, password, firstname, lastname);

    }


}