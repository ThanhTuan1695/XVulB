package com.mgmtp.blog.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mgmtp.blog.model.Comment;

@Repository
public class CommentRepository {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private PostRepository postRepository;

	public List<Comment> findAllByPostId(String id) {
		int param;
		try {
			param = Integer.valueOf(id);
		} catch (Exception ignore) {
			param = 0;
			
		}
		List<Comment> result;
		try {
			result = jdbcTemplate.query("SELECT * FROM Comments WHERE post_id = ?",
					(rs, rowNum) -> new Comment(rs.getLong("id"), rs.getString("created_at"),
							rs.getString("created_by"), rs.getString("comment"),
							postRepository.findById(Long.toString(rs.getLong("post_id")), false).get(0)), param);

		} catch (Exception e) {
			return null;
		}
		return result;

	}

	public boolean addComment(Comment comment) {
		try {
			jdbcTemplate.update("INSERT INTO Comments(created_at, created_by, comment, post_id) VALUES (to_char(NOW(), 'DD Mon YYYY HH24:MI:SS'),?,?,?)",
					comment.getCreatedBy(), comment.getComment(), comment.getPost().getId());
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
