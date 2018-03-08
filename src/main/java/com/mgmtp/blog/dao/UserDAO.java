package com.mgmtp.blog.dao;

import com.mgmtp.blog.model.User;
public interface UserDAO
{
	public void insert(User user);
	public User findByUsername(String username);
}
