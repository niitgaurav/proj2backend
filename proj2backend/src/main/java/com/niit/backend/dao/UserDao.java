package com.niit.backend.dao;

import java.util.List;

import com.niit.backend.model.User;
public interface UserDao {
	User authenticate(User user);
	User updateUser(int userid, User user);
	void deleteUser(int id);
	User registerUser(User user);
	User getUserById(int userid);
	List<User> getAllUsers();
}
