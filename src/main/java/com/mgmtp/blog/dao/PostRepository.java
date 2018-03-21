package com.mgmtp.blog.dao;

import com.mgmtp.blog.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class PostRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Post> findAll() {

        List<Post> result = jdbcTemplate.query( "SELECT * FROM Posts",
                									(rs, rowNum) -> new Post(rs.getLong("id"), 
                															rs.getString("title"), 
                															rs.getString("created_day"), 
                															rs.getString("content"), 
                															rs.getLong("user_id"))
        										 );

        return result;

    }
    
    public List<Post> findByTitle(String query) {

    		String param = "'%"+ query +"%'";

        List<Post> result = jdbcTemplate.query( "SELECT * FROM Posts WHERE title LIKE " + param, 
        										   (rs, rowNum) -> new Post(rs.getLong("id"), 
																		rs.getString("title"), 
																		rs.getString("created_day"), 
																		rs.getString("content"), 
																		rs.getLong("user_id"))
                        
        										 );

        return result;

    }

    public void addPost(Post post) {

        jdbcTemplate.update("INSERT INTO Posts(id, title, created_day, content, user_id) VALUES (?,?,?,?,?)",
                post.getId(), post.getTitle(), post.getCreatedDay(), post.getContent(), post.getUserId());

    }


}