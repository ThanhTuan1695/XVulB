package com.mgmtp.blog.model;

import java.io.Serializable;

public class PostDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
    private Long id;
    private String title;
    private String createdDay;
    private String content;
    private String createdBy;

    public PostDTO() {
    }

    public PostDTO(Long id, String title, String createdDay, String content, String createdBy) {
		super();
		this.id = id;
		this.title = title;
		this.createdDay = createdDay;
		this.content = content;
		this.createdBy = createdBy;
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

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	public static PostDTO fromPostModel(Post input) {
		return new PostDTO(input.getId(), input.getTitle(), input.getCreatedDay(), input.getContent(), input.getUser().getUsername());
	}
    
}
