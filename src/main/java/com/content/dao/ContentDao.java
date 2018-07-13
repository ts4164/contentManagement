package com.content.dao;

import java.util.List;

import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.content.model.Album;
import com.content.model.AlbumContent;
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

	public List<Content> getContentList(Long userId) {
		// TODO Auto-generated method stub
		Query query = this.getEntityManager().createQuery("select content from Content content where content.user.id=:userId and content.type <> 'album'");
		query.setParameter("userId", userId);
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

	public Album createAlbum(Album album) {
		// TODO Auto-generated method stub
		return this.getEntityManager().merge(album);
	}

	public AlbumContent createAlbumContent(AlbumContent albumContent) {
		// TODO Auto-generated method stub
		return this.getEntityManager().merge(albumContent);
	}

	public List<Album> getAlbumList(Long userId) {
		// TODO Auto-generated method stub
		Query query = this.getEntityManager().createQuery("select album from Album album where album.user.id=:userId");
		query.setParameter("userId", userId);
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

	public List<AlbumContent> getAlbumContent(Long albumId) {
		// TODO Auto-generated method stub
		Query query = this.getEntityManager().createQuery("select albumContent from AlbumContent albumContent where albumContent.albumId.id =:albumId");
		query.setParameter("albumId", albumId);
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
