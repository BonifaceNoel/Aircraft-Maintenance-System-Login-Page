package com.ibsplc.amtsloginpage.util;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Configuration
public class JwtUtil {

	@Value("${jwt.secret}")
	private String secret;

	@Bean
	public String createToken(UserDetails userDetails) {
		Date currentDate = new Date();
		Date expiryDate = new Date(currentDate.getTime() + 86400000);

		return Jwts.builder().subject(userDetails.getUsername()).issuedAt(currentDate).expiration(expiryDate)
				.signWith(getSignKey()).compact();
	}

	public Key getSignKey() {
		byte[] keyBytes = this.secret.getBytes(StandardCharsets.UTF_8);
		return Keys.hmacShaKeyFor(keyBytes);
	}

	@Bean
	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	@Bean
	public Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

	private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	private Claims extractAllClaims(String token) {
		return (Claims) Jwts.parser().build().parse(token);
	}

	@Bean
	public boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	@Bean
	public boolean validateToken(String token) {
		try {
			Jwts.parser().build().parse(token);
			return true;
		}
		catch(JwtException | IllegalArgumentException e) {
			return false;
		}
	}
}
