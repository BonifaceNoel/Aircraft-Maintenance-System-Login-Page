package com.ibsplc.amtsloginpage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ibsplc.amtsloginpage.security.AuthenticationRequest;
import com.ibsplc.amtsloginpage.security.AuthenticationResponse;
import com.ibsplc.amtsloginpage.service.LoginService;
import com.ibsplc.amtsloginpage.util.JwtUtil;

@RestController
public class LoginController {

	@Autowired
	private AuthenticationManager authManager;

	@Autowired
	private LoginService loginService;

	@Autowired
	private JwtUtil jwtUtil;

	@PostMapping("/login")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authRequest) throws Exception{
		try {
			authManager.authenticate(
					new UsernamePasswordAuthenticationToken(
							authRequest.getUsername(),
							authRequest.getUsername()
							)
					);
		}

		catch(BadCredentialsException ec) {
			throw new Exception("Incorrect Username or Password", ec);
		}

		final UserDetails userDetails = loginService.loadUserByLoginName(authRequest.getUsername());

		final String token = jwtUtil.createToken(userDetails);

		return ResponseEntity.ok(new AuthenticationResponse(token));
	}
}
