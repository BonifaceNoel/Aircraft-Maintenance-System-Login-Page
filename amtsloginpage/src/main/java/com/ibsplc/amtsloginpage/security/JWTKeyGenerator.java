package com.ibsplc.amtsloginpage.security;

import java.security.KeyPair;
import java.security.PublicKey;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;

@Component
public class JWTKeyGenerator {

	@Autowired
	AccessKeyGenerator keyGen;

	private static final Logger logger = LoggerFactory.getLogger(JWTKeyGenerator.class);

	public String generateRS256Token(String username, String password, String role) throws Exception {

			//Creating RSA Private Public Key pair
			KeyPair keySet = keyGen.generatePair(username, password);

			Instant now = Instant.now();

			//Building JWT Access Token
			return Jwts.builder()
					.claim("loginName", username)
					.claim("loginPassword", password)
					.claim("loginrole", role)
					.issuedAt(Date.from(now))
					.signWith(keySet.getPrivate(), Jwts.SIG.RS256)
					.expiration(Date.from(now.plus(1440, ChronoUnit.MINUTES)))
					.compact();
	}

	public Map<String, Object> decodeRS256Token(String accessToken) {

		Base64.Decoder decoder = Base64.getUrlDecoder();

		String[] tokenParts = accessToken.split("\\.");

		String header = new String(decoder.decode(tokenParts[0]));
		String payload = new String(decoder.decode(tokenParts[1]));

		Map<String, Object> mapPayload = jsonToMap(header, payload);

		//mapPayload.forEach((key, value) -> System.out.println(key + " : " + value));
		return mapPayload;
	}

	public Map<String, Object> jsonToMap(String header, String payload) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			Map<String, Object> map1 = objectMapper.readValue(header, Map.class);
			Map<String, Object> map2 = objectMapper.readValue(payload, Map.class);

			map1.putAll(map2);
			return map1;
		}
		catch(Exception ex) {
			logger.error("Error in creating Map for payload: " + "" + ex.getCause());
			return null;
		}
	}
}
