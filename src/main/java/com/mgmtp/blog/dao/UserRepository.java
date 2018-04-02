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
    		try {
    			List<User> result = jdbcTemplate.query( "SELECT * FROM Users",
						(rs, rowNum) -> new User(rs.getLong("id"), 
												rs.getString("username"), 
												rs.getString("password"), 
												rs.getString("firstname"), 
												rs.getString("lastname"),
												rs.getString("salt"))
					 );
    			return result;
    		} catch (Exception e) {
    			e.getStackTrace();
    		}
        return null;
    }
    
    public List<User> findByUsername(String username) {
    		try {
    			List<User> result = jdbcTemplate.query( "SELECT * FROM Users WHERE username=?", 
						   (rs, rowNum) -> new User( rs.getLong("id"),
								   					rs.getString("username"), 
								   					rs.getString("password"), 
								   					rs.getString("firstname"), 
								   					rs.getString("lastname"),
								   					rs.getString("salt")), 
						   				   username
						 );

    			return result;
    		} catch(Exception e) {
    			e.printStackTrace();
    		}
        return null;
    }
    
    public List<User> findById(Long id) {
    		try {
    			
    		} catch (Exception e) {
    			
    		}
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
    		try {
    			jdbcTemplate.update("INSERT INTO Users(username, password, firstname, lastname) VALUES (?,?,?,?)",
    	        		user.getUsername(), user.getPassword(), user.getFirstname(), user.getLastname());
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public void addListUser(List<User> users) {
    		try {
    			for(User user: users) {
        			addUser(user);
        		}
		} catch (Exception e) {
			e.getStackTrace();
		}	
    }
    
    public void deleteAll() {
    		//delete all records and reset id to 1 - n
    		try {
    			jdbcTemplate.execute("DELETE FROM Users; ALTER SEQUENCE users_id_seq  RESTART WITH 1;"); 
		} catch (Exception e) {
			e.getStackTrace();
		}	
    	}
    
    public void resetAllPassword(List<String> passwords) {
    		try {
    			int id = 1; 
    			for(String password: passwords) {
    				jdbcTemplate.update("UPDATE Users SET password = ? WHERE id = ?",
    						password, id++);
    			}
		} catch (Exception e) {
			e.getStackTrace();
		}
    }
    
    public void updateAllSaltColumn(List<String> salts) {
    		try {
    			int id = 1; 
    			for(String salt: salts) {
    				jdbcTemplate.update("UPDATE Users SET salt = ? WHERE id = ?",
    						salt, id++);
    			}
		} catch (Exception e) {
			e.getStackTrace();
		}
    }
    
    public void updateSaltColumn(String username, String salt) {
		try {
			jdbcTemplate.update("UPDATE Users SET salt = ? WHERE username = ?", salt, username);
		} catch (Exception e) {
			e.getStackTrace();
		}
	}

	public void resetAllSalt() {
		try {
			for(int id=1; id< findAll().size() + 1; id++) {
				jdbcTemplate.update("UPDATE Users SET salt = ? WHERE id = ?",
							"", id);
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
	}

}