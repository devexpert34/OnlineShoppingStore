package com.example.demo.controller;

import java.util.List;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;
import com.example.demo.requesBodies.AddUserRequest;
import com.example.demo.requesBodies.GetUser;
import com.example.demo.requesBodies.UserLoginRequest;
import com.example.demo.service.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
	
	@Autowired
	UserService service;
	
	
	@RequestMapping(path = "/useradd",method = RequestMethod.POST)
	public boolean AddUser(@RequestBody AddUserRequest request) {
		
		
		
		return service.AddUser(request.username, request.fname, request.lname, request.address, request.cnumber, request.usertype, request.bday, hashPassword(request.password),request.email);
		
	}
	
	@RequestMapping(path = "/userlogin", method = RequestMethod.POST)
	public boolean Login(@RequestBody UserLoginRequest request) {
		
		System.out.println(hashPassword(request.password));
		
		return service.Login(request.username,request.password);
	}
	
	@RequestMapping(path = "/usergetall", method = RequestMethod.GET)
	public List<User> getAllUsers() {
		
		return service.getAllUsers();
	}
	
	
	@RequestMapping(path = "/userget", method = RequestMethod.POST)
	public User getUser(@RequestBody GetUser user) {
		
		return service.findUserByUsername(user.username);
	}
	
	
	private String hashPassword(String plainTextPassword){
		return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
	}
	

}
