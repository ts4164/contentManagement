package com.content.dao;

import java.util.List;

import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.content.model.User;


@Repository("userDao")
@Transactional
public class UserDao extends EntityManagerDao{

	public User getUser(String username) {
		// TODO Auto-generated method stub
		try {
			Query query=this.getEntityManager().createQuery("from User user where user.userName=:userName");
			query.setParameter("userName", username);
			User user=(User) query.getSingleResult();
			return user;
		} catch (javax.persistence.NoResultException e) {
			return null;
		}
		finally
		{
			this.getEntityManager().close();
		}
	}
	

	public List<String> findRoleByUsername(String username) {
		// TODO Auto-generated method stub
		Query query=this.getEntityManager().createQuery("select r.role from Role r,User user where user.userName=:username and user.role_id.id=r.id");
		query.setParameter("username", username);
		List<String> role=query.getResultList();
	  try{
		return role;
	  }
		catch(Exception e){
			return null;
		}
		finally
		{
			this.getEntityManager().close();
		}
	
}


	public User saveUser(User user) {
		// TODO Auto-generated method stub
		return this.getEntityManager().merge(user);
	}


	public User getUserDetails(Long userId) {
		// TODO Auto-generated method stub
		Query query = this.getEntityManager().createQuery("select user from User user where user.id=:userId");
		query.setParameter("userId", userId);
		try{
		return (User) query.getSingleResult();
		}
		catch(Exception e){
			return null;
		}
		finally
		{
			this.getEntityManager().close();
		}
	}


	public List<User> getUserList() {
		// TODO Auto-generated method stub
		Query query = this.getEntityManager().createQuery("select user from User user");
        List<User> userList = query.getResultList();
    	try{
		return userList;
    	}
		catch(Exception e){
			return null;
		}
		finally
		{
			this.getEntityManager().close();
		}
	}
}
