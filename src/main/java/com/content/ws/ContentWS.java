package com.content.ws;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.content.model.Content;
import com.content.service.ContentService;
import com.content.vo.ContentListVO;
import com.content.vo.ContentVO;

@RestController
public class ContentWS {
	
	@Autowired
	ContentService contentService;

	@RequestMapping(value = "/saveContent",method = RequestMethod.POST)
	public Content saveContent(HttpServletRequest request){
		ContentVO contentVo = new ContentVO();
		contentVo.setEventPath(request.getParameter("eventPath"));
		contentVo.setType(request.getParameter("contentType"));
		contentVo.setUserId(Long.parseLong(request.getParameter("userId")));
		return contentService.saveContent(contentVo);	
	}
	
	@RequestMapping(value = "/updateContent",method = RequestMethod.POST)
	public Content updateContent(HttpServletRequest request){
		ContentVO contentVo = new ContentVO();
		contentVo.setContentId(Long.parseLong(request.getParameter("editContentId")));
		contentVo.setEventPath(request.getParameter("editEventPath"));
		contentVo.setType(request.getParameter("editContentType"));
		contentVo.setUserId(Long.parseLong(request.getParameter("editUserId")));
		return contentService.saveContent(contentVo);	
	}
	
	@RequestMapping(value = "/getContentDetails",method = RequestMethod.POST)
	public ContentVO getContentDetails(Long contentId){
		return contentService.getContentDetails(contentId);
	}
	
	@RequestMapping(value = "/getContentList",method = RequestMethod.POST)
	public ContentListVO getContentList(){
		return contentService.getContentList();
	}
}
