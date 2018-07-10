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

@Entity
@Table(name = "album_content")
public class AlbumContent implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Long id;
	Album albumId;
	Content content;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "album_content_sequence")
	@SequenceGenerator(name="album_content_sequence", sequenceName = "album_content_sequence", allocationSize=50)
	@Column(name = "album_content_id", updatable = false, nullable = false)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@OneToOne
	@JoinColumn(name="album_id")
	public Album getAlbumId() {
		return albumId;
	}
	public void setAlbumId(Album albumId) {
		this.albumId = albumId;
	}
	
	@OneToOne
	@JoinColumn(name="content_id")
	public Content getContent() {
		return content;
	}
	public void setContent(Content content) {
		this.content = content;
	}
	
}
