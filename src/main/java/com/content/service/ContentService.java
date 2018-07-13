package com.content.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.content.dao.ContentDao;
import com.content.model.Album;
import com.content.model.AlbumContent;
import com.content.model.Content;
import com.content.model.User;
import com.content.vo.AlbumContentVO;
import com.content.vo.AlbumVO;
import com.content.vo.ContentListVO;
import com.content.vo.ContentVO;

@Service
public class ContentService {

	@Resource
	ContentDao contentDao;

	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public Content saveContent(ContentVO contentVo) {
		// TODO Auto-generated method stub
		Date date = new Date();
		//System.out.println(dateFormat.format(date)); //2016/11/16 12:08:43
		Content content = new Content();
		if(contentVo.getContentId() != null){
			content.setId(contentVo.getContentId());
		}
		content.setContentPath(contentVo.getContentPath());
		content.setContentDescription(contentVo.getContentDescription());
		content.setType(contentVo.getType());
		User user = (User) contentDao.loadObject(User.class, contentVo.getUserId());
		content.setUser(user);
		content.setUploadTime(date);
		return contentDao.saveContent(content);
	}

	public ContentVO getContentDetails(Long contentId) {
		// TODO Auto-generated method stub
		Content content = contentDao.getContentDetails(contentId);
		ContentVO contentVo = this.getContentVo(content);
		return contentVo;
	}

	private ContentVO getContentVo(Content content) {
		// TODO Auto-generated method stub
		ContentVO vo = new ContentVO();
		vo.setContentId(content.getId());
		vo.setContentPath(content.getContentPath());
	    vo.setContentDescription(content.getContentDescription());
		vo.setType(content.getType());
		vo.setUploadTime(dateFormat.format(content.getUploadTime()));
		vo.setUserId(content.getUser().getId());
		return vo;
	}

	public ContentListVO getContentList(Long userId) {
		// TODO Auto-generated method stub
		List<Content> contentList = contentDao.getContentList(userId);
		List<ContentVO> contentVoList = new ArrayList<>(contentList.size());
		Long totalRecords = (long)0;
		for(Content c : contentList){
			ContentVO vo = this.getContentVo(c);
			contentVoList.add(vo);
			totalRecords++;
		}
		
		List<Album> albumList = contentDao.getAlbumList(userId);
		List<AlbumVO> AlbumVOLst = new ArrayList<>(albumList.size());
		for(Album a : albumList){
			//System.out.println("@@@@@@@@@@@@@@@: "+a.getId()+" @@@@@ "+a.getUser().getId());
			List<AlbumContent> albumContentList = contentDao.getAlbumContent(a.getId());
			//albumDetailsVOList = new ArrayList<>(albumContentList.size());
			//for(AlbumContent ac : albumContentList){
				//AlbumDetailsVO adVo = this.getalbumDetails(a,ac);
				//System.out.println("@@@@@@@@@@@@@@@: "+a.getId()+" @@@@@ "+ac.getContent().getContentPath());
				//albumDetailsVOList.add(adVo);
			//}
			
			AlbumVO aVO = this.getAVO(a,albumContentList);
			AlbumVOLst.add(aVO);
		}
		
		ContentListVO contentListVo = new ContentListVO();
		contentListVo.setContentListVo(contentVoList);
		contentListVo.setTotalRecords(totalRecords);
		//contentListVo.setAlbumDetailsListVO(albumDetailsVOList);
		contentListVo.setAlbumVOList(AlbumVOLst);
		return contentListVo;
	}

	private AlbumVO getAVO(Album a, List<AlbumContent> albumContentList) {
		// TODO Auto-generated method stub
		AlbumVO av = new AlbumVO();
		List<ContentVO> conList = new ArrayList<>(albumContentList.size());
		for(AlbumContent acl : albumContentList)
		{
			ContentVO cvo = this.getACVO(acl);
			conList.add(cvo);
		}
		av.setAlbumDescription(a.getAlbumDescription());
		av.setAlbumId(a.getId());
		av.setAlbumName(a.getAlbumName());
		av.setUploadTime(dateFormat.format(a.getUploadTime()));
		av.setUserId(a.getUser().getId());
		av.setContentVO(conList);	
		return av;
	}

	private ContentVO getACVO(AlbumContent acl) {
		// TODO Auto-generated method stub
		ContentVO cv = new ContentVO();
		cv.setContentId(acl.getContent().getId());
		cv.setContentDescription(acl.getContent().getContentDescription());
		cv.setContentPath(acl.getContent().getContentPath());
		cv.setType(acl.getContent().getType());
		cv.setUploadTime(dateFormat.format(acl.getContent().getUploadTime()));
		cv.setUserId(acl.getContent().getUser().getId());
		return cv;
	}

	public Album createAlbum(AlbumVO albumVO) {
		// TODO Auto-generated method stub
		Date date = new Date();
		Album album = new Album();
		album.setAlbumName(albumVO.getAlbumName());
		album.setAlbumDescription(albumVO.getAlbumDescription());
		album.setUploadTime(date);
		User user = (User) contentDao.loadObject(User.class, albumVO.getUserId());
		album.setUser(user);
		return contentDao.createAlbum(album);
	}

	public AlbumContent createAlbumContent(AlbumContentVO albumContentVO) {
		AlbumContent albumContent = new AlbumContent();
		Album album = (Album) contentDao.loadObject(Album.class, albumContentVO.getAlbumId());
		Content content = (Content) contentDao.loadObject(Content.class, albumContentVO.getContentId());
		albumContent.setAlbumId(album);
		albumContent.setContent(content);
		return contentDao.createAlbumContent(albumContent);		
	}
	
}
