package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.User;

public interface UserService {

	public User addUser(User user);

	public User findById(int id);

	public List<User> getAllUser();

	public void deleteUser(int id);

}
