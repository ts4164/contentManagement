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
@Table(name = "content")
public class Content implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	 Long id;
	 String eventPath;
	 Date uploadTime;
	 String type;
	User user;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "content_sequence")
	@SequenceGenerator(name="content_sequence", sequenceName = "content_sequence", allocationSize=50)
	@Column(name = "content_id", updatable = false, nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name="event_path")
	public String getEventPath() {
		return eventPath;
	}

	public void setEventPath(String eventPath) {
		this.eventPath = eventPath;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="upload_time")
	public Date getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}
	
	@Column(name="type")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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
