package com.content.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

//@Configuration
public class AuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler
{
	
    protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response) 
    {
        // Get the role of logged in user
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String role = auth.getAuthorities().toString(); 
        
        String targetUrl = "";
        if(role.contains("ROLE_ADMIN"))
        {
            targetUrl = "/adminProfile";
        }
        else if(role.contains("ROLE_USER")) 
        {
            targetUrl = "/userProfile";
        }
        return targetUrl;
    }
}