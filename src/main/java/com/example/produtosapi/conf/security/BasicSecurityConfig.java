package com.example.produtosapi.conf.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Profile("basic-security")
@EnableWebSecurity
public class BasicSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private InternalPasswordEncoder password;

 	@Autowired
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
 		 		
 		String pwd = password.passwordEncoder().encode("admin");
		auth.inMemoryAuthentication().withUser("admin").password(pwd).roles("REGRAS");
	
 	}
 	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests().anyRequest().authenticated().and().httpBasic()
			.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and().csrf().disable();

	}
	
}
