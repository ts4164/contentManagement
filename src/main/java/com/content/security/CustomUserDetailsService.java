package com.content.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.content.dao.UserDao;
import com.content.model.User;


@Service("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService{


	@Autowired
	UserDao userDao;
	
	@Autowired
	private SessionRegistry sessionRegistry; 
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		/*System.out.println("**************************************** User name: "+username);*/
		User user=userDao.getUser(username);
		if(null == user){
			throw new UsernameNotFoundException("No user present with username: "+username);
		}else{
			List<String> userRoles=userDao.findRoleByUsername(username);
			expireUserSessions(user.getUserName());
			return new CustomUserDetails(user,userRoles);
		}
	}
	
	public void expireUserSessions(String username) {
		//boolean status=true;
        for (Object principal : sessionRegistry.getAllPrincipals()) {
            if (principal instanceof CustomUserDetails) {
                CustomUserDetails userDetails = (CustomUserDetails) principal;
                if (userDetails.getUsername().equals(username)) {
                    for (SessionInformation information : sessionRegistry.getAllSessions(userDetails, true)) {
                    	information.expireNow();
                    }
                    //status=false;
                   // break;
                }
            }
        }
        //return status;
	}

}
