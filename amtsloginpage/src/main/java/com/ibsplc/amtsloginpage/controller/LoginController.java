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

}
