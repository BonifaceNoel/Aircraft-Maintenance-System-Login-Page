package com.ibsplc.amtsloginpage.security;

import java.security.KeyPair;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.ibsplc.amtsloginpage.exceptions.JWTKeyGenerationException;

import io.jsonwebtoken.Jwts;

public class JWTKeyGenerator {

	@Autowired
	AccessKeyGenerator accKeyGen;

	public String generateRS256Token(String username, String password, String role) throws JWTKeyGenerationException{
		try {
			//Creating RSA Key Pair
			KeyPair keyPair = accKeyGen.generatePair();

			//Building JWT Access Token
			return Jwts.builder()
					.claim("LoginName", username)
					.claim("LoginPassword", password)
					.claim("LoginRole", role)
					.issuedAt(new Date())
					.signWith(keyPair.getPrivate(), Jwts.SIG.RS256)
					.compact();
		}
		catch(Exception e) {
			throw new JWTKeyGenerationException("Error in creating Access Token", e.getCause());
		}
	}

}
