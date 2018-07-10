package com.content.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "albums")
public class Album implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Long id;
	 String albumName;
	 String albumDescription;
	 Date uploadTime;
	 User user;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "album_sequence")
	@SequenceGenerator(name="album_sequence", sequenceName = "album_sequence", allocationSize=50)
	@Column(name = "album_id", updatable = false, nullable = false)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name="album_name")
	public String getAlbumName() {
		return albumName;
	}
	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}
	
	@Column(name="album_description")
	public String getAlbumDescription() {
		return albumDescription;
	}
	public void setAlbumDescription(String albumDescription) {
		this.albumDescription = albumDescription;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="upload_time")
	public Date getUploadTime() {
		return uploadTime;
	}
	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}
	
	@OneToOne
	@JoinColumn(name="user_id")
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
			
}
