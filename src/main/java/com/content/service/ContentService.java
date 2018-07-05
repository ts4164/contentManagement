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
import com.content.model.Content;
import com.content.model.User;
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

	public ContentListVO getContentList() {
		// TODO Auto-generated method stub
		List<Content> contentList = contentDao.getContentList();
		List<ContentVO> contentVoList = new ArrayList<>(contentList.size());
		Long totalRecords = (long)0;
		for(Content c : contentList){
			ContentVO vo = this.getContentVo(c);
			contentVoList.add(vo);
			totalRecords++;
		}
		ContentListVO contentListVo = new ContentListVO();
		contentListVo.setContentListVo(contentVoList);
		contentListVo.setTotalRecords(totalRecords);
		return contentListVo;
	}	
}
