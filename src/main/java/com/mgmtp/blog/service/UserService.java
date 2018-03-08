package com.mgmtp.blog.service;

import com.mgmtp.blog.model.User;
import java.util.List;

public interface UserService {
	List<User> findAll();
	List<User> findByUsername(String username);
}