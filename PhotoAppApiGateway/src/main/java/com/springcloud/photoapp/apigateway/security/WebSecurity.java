package com.springcloud.photoapp.apigateway.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

	@Autowired
	private Environment env;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.headers().frameOptions().disable();// For h2-console

		http.authorizeRequests().antMatchers(env.getProperty("user.ws.login.uri")).permitAll()
				.antMatchers(env.getProperty("user.ws.registration.uri")).permitAll()
				.antMatchers(env.getProperty("zuul.actuator.url.path")).permitAll()
				.antMatchers(env.getProperty("user.ws.actuator.uri")).permitAll()				
				.antMatchers(env.getProperty("user.ws.h2console.uri")).permitAll().anyRequest().authenticated()
				.and().addFilter(new AuthorizationFilter(authenticationManager(), env));

		// To Prevent http Autherization headers to be cached we make session
		// STATLESS-->Spring security will never create a http session and obtain
		// authentication object from SecurityContext...
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

	}

}
