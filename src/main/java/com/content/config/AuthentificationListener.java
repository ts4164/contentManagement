package com.content.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

public class AuthentificationListener implements AuthenticationFailureHandler{

	@Override
	public void onAuthenticationFailure(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException authexcp)
					throws IOException, ServletException {
		/*System.out.println(authexcp+" ************");*/
		if(authexcp.getClass().isAssignableFrom(UsernameNotFoundException.class)) {
			response.sendRedirect("login?error");
		} else if (authexcp.getClass().isAssignableFrom(DisabledException.class)) {
			response.sendRedirect("login?disabled");
		}
		else if (authexcp.getClass().isAssignableFrom(CredentialsExpiredException.class)) {
			response.sendRedirect("login?block");
		}
		else if (authexcp.getClass().isAssignableFrom(BadCredentialsException.class)) {
			response.sendRedirect("login?error");
		}
		else if (authexcp.getClass().isAssignableFrom(LockedException.class)) {
			response.sendRedirect("login?locked");
		}
		else if (authexcp.getClass().isAssignableFrom(AccountExpiredException.class)) {
			response.sendRedirect("login?accountExpired");
		}
	}
}

