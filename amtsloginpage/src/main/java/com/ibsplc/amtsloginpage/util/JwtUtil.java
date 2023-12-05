package com.ibsplc.amtsloginpage.util;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

	@Value("${jwt.secret}")
	private String secret;

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

	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}

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

	public boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

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
