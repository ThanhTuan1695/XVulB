package com.mgmtp.blog.dao;

import com.mgmtp.blog.model.Post;
import com.mgmtp.blog.model.PostDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PostRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Autowired
    private UserRepository userRepository;

    public List<Post> findAll() {
    		List<Post> result;
    		try {
    			result = jdbcTemplate.query( "SELECT * FROM Posts ORDER BY id DESC;",
						(rs, rowNum) -> new Post(rs.getLong("id"), 
												rs.getString("title"), 
												rs.getString("created_day"), 
												rs.getString("content"), 
												userRepository.findById(rs.getLong("user_id")).get(0)
										)
					 );
    		} catch (Exception e) {
    			return null;
    		}
        return result;

    }
    
    public List<Post> findById(String id, boolean safe) {
    		List<Post> result;
    		try {

				if(safe) {
					int param;
					try {
						param = Integer.valueOf(id);
					} catch (Exception ignore) {
						param = 0;		
					}		
					//safe query
					result = jdbcTemplate.query( "SELECT * FROM Posts WHERE id = ?", 
													(rs, rowNum) -> new Post(rs.getLong("id"), 
																			rs.getString("title"), 
																			rs.getString("created_day"), 
																			rs.getString("content"), 
																			userRepository.findById(rs.getLong("user_id")).get(0)), param
							
													);
				}
				
				else {
					//unsafe query
					
						result = jdbcTemplate.query( "SELECT * FROM Posts WHERE id = " + id, 
							(rs, rowNum) -> new Post(rs.getLong("id"), 
													rs.getString("title"), 
													rs.getString("created_day"), 
													rs.getString("content"), 
													userRepository.findById(rs.getLong("user_id")).get(0))

						);
					
					
						
				}
			} catch (Exception ignore) {
					return new ArrayList<Post>();
				}
	    return result;
	
	}
    
    public List<Post> findByTitle(String query, boolean safe) {
    		String param = "%"+ query +"%";
    		List<Post> result;
    		if(safe) {
    			//safe query
    			result = jdbcTemplate.query( "SELECT * FROM Posts WHERE title LIKE ?", 
						   (rs, rowNum) -> new Post(rs.getLong("id"), 
												rs.getString("title"), 
												rs.getString("created_day"), 
												rs.getString("content"), 
												userRepository.findById(rs.getLong("user_id")).get(0)), param

						 );
    			
    		}
    		else {
    			try {
	    			//unsafe query
	    			result = jdbcTemplate.query( "SELECT * FROM Posts WHERE title LIKE '" + param + "'", 
	        										   (rs, rowNum) -> new Post(rs.getLong("id"), 
																			rs.getString("title"), 
																			rs.getString("created_day"), 
																			rs.getString("content"), 
																			userRepository.findById(rs.getLong("user_id")).get(0))
	                        
	        										 );
    			} catch (BadSqlGrammarException e) {
    				return new ArrayList<Post>();
    			}
    			
    		}
    		return result;
        

    }

    public boolean addPost(Post post) {
    		try {
    			jdbcTemplate.update("INSERT INTO Posts(title, created_day, content, user_id) VALUES (?,NOW(),?,?)",
    	                 post.getTitle(), post.getContent(), post.getUser().getId());
    		} catch (Exception e) {
    			e.printStackTrace();
    			return false;
    		}
    		return true;
    }


}