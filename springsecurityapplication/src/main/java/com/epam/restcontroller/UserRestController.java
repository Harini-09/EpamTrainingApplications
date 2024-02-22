package com.epam.restcontroller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epam.dtos.UserDto;
import com.epam.entities.User;
import com.epam.service.UserAuthentication;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserRestController {

	@Autowired
	UserAuthentication service;
	
	private final Logger logger = LogManager.getLogger(UserRestController.class);
	
	@PostMapping(value="/save", produces= {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<UserDto> saveUser(@RequestBody @Valid UserDto userDto) {
		logger.info("Received the POST request to save a user");
		return new ResponseEntity<>(service.signUp(userDto),HttpStatus.CREATED);
	}
	
	@PostMapping(value="/check", produces= {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE })
	public String checkUser(@RequestBody UserDto userDto) {
		logger.info("Received the POST request to check if a user is an authorized user");
		if(service.logIn(userDto)) {
			return "User successfully logged in !!!";
		}
		else {
			return "Invalid Credentials!!";
		}
	}
	
	@GetMapping(value="/view", produces= {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<List<User>> getUsers() {
		logger.info("Received the GET request to retrieve all the users");
		return new ResponseEntity<>(service.view(),HttpStatus.OK);
	}
}
