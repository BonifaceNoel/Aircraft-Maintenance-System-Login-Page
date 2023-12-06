package com.ibsplc.amtsloginpage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ibsplc.amtsloginpage.bo.LoginUser;
import com.ibsplc.amtsloginpage.exceptions.NoLoginNameException;
import com.ibsplc.amtsloginpage.service.LoginService;

@RestController
@CrossOrigin(origins = "https://localhost:5173")
public class LoginController {

	@Autowired
	LoginService logService;

	@GetMapping(value="/login/{userName}", produces=MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<String> getLoginInfo(@PathVariable("userName") String userName) throws NoLoginNameException{
		ResponseEntity<String> loginInfo = null;
		LoginUser user = logService.loadUserByLoginName(userName);

		String responseMessage = null;

		if(user != null) {
			responseMessage = "{\"Login User found\" : \"Logged in\"}";
			loginInfo = new ResponseEntity<>(responseMessage, HttpStatus.ACCEPTED);
		}
		else {
			responseMessage = "{\"No User found\" : \"No Access\"}";
			loginInfo = new ResponseEntity<>(responseMessage, HttpStatus.NOT_ACCEPTABLE);
		}
		return loginInfo;
	}
}
