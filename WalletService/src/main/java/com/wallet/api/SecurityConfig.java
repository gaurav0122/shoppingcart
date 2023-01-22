package com.wallet.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.wallet.api.jwtfilter.JwtFilter;
import com.wallet.api.security.service.MyUserDetails;

@Configuration
public class SecurityConfig {

	@Autowired
	private MyUserDetails myUserDetails;
	
	@Autowired
	private JwtFilter jwtRequestFilter;
	
	@Bean
	public AuthenticationManager authenticationManager(
						AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}
	
	@Bean
	public AuthenticationProvider getAuthProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(myUserDetails);
		authenticationProvider.setPasswordEncoder(getPassEncoder());
		
		return authenticationProvider;
		
	}
	
	@Bean
	public PasswordEncoder getPassEncoder() {
		PasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authenticationProvider(getAuthProvider());
		
		http.csrf().disable().authorizeRequests()
				.antMatchers(HttpMethod.GET, "/api/wallets").authenticated()
				.antMatchers(HttpMethod.POST,"/api/wallet/*").authenticated()
				.antMatchers(HttpMethod.GET,"/api/wallets/*").authenticated()
				.antMatchers(HttpMethod.GET,"/api/wallet/statements").authenticated()
				.antMatchers(HttpMethod.DELETE,"/api/wallet/*").authenticated()
				.antMatchers(HttpMethod.POST,"/api/wallet/*/*").authenticated()
				.antMatchers(HttpMethod.GET, "/api/wallet/statements/*").authenticated()
				.antMatchers(HttpMethod.GET, "/api/wallets/byuser/*").authenticated()
				.antMatchers(HttpMethod.POST,"/api/wallet/pay/*/*/*").authenticated()
				.antMatchers(HttpMethod.POST,"/api/wallet/pay/*/*").authenticated()
				.anyRequest().permitAll()
				.and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		http.addFilterBefore(jwtRequestFilter,UsernamePasswordAuthenticationFilter.class);
				
		return http.build();
		
	}
	
}
