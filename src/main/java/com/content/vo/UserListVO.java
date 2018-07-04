package com.content.vo;

import java.util.List;

public class UserListVO {
List<UserVO> userListVo;
Long totalRecords;

public List<UserVO> getUserListVo() {
	return userListVo;
}
public void setUserListVo(List<UserVO> userListVo) {
	this.userListVo = userListVo;
}
public Long getTotalRecords() {
	return totalRecords;
}
public void setTotalRecords(Long totalRecords) {
	this.totalRecords = totalRecords;
}

}
