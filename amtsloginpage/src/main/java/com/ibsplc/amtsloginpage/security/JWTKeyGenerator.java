package com.ibsplc.amtsloginpage.security;

import java.security.KeyPair;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
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

			//Creating RSA Private Public Key pair
			KeyPair keySet = keyGen.generatePair();

			Instant now = Instant.now();

			//Building JWT Access Token
			return Jwts.builder()
					.claim("loginName", username)
					.claim("loginPassword", password)
					.claim("loginrole", role)
					.issuedAt(Date.from(now))
					.signWith(keySet.getPrivate(), Jwts.SIG.RS256)
					.expiration(Date.from(now.plus(60, ChronoUnit.MINUTES)))
					.compact();
	}
}
