package com.content.config;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.content.security.CustomUserDetailsService;


@Configuration
@EnableWebSecurity
@ComponentScan(basePackageClasses = CustomUserDetailsService.class)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	@Autowired
	 private UserDetailsService userDetailsService;

	 @Autowired
	 private SessionRegistry sessionRegistry;

	 
	 @Autowired
	 public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception{
	 auth.userDetailsService(userDetailsService).passwordEncoder(passwordencoder());
	} 
	 
	@Override
	    protected void configure(HttpSecurity http) throws Exception {
		 http.authorizeRequests()
		.antMatchers("/adminProfile").access("hasRole('ROLE_ADMIN')")
		.antMatchers("/userProfile").access("hasRole('ROLE_USER')")
		
		 .anyRequest().permitAll().and().exceptionHandling()
		 .and()
		 .formLogin()
		 .successHandler(new AuthenticationSuccessHandler())
		 .failureHandler(new AuthentificationListener())
		 .loginPage("/login")
		 .usernameParameter("username").passwordParameter("password")
		 .and()
		 .logout().logoutSuccessUrl("/login")
		 .and()
		 .exceptionHandling().accessDeniedPage("/login")
		 .and()
		 .csrf().disable().rememberMe().key("Key").rememberMeParameter("remember").rememberMeCookieName("Remember")
		 .tokenValiditySeconds(86400).and().sessionManagement().sessionFixation().changeSessionId().maximumSessions(1)
		 .maxSessionsPreventsLogin( true).expiredUrl("/login?expired" ).sessionRegistry(sessionRegistry );
	    }

	@Bean(name="passwordEncoder")
	public PasswordEncoder passwordencoder() {
	    	// TODO Auto-generated method stub
	    	return new BCryptPasswordEncoder();
	    }
	    	    
}

