package com.example.demo.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.serviceImpl.UserServiceImpl;

@RestController
@RequestMapping("user")
public class UserController {

	
	@Autowired
	UserServiceImpl userServiceImpl;
	
	
	@GetMapping("getAll")
	public List<User> getAllUsers(){
		return  userServiceImpl.getAllUser();
	}
	
	
	@GetMapping("get/{id}")
	public User getUserById(@PathParam(value = "id") int id){
		return  userServiceImpl.findById(id);
	}
	
	
	@DeleteMapping("delete/{id}")
	public void deleteUserById(@PathParam(value = "id") int id){
		userServiceImpl.deleteUser(id);
	}
	
	
	@PostMapping("save")
	public User addUser(@RequestBody User user){
		return userServiceImpl.addUser(user);
	}
	
	
}
