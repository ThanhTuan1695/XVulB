package com.mgmtp.blog.dao;

import com.mgmtp.blog.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<User> findAll() {

        List<User> result = jdbcTemplate.query( "SELECT * FROM Users",
                									(rs, rowNum) -> new User(rs.getLong("id"), 
                															rs.getString("username"), 
                															rs.getString("password"), 
                															rs.getString("firstname"), 
                															rs.getString("lastname"))
        										 );

        return result;

    }
    
    public List<User> findByUsername(String username) {

        List<User> result = jdbcTemplate.query( "SELECT * FROM Users WHERE username=?", 
        										   (rs, rowNum) -> new User( rs.getLong("id"),
        												   					rs.getString("username"), 
        												   					rs.getString("password"), 
        												   					rs.getString("firstname"), 
        												   					rs.getString("lastname")), 
        										   				   username
                        
        										 );

        return result;

    }
    
    public List<User> findById(Long id) {

    		List<User> result = jdbcTemplate.query( "SELECT * FROM Users WHERE id=?", 
        										   (rs, rowNum) -> new User( rs.getLong("id"),
        												   					rs.getString("username"), 
        												   					rs.getString("password"), 
        												   					rs.getString("firstname"), 
        												   					rs.getString("lastname")), 
        										   				   id
                        
        										 );
        return result;

    }

	// Add new user
    public void addUser(User user) {
        jdbcTemplate.update("INSERT INTO Users(username, password, firstname, lastname) VALUES (?,?,?,?)",
        		user.getUsername(), user.getPassword(), user.getFirstname(), user.getLastname());

    }
    
    public void addListUser(List<User> users) {
    		for(User user: users) {
    			addUser(user);
    		}
    }
    
    public void deleteAll() {
    		//delete all records and reset id to 1 - n
    		jdbcTemplate.execute("DELETE FROM Users; ALTER SEQUENCE users_id_seq  RESTART WITH 1;"); 
    }

}