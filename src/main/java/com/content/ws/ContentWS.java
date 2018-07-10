package com.content.ws;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.content.model.Album;
import com.content.model.Content;
import com.content.service.ContentService;
import com.content.vo.AlbumContentVO;
import com.content.vo.AlbumVO;
import com.content.vo.ContentListVO;
import com.content.vo.ContentVO;

@RestController
public class ContentWS {
	
	@Autowired
	ContentService contentService;

	@RequestMapping(value = "/saveAndUploadContent",method = RequestMethod.POST)
	public String[] saveAndUploadContent(HttpServletRequest request,@RequestParam("file") final MultipartFile[] uploadFiles, @RequestParam("contentDescription") String contentDescription, @RequestParam("contentType") String contentType, @RequestParam("user_id") Long user_id) throws IllegalStateException, IOException{
		String[] files = null;
		
		if(contentType.equals("album")){
			System.out.println("@@@@@@@@@@@@ Create Album @@@@@@@@@@@");
			Content contentId = null;
			AlbumVO albumVO = new AlbumVO();
			albumVO.setAlbumName(contentDescription);
			albumVO.setAlbumDescription(contentDescription);
			albumVO.setUserId(user_id);			
			Album albumId = contentService.createAlbum(albumVO);
			
			ContentVO contentVo1 = new ContentVO();
			if(null != uploadFiles && uploadFiles.length > 0 )
			 {
			for(MultipartFile uploadedFiles : uploadFiles) {
				String fileName =  uploadedFiles.getOriginalFilename();
				Long fileSize= uploadedFiles.getSize()/1000;
		
				try {
					String uploadsDir = "/contentFiles/userId_"+user_id+"/"+contentDescription+"/";
	                String realPathtoUploads =  request.getServletContext().getRealPath(uploadsDir);
	                if(! new File(realPathtoUploads).exists())
	                {
	                    new File(realPathtoUploads).mkdir();
	                }
	                String filePath = realPathtoUploads+fileName;
	                File destination = new File(filePath);
	                uploadedFiles.transferTo(destination);
			
	            }catch(UnsupportedEncodingException e){
	            }
				
				contentVo1.setContentPath("/contentFiles/userId_"+user_id+"/"+contentDescription+"/"+fileName);
				contentVo1.setContentDescription(contentDescription);
				contentVo1.setType(contentType);
				contentVo1.setUserId(user_id);
				contentId = contentService.saveContent(contentVo1);	
				
				AlbumContentVO albumContentVO = new AlbumContentVO();
				albumContentVO.setAlbumId(albumId.getId());
				albumContentVO.setContentId(contentId.getId());
				contentService.createAlbumContent(albumContentVO);
				
			}
		}
			
			String dirPath1 = "/contentFiles/userId_"+user_id+"/"+contentDescription+"/";
			String checkPath1 =  request.getServletContext().getRealPath(dirPath1);
			File dir1 = new File(checkPath1);
			files = dir1.list();
			if (files.length == 0) {
			    System.out.println("The directory is empty");
			} else {
			    for (String aFile : files) {
			       System.out.println(aFile);
			    }
			}
					
		}else{
			ContentVO contentVo = new ContentVO();	
			if(null != uploadFiles && uploadFiles.length > 0 )
			 {
			for(MultipartFile uploadedFiles : uploadFiles) {
				String fileName =  uploadedFiles.getOriginalFilename();
				Long fileSize= uploadedFiles.getSize()/1000;
		
				try {
					String uploadsDir = "/contentFiles/userId_"+user_id+"/";
	                String realPathtoUploads =  request.getServletContext().getRealPath(uploadsDir);
	                if(! new File(realPathtoUploads).exists())
	                {
	                    new File(realPathtoUploads).mkdir();
	                }
	                String filePath = realPathtoUploads+fileName;
	                File destination = new File(filePath);
	                uploadedFiles.transferTo(destination);
			
	            }catch( UnsupportedEncodingException e){
	            }
				
				contentVo.setContentPath("/contentFiles/userId_"+user_id+"/"+fileName);
				contentVo.setContentDescription(contentDescription);
				contentVo.setType(contentType);
				contentVo.setUserId(user_id);
				contentService.saveContent(contentVo);		
			}
		}
			String dirPath = "/contentFiles/userId_"+user_id+"/";
			String checkPath =  request.getServletContext().getRealPath(dirPath);
			File dir = new File(checkPath);
			files = dir.list();
			if (files.length == 0) {
			    System.out.println("The directory is empty");
			} else {
			    for (String aFile : files) {
			       System.out.println(aFile);
			    }
			}
		}
		
		return files;
	}
	
	@RequestMapping(value = "/updateContent",method = RequestMethod.POST)
	public Content updateContent(HttpServletRequest request){
		ContentVO contentVo = new ContentVO();
		contentVo.setContentId(Long.parseLong(request.getParameter("editContentId")));
		contentVo.setContentPath(request.getParameter("editContentPath"));
		contentVo.setContentDescription(request.getParameter("editContentDescription"));
		contentVo.setType(request.getParameter("editContentType"));
		contentVo.setUserId(Long.parseLong(request.getParameter("editUserId")));
		return contentService.saveContent(contentVo);	
	}
	
	@RequestMapping(value = "/getContentDetails",method = RequestMethod.POST)
	public ContentVO getContentDetails(Long contentId){
		return contentService.getContentDetails(contentId);
	}
	
	@RequestMapping(value = "/getContentList",method = RequestMethod.GET)
	public ContentListVO getContentList(){
		return contentService.getContentList();
	}
}
