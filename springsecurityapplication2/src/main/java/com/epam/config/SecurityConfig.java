package com.epam.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.resource.web.BearerTokenAuthenticationEntryPoint;
import org.springframework.security.oauth2.server.resource.web.access.BearerTokenAccessDeniedHandler;
import org.springframework.security.web.SecurityFilterChain;

import com.epam.service.JpaUserDetailsService;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {
	private final RsaKeyProperties jwtConfigProperties;
	public static final String[] PUBLIC_PATHS = {
			"/v3/api-docs.yaml",
			"/v3/api-docs/**",
			"/swagger-ui/**",
			"/swagger-ui.html"
			};
	
	JpaUserDetailsService jpaUserDetailService ;
	public SecurityConfig(JpaUserDetailsService jpaUserDetailService, RsaKeyProperties jwtConfigProperties) {
		this.jpaUserDetailService = jpaUserDetailService;
		this.jwtConfigProperties = jwtConfigProperties;
	}
	
	
	@Bean
	@Order(1)
	public SecurityFilterChain basicSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {
		 httpSecurity
				.securityMatcher("/token")
				.authorizeHttpRequests(auth->
				auth.requestMatchers(PUBLIC_PATHS).permitAll()
				.anyRequest().authenticated()
				)
				.csrf().disable()
			.httpBasic(Customizer.withDefaults());
		return httpSecurity.build();
	}
	
	 @Bean
	    JwtDecoder jwtDecoder() {
	        return NimbusJwtDecoder.withPublicKey(jwtConfigProperties.publicKey()).build();
	    }

	    @Bean
	    JwtEncoder jwtEncoder() {
	        JWK jwk = new RSAKey.Builder(jwtConfigProperties.publicKey()).privateKey(jwtConfigProperties.privateKey()).build();
	        JWKSource<SecurityContext> jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
	        return new NimbusJwtEncoder(jwks);
	    }
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		 httpSecurity
				.authorizeHttpRequests()
				.requestMatchers(PUBLIC_PATHS).permitAll()
				.anyRequest().authenticated().and()
				.httpBasic().disable()
				.oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.exceptionHandling(exceptions -> exceptions
				.authenticationEntryPoint(new BearerTokenAuthenticationEntryPoint())
				.accessDeniedHandler(new BearerTokenAccessDeniedHandler())
				)
				// XSS protection
				.headers().xssProtection().and()
				.contentSecurityPolicy("script-src 'self'");
		 return httpSecurity.build();
	}
}
