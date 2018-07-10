package com.content.vo;

import java.util.List;

public class ContentListVO {
	List<ContentVO> contentListVo;
	List<AlbumVO> albumListVO;
	List<AlbumContentVO> albumContentListVO;
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
	public List<AlbumVO> getAlbumListVO() {
		return albumListVO;
	}
	public void setAlbumListVO(List<AlbumVO> albumListVO) {
		this.albumListVO = albumListVO;
	}
	public List<AlbumContentVO> getAlbumContentListVO() {
		return albumContentListVO;
	}
	public void setAlbumContentListVO(List<AlbumContentVO> albumContentListVO) {
		this.albumContentListVO = albumContentListVO;
	}
	
}
