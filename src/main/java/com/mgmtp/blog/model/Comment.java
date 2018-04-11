package com.mgmtp.blog.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "comments")
public class Comment {
	
	@Id
    private Long id;
    
    @Column(name="created_at")
    private String createdAt;
    
    @Column(name="created_by")
    private String createdBy;
    
    private String comment;
    
    @ManyToOne()
    @JoinColumn(name="post_id", nullable= false)
    private Post post;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public Comment(Long id, String createdAt, String createdBy, String comment, Post post) {
		super();
		this.id = id;
		this.createdAt = createdAt;
		this.createdBy = createdBy;
		this.comment = comment;
		this.post = post;
	}
	
	public Comment(String createdBy, String comment, Post post) {
		super();
		this.createdBy = createdBy;
		this.comment = comment;
		this.post = post;
	}
    
}
