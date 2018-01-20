package com.polibuda.pbl.endpoint;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.polibuda.pbl.exception.NotFoundUserException;
import com.polibuda.pbl.exception.RegisterExternalServiceUserException;
import com.polibuda.pbl.exception.RegisterUserException;
import com.polibuda.pbl.model.User;
import com.polibuda.pbl.service.UserService;
import com.polibuda.pbl.validator.ExternalUserValidator;
import com.polibuda.pbl.validator.RegisterUserValidator;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/user")
public class UsersRestEndpoint {

	private final UserService userService;
	private final RegisterUserValidator registerUserValidator;
	private final ExternalUserValidator externalUserValidator;
	
	@Autowired
	public UsersRestEndpoint(@NonNull UserService userService, @NonNull RegisterUserValidator registerUserValidator,
			@NonNull ExternalUserValidator externalUserValidator){
		this.userService = userService;
		this.registerUserValidator = registerUserValidator;
		this.externalUserValidator = externalUserValidator;
	}
	
	@GetMapping
	public String testMapping(){
		return "Ok";
	}
	
	@GetMapping("/login")
	public ResponseEntity<User> loginAppUser(@RequestParam String email, @RequestParam String password) throws NotFoundUserException {
		
		log.info("User with email: {} is trying to log in.", email);
		
		User user = userService.findUserByEmailAndPassword(email, password);
		user.setPassword("You ain't gona get it ;)");

		log.info("User with email: {} logged in succesfully.", email);
		
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@PostMapping("/login/external")
	public ResponseEntity<User> loginExternalUser(@RequestBody User externalUser, @RequestParam String externalAccessToken) throws RegisterExternalServiceUserException {
		log.info("User with fbId: {}, googleId: {} is trying to log in.", externalUser.getFacebookId(), externalUser.getGoogleId());
		
		externalUserValidator.validate(externalUser);
		
		User user;
		try {
			user = userService.findExternalServiceUser(externalUser);
			user = userService.updateUserInfo(user, externalUser);
			
			log.info("User with fbId: {}, googleId: {} successfully logged in.", user.getFacebookId(), user.getGoogleId());
			
			return new ResponseEntity<User>(user, HttpStatus.OK);
		} catch (NotFoundUserException e) {
			user = userService.registerExternalUser(externalUser, externalAccessToken);
		}
		
		log.info("User with id: {}{} created and logged in.", user.getFacebookId(), user.getGoogleId());
		
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@PostMapping("/register")
	public ResponseEntity<User> registerUser(@RequestHeader(value="Accept-Language") String acceptLanguage, @RequestBody User user, 
			@RequestParam String psw) throws RegisterUserException {
		
		log.info("Started registering user with email: {}.", user.getEmail());
		
		user.setPassword(psw);
		registerUserValidator.validate(user);		
		user = userService.registerUser(user, Locale.forLanguageTag(acceptLanguage));
		
		log.info("Registering user with email: {} has been completed succesfully.", user.getEmail());
		
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@GetMapping("/recover")
	public ResponseEntity<?> recoverAccount(@RequestParam String emailAddress) throws NotFoundUserException {
		
		boolean result = userService.recoverAccount(emailAddress);
		
		if(result)
			return new ResponseEntity<String>("OK", HttpStatus.OK);
		else 
			return new ResponseEntity<String>("BAD", HttpStatus.NOT_FOUND);
	}
}
