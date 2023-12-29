package com.ibsplc.amtsloginpage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ibsplc.amtsloginpage.bo.LoginUser;
import com.ibsplc.amtsloginpage.exceptions.NewUserInvalidException;
import com.ibsplc.amtsloginpage.exceptions.NoLoginNameException;
import com.ibsplc.amtsloginpage.service.LoginService;

@RestController
@CrossOrigin(origins = "https://localhost:5173")
public class LoginController {

	@Autowired
	LoginService logService;

	@GetMapping(value="/login/{userName}/{password}", produces=MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<String> getLoginInfo(@PathVariable("userName") String userName, @PathVariable("password") String password) throws NoLoginNameException{
		ResponseEntity<String> loginInfo = null;
		boolean userStatus = logService.loadUserByLoginName(userName, password);

		String responseMessage = null;

		if(userStatus) {
			responseMessage = "{\"Login User found\" : \"Logged in\"}";
			loginInfo = new ResponseEntity<>(responseMessage, HttpStatus.ACCEPTED);
		}
		else {
			responseMessage = "{\"No User found\" : \"No Access\"}";
			loginInfo = new ResponseEntity<>(responseMessage, HttpStatus.NOT_ACCEPTABLE);
		}
		return loginInfo;
	}

	@PostMapping(value="/newregist", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<String> newUserInfo(@RequestBody LoginUser newUser) throws Exception {
		ResponseEntity<String> newLogin = null;
		String newUserResponse = null;

		boolean result = logService.loadNewUser(newUser);

		if(result) {
			newUserResponse = "{\"New User found\" : \"Access Request Sent\"}";
			newLogin = new ResponseEntity<>(newUserResponse, HttpStatus.CREATED);
		}
		else {
			newUserResponse = "{\"New User error\" : \"Access Request Failed\"}";
			newLogin = new ResponseEntity<>(newUserResponse, HttpStatus.BAD_REQUEST);
		}

		return newLogin;
	}
}
