package com.ibsplc.amtsloginpage.security;

import java.security.SecureRandom;
import java.util.Base64;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class AccessKeyGenerator {

	@Bean
	public String generateRandomAccessKey() {
		int keyLength = 32;

		byte[] randomBytes = new byte[keyLength];
		SecureRandom secure = new SecureRandom();

		secure.nextBytes(randomBytes);

		String generatedKey = Base64.getEncoder().encodeToString(randomBytes);

		generatedKey = generatedKey.replaceAll("[^a-zA-Z0-9]", "");

		return generatedKey;
	}
}
