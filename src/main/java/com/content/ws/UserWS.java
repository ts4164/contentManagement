package com.content.ws;

import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.content.model.User;
import com.content.service.UserService;
import com.content.vo.UserListVO;
import com.content.vo.UserVO;

@RestController
public class UserWS {

	@Autowired
	UserService userService;
	
	PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	@RequestMapping(value = "/saveUser", produces = { MediaType.APPLICATION_JSON_VALUE },method = RequestMethod.POST)
	public User saveUser(HttpServletRequest request) throws ParseException{
		UserVO userVo = new UserVO();
		userVo.setAddress(request.getParameter("address"));
		userVo.setContactno(request.getParameter("contactNo"));
		userVo.setDateofbirth(request.getParameter("dateOfBirth"));
     	userVo.setEmail(request.getParameter("email"));
		
		userVo.setName(request.getParameter("name"));
		userVo.setPassword(passwordEncoder.encode(request.getParameter("password")));
		
		userVo.setRoleId(Long.parseLong(request.getParameter("selectRole")));
		
		userVo.setUserName(request.getParameter("userName"));
		userVo.setLock(request.getParameter("lock"));
		userVo.setStatus(request.getParameter("status"));
		return userService.saveUser(userVo);
	}
	
	@RequestMapping(value = "/getUserDetails",method = RequestMethod.POST)
	public UserVO getUserDetails(Long userId){
		return userService.getUserDetails(userId);
	}
	
	@RequestMapping(value = "/updateUser",method = RequestMethod.POST)
	public User updateUser(HttpServletRequest request) throws ParseException{
		UserVO userVo = new UserVO();
		userVo.setUserId(Long.parseLong(request.getParameter("editUserId")));
		userVo.setAddress(request.getParameter("editAddress"));
		userVo.setContactno(request.getParameter("editContactNo"));
		userVo.setDateofbirth(request.getParameter("editDateOfBirth"));
		userVo.setEmail(request.getParameter("editEmail"));
		userVo.setLock(request.getParameter("editLock"));
		userVo.setName(request.getParameter("editName"));
		userVo.setPassword(passwordEncoder.encode(request.getParameter("editPassword")));
		userVo.setRoleId(Long.parseLong(request.getParameter("editRoleid")));
		userVo.setStatus(request.getParameter("editStatus"));
		userVo.setUserName(request.getParameter("editUserName"));
		return userService.saveUser(userVo);
	}
	
	@RequestMapping(value = "/getUserList",method = RequestMethod.GET)
	public UserListVO getUserList(){
		return userService.getUserList();
	}
}
