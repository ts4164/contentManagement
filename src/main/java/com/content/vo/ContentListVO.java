package com.content.vo;

import java.util.List;

public class ContentListVO {
	List<ContentVO> contentListVo;
	List<AlbumVO> AlbumVOList;
	Long totalRecords;
	
	public List<ContentVO> getContentListVo() {
		return contentListVo;
	}
	public void setContentListVo(List<ContentVO> contentListVo) {
		this.contentListVo = contentListVo;
	}
	public Long getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(Long totalRecords) {
		this.totalRecords = totalRecords;
	}
	public List<AlbumVO> getAlbumVOList() {
		return AlbumVOList;
	}
	public void setAlbumVOList(List<AlbumVO> albumVOList) {
		AlbumVOList = albumVOList;
	}
	
}
