package com.content.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.content.dao.UserDao;
import com.content.model.Role;
import com.content.model.User;
import com.content.vo.UserListVO;
import com.content.vo.UserVO;

@Service
public class UserService {

	@Resource
	UserDao userDao;
	
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	
	public User saveUser(UserVO userVo) throws ParseException{
		// TODO Auto-generated method stub
		User user = new User();
		if(userVo.getUserId() != null){
			user.setId(userVo.getUserId());
		}
		user.setName(userVo.getName());
		user.setUserName(userVo.getUserName());
		user.setPassword(userVo.getPassword());
		user.setEmail(userVo.getEmail());
		user.setContactno(userVo.getContactno());
		user.setAddress(userVo.getAddress());
		user.setDateofbirth(df.parse(userVo.getDateofbirth()));
		user.setStatus(userVo.getStatus());
		user.setLock(userVo.getLock());
		//Role role = (Role) userDao.loadObject(Role.class,userVo.getRoleId());
		Role role = (Role) userDao.loadObject(Role.class, userVo.getRoleId());
		
		System.out.println("Role @@@@@@@@@@@@@@@@@@@@");
		//System.out.println(role.getId());
		//System.out.println(role.getRole());                         
		user.setRole_id(role);
		User u = userDao.saveUser(user);
		return u;
	}

	public UserVO getUserDetails(Long userId) {
		// TODO Auto-generated method stub
		User user = userDao.getUserDetails(userId);
		UserVO vo = getUserVo(user);
		return vo;
	}

	private UserVO getUserVo(User user) {
		// TODO Auto-generated method stub
		UserVO vo = new UserVO();
		vo.setAddress(user.getAddress());
		vo.setContactno(user.getContactno());
		vo.setDateofbirth(df.format(user.getDateofbirth()));
		vo.setEmail(user.getEmail());
		vo.setLock(user.getLock());
		vo.setName(user.getName());
		vo.setPassword(user.getPassword());
		vo.setRoleId(user.getRole_id().getId());
		vo.setStatus(user.getStatus());
		vo.setUserId(user.getId());
		vo.setUserName(user.getUserName());	
		return vo;
	}

	public UserListVO getUserList() {
		// TODO Auto-generated method stub
		List<User> userList = userDao.getUserList(); 
		List<UserVO> listUserVo = new ArrayList<>(userList.size());
		Long totalRecords = (long)0;
		for(User u : userList){
			UserVO vo = this.getUserVo(u);	
			listUserVo.add(vo);
			totalRecords++;
		}
		UserListVO userListVo = new UserListVO();
		userListVo.setUserListVo(listUserVo);
		userListVo.setTotalRecords(totalRecords);
		return userListVo;
	}

}
