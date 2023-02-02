package com.example.demo.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserDao userDao;

	@Override
	public User addUser(User user) {
		// TODO Auto-generated method stub
		return userDao.save(user);
	}

	@Override
	public User findById(int id) {
		// TODO Auto-generated method stub
		return userDao.findById(id).get();
	}

	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		return userDao.findAll();
	}

	@Override
	public void deleteUser(int id) {
		// TODO Auto-generated method stub
		userDao.deleteById(id);
	}

}
