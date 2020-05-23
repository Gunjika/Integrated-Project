package com.capgemini.hms.service;

import java.util.List;

import com.capgemini.hms.entity.User;

public interface UserServiceInterface {
	boolean addUser(User user);
    List<User> showAllUsers();
	void deleteuser(long userId);
	java.util.Optional<User> findUserById(Long userId);

}
