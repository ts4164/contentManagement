<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<style>
.tableDiv{
    background: white;
    padding: 15px;
    border-radius: 5px;
    box-shadow: 1px 1px 16px 1px #d4dffb;
    margin-top:15px;
}
</style>
<script type="text/javascript" >
$(document).ready(function(){
	contentManagement();
});

function contentManagement(){
	$.ajax({
		url:'/getContentList',
		type:'GET',
		success:function(response, textStatus, jqXHR){
			
			console.log("Check Content List @@@@@@@@@@@@@");
			console.log(response);
		var tableItem="";
	/* 	tableItem += '<table id="contentManagement" class="display">';
		tableItem += '<thead>';
		tableItem += '<tr>';
		tableItem +='<th align="left" width="10%">Sr.No.</th>'
		tableItem +='<th align="left">Name</th>'
		tableItem += '<th align="left">Email</th>'
		tableItem += '<th align="left">Role</th>'
		tableItem += '<th align="left">Status</th>'
		tableItem += '</tr>';
		tableItem += '</thead> ';
		tableItem += '<tbody>';
		for(i=0;i<response.totalRecords;i++){
		tableItem += '<tr>';
		tableItem += '<td>'+(i+1)+'</td>';
		tableItem += '<td>'+response.userListVo[i].name+'</td>';
		tableItem += '<td>'+response.userListVo[i].email+'</td>';
		tableItem += '<td>'+response.userListVo[i].roleId+'</td>';
		tableItem += '<td>'+response.userListVo[i].status+'</td>';
		tableItem += '</tr>';
		}
		tableItem += '</tbody>';
		
		tableItem += '</table>';
		$('#ContentDiv').html('');
		$('#ContentDiv').html(tableItem);
		$('#contentManagement').DataTable(); */	
		for(i=0;i<response.totalRecords;i++){
		
			
		if(response.contentListVo[i].type == "image")
			{
		$("#ContentDiv").append("<div id=innerDiv"+response.contentListVo[i].contentId+">"
				+"<div>"+response.contentListVo[i].contentDescription+"   "+response.contentListVo[i].uploadTime+"</div>"		
				+"<img src="+response.contentListVo[i].contentPath+" style=width:20%; width:20% ></div>");
		
			}
		
		if(response.contentListVo[i].type == "video")
		{
		$("#ContentDiv").append("<div id=innerDiv"+response.contentListVo[i].contentId+">"
			+"<div>"+response.contentListVo[i].contentDescription+"   "+response.contentListVo[i].uploadTime+"</div>"		
			+"<video style=width:20%; width:20% controls><source src="+response.contentListVo[i].contentPath+" type=video/mp4></video></div>");
		}
	
		if(response.contentListVo[i].type == "audio")
		{
		$("#ContentDiv").append("<div id=innerDiv"+response.contentListVo[i].contentId+">"
			+"<div>"+response.contentListVo[i].contentDescription+"   "+response.contentListVo[i].uploadTime+"</div>"		
			+"<audio style=width:20%; width:20% controls><source src="+response.contentListVo[i].contentPath+" type=audio/ogg></audio></div>");
		}
		
	}
		if(response.albumVOList.length != 0)
		{
			for(var i=0;i<response.albumVOList.length;i++){
			//console.log("@@@@@@@@@@@@@@@ album centent "+response.albumDetailsListVO.length);
			$("#ContentDiv").append("<div id=albumDiv"+response.albumVOList[i].albumId+"><div>Album name : "+response.albumVOList[i].albumName+"</div>"
					+"<div id=imgConDiv"+response.albumVOList[i].albumId+"></div></div>");
			
			for(var j=0;j<response.albumVOList[i].contentVO.length;j++){
				$("#imgConDiv"+response.albumVOList[i].albumId).append("<div id=img"+response.albumVOList[i].contentVO[j].contentId+"><img src="+response.albumVOList[i].contentVO[j].contentPath+" style=width:10%; width:10% ></div></div>");
			}
			
			}
		}		
}
	});
}

function displayProgressBars(id)
{
	if(id== "fileUpload"){
	if ($("#fileUpload").val() == null || $("#fileUpload").val() == "") {
		$('#fileUploadError').html('<p  style="font-size:10px; color:red">Please Add Policie');
		return false;
	}
	else{
		$('#fileUploadError').html("");
	}
	var fileSelect = document.getElementById('fileUpload');
	var files = fileSelect.files;
	//if(validatefileSize(fileSelect,'fileUpload')){
		for (var i = 0; i < files.length; i++) {
		var file = files[i];
		   $("#toAppendProgressBars").append('<div class="progress" style="width:50%;height: 17px;background-color:red; " id="multipleProgressBars'+i+'"></div><div style="float:left;margin-left:55%;margin-top:-40px"></div>');
		   $("#multipleProgressBars"+i).append('<div class="progress-bar progress-bar-striped active progress-bar-success" role="progressbar" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width:0%" id="documentProgressbar'+i+'">'+file.name+'&nbsp;&nbsp;&nbsp;uploaded...</div>');
		   $('#documentProgressbar'+i).css('width',  '100%');
		}
		document.getElementById('addContentButton').disabled = false;
	//}else{
		//document.getElementById('addContentButton').disabled = true;
	//}
	}
}

function validateSaveContent(){
	
	var contentDescription = document.forms["saveContentForm"]["contentDescription"].value;
	if(contentDescription == null || contentDescription == "")
		{
		$('#contentDescription').css('border-color','red');
		$('#description_err').text("Please add description");
		return false;
		}else{
		$('#contentDescription').css('border-color','');	
		}
	
	var contentType = document.forms["saveContentForm"]["contentType"].value;
	if(contentType == null || contentType == "" || contentType =="-- Select --")
		{
		$('#contentType').css('border-color','red');
		$('#type_err').text("Please select type");
		return false;
		}else{
		$('#contentType').css('border-color','');	
		}
	
	var fileSelect = document.getElementById('fileUpload');
	var files = fileSelect.files;
	//console.log(" files length "+files.length);
	if(files.length == 0)
		{
		$('#fileUpload').css('border-color','red');
		$('#file_err').text("Please upload file");
		return false;
		}else{
		$('#fileUpload').css('border-color','');	
		}	
return true;	
}

function saveContent(btnId){
	if(validateSaveContent()){
		
	var fileSelect = document.getElementById('fileUpload');
	var files = fileSelect.files;
	var form_data = new FormData($('#newUpload')[0]);
	 for (var i = 0; i < files.length; i++) {
		var file = files[i];
	 }
	 
	 for (var i = 0; i < files.length; i++) {
			var file = files[i];
			   form_data.append('file', file);	   
			}
	//var user_id = $("#login_userId").val();
	    var user_id = 3;
		var contentType = $("#contentType").val();
		var contentDescription = $("#contentDescription").val();
	   form_data.append("contentDescription", contentDescription);
	   form_data.append("contentType", contentType);
	   form_data.append("user_id", user_id);
	 
		$.ajax({
	        url: "/saveAndUploadContent",
	        dataType: 'JSON',
	        cache: false,
	        contentType: false,
	        processData: false,
	        enctype: 'multipart/form-data',
	        data: form_data,                         // Setting the data attribute of ajax with file_data
	        type: 'post',
	        success : function(response, textStatus, jqXHR) {
	        	location.reload();
	    	},
	    	error: function (response) {
	    	},                	
		});
	}		
}

</script>

<div class="hrader-content">Content <button id="addContent" data-toggle="modal" data-target="#AddContentModal" title="Add User">
			<i class="material-icons">add_circle_outline</i></button></div>
<div id="ContentDiv" class="tableDiv" style="padding-top: 15px;"></div>


	<!--Add Content Modal -->	
	<div class="modal fade" id="AddContentModal" role="dialog">
	    <div class="modal-dialog">
	      <div class="modal-content">
	        <div class="modal-header">
	          <button id="closeAddContentModal" type="button" class="close" data-dismiss="modal">&times;</button>
	          <h4 class="modal-title">Add Content</h4>
	        </div>
	        <div class="modal-body" style="padding: 0;">
				<div style="">
				<form role="form" method="POST" id="saveContentForm" name="saveContentForm" action="/saveContent" onsubmit="return validateSaveContent()">
					<table class="table2">
						<tr>
							<td style="width: 15%;"><label class="formLabel">Content Description :</label></td>
							<td><input type="text" id="contentDescription" placeholder="Description" name="contentDescription"></td>
							<td style="width: 15%;"><label class="formLabel">Content Type :</label></td>
						<td><select id="contentType" class="selectDropdown" name="contentType">
				         	 		<option>-- Select --</option>
			   					 	<option value="text">Text</option>
			   					 	<option value="audio">Audio</option>
			   					 	<option value="video">Video</option>
			   					 	<option value="image">Image</option>
			   					 	<option value="album">Album</option>
			  					</select>
			  				</td>
						</tr>
						<tr>
							<td></td>
							<td><span id="description_err" style="color:red;font-size:10px"></span></td>
							<td></td>
							<td><span id="type_err" style="color:red;font-size:10px"></span></td>
						</tr>
						<tr>
							<td><label class="formLabel">Upload:</label></td>
							<td><input type="file" id="fileUpload" name="fileUpload" accept=".pdf,.jpg,.jpeg,.png,.mp4,.mp3,.ogg,mpeg" onchange="displayProgressBars(this.id)" multiple="multiple"></td>
							<td></td>
							<td></td>
						</tr>		
						<tr>
							<td></td>
							<td><span id="file_err" style="color:red;font-size:10px"></span></td>
							<td></td>
							<td></td>
						</tr>
						<tr>
						<td colspan="4">
			<div id="toAppendProgressBars" style="margin-top:20px;"></div>
			</td>
			</tr>
					</table>
					</form>	
				</div>
		       	<div class="modal-footer">
		         	<button id="addContentButton" type="button" class="btn btn-default" style="float: right;" title="Save user" onclick="saveContent(this.id)">Save</button>
		        </div>
			</div>
	      </div>
	    </div>
	  </div>  					