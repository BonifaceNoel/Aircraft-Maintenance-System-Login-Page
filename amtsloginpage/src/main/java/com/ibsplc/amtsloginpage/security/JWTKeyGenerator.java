package com.ibsplc.amtsloginpage.security;

import java.security.KeyPair;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;

@Component
public class JWTKeyGenerator {

	@Autowired
	AccessKeyGenerator keyGen;

	private static final Logger logger = LoggerFactory.getLogger(JWTKeyGenerator.class);

	public String generateRS256Token(String username, String password, String role) throws Exception {

		String jwtKey = null;
		try {
			//Creating RSA Key Pair
			KeyPair keyPair = keyGen.generatePair();
			System.out.println(keyPair.getPublic().toString());
			System.out.println(keyPair.getPrivate().toString());

			//Building JWT Access Token
			jwtKey =  Jwts.builder()
					.claim("LoginName", username)
					.claim("LoginPassword", password)
					.claim("LoginRole", role)
					.issuedAt(new Date())
//					.signWith(keyPair.getPrivate(), Jwts.SIG.RS256)
					.compact();
		}
		catch(Exception e) {
			logger.error("Error in creating Access Tokens: " + "" + e.getCause());
		}

		return jwtKey;
	}
}
