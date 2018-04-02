package com.mgmtp.blog.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Posts")
public class Post {
	
	@Id
    private Long id;
    private String title;
    
    @Column(name="created-day")
    private String createdDay;
    private String content;
    
    @ManyToOne()
    @JoinColumn(name="user_id", nullable= false)
    private User user;

    public Post() {
    }

    public Post(Long id, String title, String createdDay, String content, User user) {
		super();
		this.id = id;
		this.title = title;
		this.createdDay = createdDay;
		this.content = content;
		this.user = user;
	}
    
    public Post(String title, String content, User user) {
		super();
		this.title = title;
		this.content = content;
		this.user = user;
	}
    
	public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCreatedDay() {
		return createdDay;
	}

	public void setCreatedDay(String createdDay) {
		this.createdDay = createdDay;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
    
}
