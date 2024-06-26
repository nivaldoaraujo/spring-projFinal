package com.nivaldo.projetoapi.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
		.csrf(csrf -> csrf.disable())
		.authorizeHttpRequests((auth) -> auth
				.requestMatchers(HttpMethod.GET, "/aluno").permitAll()
				.requestMatchers(HttpMethod.GET, "/professor").permitAll()
				.requestMatchers(HttpMethod.POST).hasAuthority("ADMIN")
				.requestMatchers(HttpMethod.PUT).hasAuthority("ADMIN")
				.requestMatchers(HttpMethod.DELETE).hasAuthority("ADMIN")
				).httpBasic(Customizer.withDefaults());
		return http.build();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
	
	@Bean
	public PasswordEncoder passowordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
