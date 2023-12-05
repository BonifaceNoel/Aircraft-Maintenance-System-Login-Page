package com.ibsplc.amtsloginpage.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ibsplc.amtsloginpage.exceptions.NoLoginNameException;
import com.ibsplc.amtsloginpage.service.LoginService;
import com.ibsplc.amtsloginpage.util.JwtUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtRequestFilter extends OncePerRequestFilter{

	@Autowired
	private LoginService loginService;

	@Autowired
	private JwtUtil jwtUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		final String authorizationHeader = request.getHeader("Authorization");

		String username = null;
		String jwt = null;

		if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			jwt = authorizationHeader.substring(7);
			username = jwtUtil.extractUsername(jwt);
		}

		if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
			UserDetails userDetails = null;
			try {
				userDetails = this.loginService.loadUserByLoginName(username);
			} catch (NoLoginNameException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if(jwtUtil.validateToken(jwt)) {
				UsernamePasswordAuthenticationToken usernamePassAuth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

				usernamePassAuth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

				SecurityContextHolder.getContext().setAuthentication(usernamePassAuth);
			}
		}
		filterChain.doFilter(request,response);
	}
}
