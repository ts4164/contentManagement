package com.content.dao;

import java.util.List;

import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.content.model.Content;

@Repository("contentDao")
@Transactional
public class ContentDao extends EntityManagerDao{

	public Content saveContent(Content content) {
		// TODO Auto-generated method stub
		return this.getEntityManager().merge(content);
	}

	public Content getContentDetails(Long contentId) {
		// TODO Auto-generated method stub
		Query query = this.getEntityManager().createQuery("select content from Content content where content.id=:contentId");
		query.setParameter("contentId", contentId);
		try{
		return (Content) query.getSingleResult();
		}
		catch(Exception e){
			return null;
		}
		finally
		{
			this.getEntityManager().close();
		}
	
	}

	public List<Content> getContentList() {
		// TODO Auto-generated method stub
		Query query = this.getEntityManager().createQuery("select content from Content content");
		try{
		return query.getResultList();
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
