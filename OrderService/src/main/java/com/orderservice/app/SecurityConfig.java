package com.orderservice.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.orderservice.app.jwtfilter.JwtFilter;
import com.orderservice.app.security.service.MyUserDetails;



@Configuration
public class SecurityConfig {

	@Autowired
	private MyUserDetails myUserDetails;
	
	@Autowired
	private JwtFilter jwtRequestFilter;
	
	@Bean
	protected AuthenticationManager authenticationManager(
	        AuthenticationConfiguration authConfig) throws Exception {
		
		return authConfig.getAuthenticationManager();
	}
	
	@Bean
	public AuthenticationProvider getAuthProvider() {
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(myUserDetails);
		auth.setPasswordEncoder(getEncryptPassword());
		
		return auth;
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		http.authenticationProvider(getAuthProvider());
		http.csrf().disable().authorizeRequests()
				.antMatchers(HttpMethod.GET, "/order/allorder").permitAll()
				.antMatchers(HttpMethod.GET, "/order/allorder/*").authenticated()
				.antMatchers(HttpMethod.POST, "/order/order/*").authenticated()
				.antMatchers(HttpMethod.DELETE, "/order/allorder/*").authenticated()
				.antMatchers(HttpMethod.GET, "/order/allorder/byuser/*").authenticated()
				.antMatchers(HttpMethod.PUT, "/order/allorder/cancelorder/*").authenticated()
				.antMatchers(HttpMethod.GET, "/pdf/getpdf/*").authenticated()
				.antMatchers(HttpMethod.GET, "/email/*").authenticated()
				.anyRequest().permitAll()
				.and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
		
	}
	
	
	
	@Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/images/**", "/js/**", "/webjars/**");
    }

	@Bean
	public PasswordEncoder getEncryptPassword() {
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}
}
