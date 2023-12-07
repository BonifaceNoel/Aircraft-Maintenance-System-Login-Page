package com.ibsplc.amtsloginpage.security;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.util.Base64;

import org.springframework.stereotype.Component;

@Component
public class AccessKeyGenerator {

	public String generateRandomAccessKey() {
		int keyLength = 32;

		byte[] randomBytes = new byte[keyLength];
		SecureRandom secure = new SecureRandom();

		secure.nextBytes(randomBytes);

		String generatedKey = Base64.getEncoder().encodeToString(randomBytes);

		generatedKey = generatedKey.replaceAll("[^a-zA-Z0-9]", "");

		return generatedKey;
	}

    KeyPair generatePair() throws Exception{
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
		SecureRandom secure = new SecureRandom();

		keyPairGenerator.initialize(2048, secure);

		return keyPairGenerator.generateKeyPair();
	}
}
