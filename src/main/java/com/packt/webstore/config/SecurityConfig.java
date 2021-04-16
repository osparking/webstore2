package com.packt.webstore.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.packt.webstore.domain.User;
import com.packt.webstore.service.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) 
			throws Exception {
		for (User u : userService.getAllUsers()) {
			auth.inMemoryAuthentication().withUser(u.getUsername())
			.password(u.getPassword())
			.roles(u.getRoles().toArray(new String[0]))
			.and().passwordEncoder(passwordEncoder);
		}
	}
	
	@Autowired
	AuthenticationSuccessHandlerImpl authenticationSuccessHandler;
	
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.formLogin().loginPage("/login").permitAll()
				.usernameParameter("userId").passwordParameter("password");
		httpSecurity.formLogin()
				.successHandler(authenticationSuccessHandler)
				.defaultSuccessUrl("/market/customers")
				.failureUrl("/login?error");
		httpSecurity.logout().logoutSuccessUrl("/login?logout");
		httpSecurity.exceptionHandling().accessDeniedPage("/login?accessDenied");
		httpSecurity.authorizeRequests()
				.antMatchers("/").permitAll()
				.antMatchers("/**/products/add").access("hasRole('ADMIN')")
				.antMatchers("/**/customers/add").access("hasRole('SERVICE')")
				.antMatchers("/**/market/**").access("hasRole('USER')");
		httpSecurity.formLogin().successHandler(authenticationSuccessHandler);
		httpSecurity.csrf().disable();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
