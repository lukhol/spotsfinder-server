package com.polibuda.pbl.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.polibuda.pbl.exception.NotFoundUserException;
import com.polibuda.pbl.model.User;
import com.polibuda.pbl.service.UserService;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/user")
public class UsersRestEndpoint {

	private final UserService userService;
	
	@Autowired
	public UsersRestEndpoint(@NonNull UserService userService){
		this.userService = userService;
	}
	
	@GetMapping
	public String test(){
		return "test";
	}
	
	@GetMapping
	@RequestMapping("/login")
	public ResponseEntity<User> logIn(@RequestParam String email, @RequestParam String password) throws NotFoundUserException {
		log.info("User with email: {} is trying to log in.", email);
		
		User user = userService.findUserByEmailAndPassword(email, password);
		
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
}
