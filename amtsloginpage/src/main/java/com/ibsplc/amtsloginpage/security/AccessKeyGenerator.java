package com.ibsplc.amtsloginpage.security;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.util.Base64;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.security.Keys;

@Component
public class AccessKeyGenerator {

	String secret = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9";

	public String generateRandomAccessKey() {
		int keyLength = 32;

		byte[] randomBytes = new byte[keyLength];
		SecureRandom secure = new SecureRandom();

		secure.nextBytes(randomBytes);

		String generatedKey = Base64.getEncoder().encodeToString(randomBytes);

		generatedKey = generatedKey.replaceAll("[^a-zA-Z0-9]", "");

		return generatedKey;
	}

    KeyPair generatePair(String username, String password) throws Exception{
    	String seedConcat = username.concat(password);
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
		SecureRandom secure = new SecureRandom(seedConcat.getBytes());

		keyPairGenerator.initialize(2048, secure);

		return keyPairGenerator.generateKeyPair();
	}

    public Key getSigningKey() {
        byte[] keyBytes = this.secret.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
