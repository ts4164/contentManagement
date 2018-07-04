package com.content.vo;

public class ContentVO {

	Long contentId;
	String eventPath;
	String uploadTime;
	String type;
	Long userId;
	
	public Long getContentId() {
		return contentId;
	}
	public void setContentId(Long contentId) {
		this.contentId = contentId;
	}
	public String getEventPath() {
		return eventPath;
	}
	public void setEventPath(String eventPath) {
		this.eventPath = eventPath;
	}
	public String getUploadTime() {
		return uploadTime;
	}
	public void setUploadTime(String uploadTime) {
		this.uploadTime = uploadTime;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}

}
