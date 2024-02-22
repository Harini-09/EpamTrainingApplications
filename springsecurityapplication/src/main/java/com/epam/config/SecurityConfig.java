package com.epam.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	public static final String[] PUBLIC_PATHS = {
			"/v3/api-docs.yaml",
			"/v3/api-docs/**",
			"/swagger-ui/**",
			"/swagger-ui.html"
			};
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity.authorizeHttpRequests(auth->
				auth.requestMatchers(PUBLIC_PATHS).permitAll()
				.anyRequest().authenticated()
				
				)
			.httpBasic(Customizer.withDefaults()).build();
	}
}
