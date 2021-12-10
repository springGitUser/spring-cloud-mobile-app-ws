package com.springcloud.photoapp.userservice.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springcloud.photoapp.userservice.dto.LoginDTO;
import com.springcloud.photoapp.userservice.dto.UserRequestDTO;
import com.springcloud.photoapp.userservice.service.UserService;

import io.jsonwebtoken.Jwts;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private Environment env;
	private UserService userService;

	public AuthenticationFilter(Environment env2, UserService userService2,
			AuthenticationManager authenticationManager) {
		this.env = env2;
		this.userService = userService2;
		super.setAuthenticationManager(authenticationManager);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

		try {
			LoginDTO creds = new ObjectMapper().readValue(request.getInputStream(), LoginDTO.class);

			return getAuthenticationManager().authenticate(
					new UsernamePasswordAuthenticationToken(creds.getEmail(), creds.getPassword(), new ArrayList<>()));

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {

		String userName = ((User) authResult.getPrincipal()).getUsername();
		UserRequestDTO userReqDTO = userService.getUserDetailsByUsername(userName);
		System.out.println("Token Expirty Time User MicroService==>" + env.getProperty("token.expiry.time"));
		String token = Jwts.builder().setSubject(userReqDTO.getUserId())
				.setExpiration(
						new Date(System.currentTimeMillis() + Long.parseLong(env.getProperty("token.expiry.time"))))
				.signWith(io.jsonwebtoken.SignatureAlgorithm.HS512, env.getProperty("token.secret")).compact();

		response.addHeader("token", token);
		response.addHeader("userId", userReqDTO.getUserId());
	}
}
