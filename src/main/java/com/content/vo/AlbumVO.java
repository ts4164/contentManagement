package com.content.vo;

import java.util.List;

public class AlbumVO {

	Long albumId;
	 String albumName;
	 String albumDescription;
	 String uploadTime;
	 Long userId;
	 List<ContentVO> contentVO;
	 
	public Long getAlbumId() {
		return albumId;
	}
	public void setAlbumId(Long albumId) {
		this.albumId = albumId;
	}
	public String getAlbumName() {
		return albumName;
	}
	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}
	public String getAlbumDescription() {
		return albumDescription;
	}
	public void setAlbumDescription(String albumDescription) {
		this.albumDescription = albumDescription;
	}
	public String getUploadTime() {
		return uploadTime;
	}
	public void setUploadTime(String uploadTime) {
		this.uploadTime = uploadTime;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public List<ContentVO> getContentVO() {
		return contentVO;
	}
	public void setContentVO(List<ContentVO> contentVO) {
		this.contentVO = contentVO;
	}
	 
}
