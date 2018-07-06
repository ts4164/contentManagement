<style>
.tableDiv{
    background: white;
    padding: 15px;
    border-radius: 5px;
    box-shadow: 1px 1px 16px 1px #d4dffb;
    margin-top:15px;
}
</style>
<script type="text/javascript">
$(document).ready(function() {
	userManagement();
});

function userManagement(){
	$.ajax({
		url:'/getUserList',
		type:'GET',
		success:function(response, textStatus, jqXHR){
			console.log(response);
		var tableItem="";
		tableItem += '<table id="userManagement" class="display">';
		tableItem += '<thead>';
		tableItem += '<tr>';
		tableItem +='<th align="left" width="10%">Sr.No.</th>'
		tableItem +='<th align="left">Name</th>'
		tableItem += '<th align="left">Email</th>'
		tableItem += '<th align="left">Role</th>'
		tableItem += '<th align="left">Status</th>'
		tableItem += '<th align="left">Action</th>'
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
		tableItem += '<td><button onclick=editUserDetails('+response.userListVo[i].userId+') data-toggle="modal" data-target="#EditUserModal" title="Edit User" ><span><i class=material-icons md-18>mode_edit</i></span></button></td>';	
		tableItem += '</tr>';
		}
		tableItem += '</tbody>';
		
		tableItem += '</table>';
		$('#UsersDiv').html('');
		$('#UsersDiv').html(tableItem);
		$('#userManagement').DataTable();	
		}
	});
}

function saveUser(){
	
	var saveUserForm = $("#saveUserForm");
	$.ajax({
		url:'/saveUser',
		type:'POST',
		data: saveUserForm.serialize(),
		success:function(response){
			$('#AddUserModal').modal('hide');
			userManagement();
		}
	});	
}

function editUserDetails(userId)
{
	console.log("Edit user : "+userId);
	$.ajax({
		url:'/getUserDetails',
		type:'POST',
		data: {"userId" : userId},
		success:function(response){
			console.log(response);
			
			$('#editUserId').val(response.userId);
			$('#editName').val(response.name);
			$('#editEmail').val(response.email);
			$('#editContactNo').val(response.contactno);
			$('#editAddress').val(response.address);
			$('#editDateOfBirth').val(response.dateofbirth);
			if(response.lock == "FALSE"){
				$('#editLockFalse').prop( "checked", true );
			}else{
				$('#editLockTrue').prop( "checked", true );
			}
			if(response.status == "Deactive"){
				$('#editStatusDeactive').prop( "checked", true );
			}else{
				$('#editStatusActive').prop( "checked", true );
			}
			$('#editSelectRole').val(response.roleId);
		}
	});	
}

function updateUser(){
	
	var editUserForm = $("#editUserForm");
	$.ajax({
		url:'/updateUser',
		type:'POST',
		data: editUserForm.serialize(),
		success:function(response){
			$('#EditUserModal').modal('hide');
			userManagement();
		}
	});	
}


</script>

<div class="hrader-content">User <button id="addUser" data-toggle="modal" data-target="#AddUserModal" title="Add User">
			<i class="material-icons">add_circle_outline</i></button></div>
			<div id="UsersDiv" class="tableDiv" style="padding-top: 15px;"></div>
			
			
			<!--Add User Modal -->	
	<div class="modal fade" id="AddUserModal" role="dialog">
	    <div class="modal-dialog">
	      <div class="modal-content">
	        <div class="modal-header">
	          <button id="closeAddUserModal" type="button" class="close" data-dismiss="modal">&times;</button>
	          <h4 class="modal-title">Add User</h4>
	        </div>
	        <div class="modal-body" style="padding: 0;">
				<div style="">
				<form role="form" method="POST" id="saveUserForm" name="saveUserForm" action="/saveUser" onsubmit="return validateSaveUser()">
					<table class="table2">
						<tr>
							<td style="width: 15%;"><label class="formLabel">Name :</label></td>
							<td><input id="name" type="text" placeholder="Name" name="name"></td>
						<td style="width: 15%;"><label class="formLabel">Email :</label></td>
						<td><input id="email" type="text" placeholder="Email" name="email"></td>
						</tr>
						<tr>
							<td></td>
							<td><span id="name_err" style="color:red;font-size:10px"></span></td>
							<td></td>
							<td><span id="email_err" style="color:red;font-size:10px"></span></td>
						</tr>
						<tr>
							<td style="width: 15%;"><label class="formLabel">Contact no. :</label></td>
							<td><input id="contactNo" type="text" placeholder="Contact No" name="contactNo"></td>
							<td style="width: 15%;"><label class="formLabel">Address :</label></td>
							<td><input id="address" type="text" placeholder="Address" name="address"></td>
						</tr>
						<tr>
							<td></td>
							<td><span id="contactNo_err" style="color:red;font-size:10px"></span></td>
							<td></td>
							<td><span id="address_err" style="color:red;font-size:10px"></span></td>
						</tr>
						<tr>
							<td style="width: 15%;"><label class="formLabel">Date Of Birth :</label></td>
							<td><input id="dateOfBirth" type="text" placeholder="Date Of Birth" name="dateOfBirth"></td>
							<td><label class="formLabel">Role :</label></td>
							<td><select id="selectRole" class="selectDropdown" name="selectRole">
				         	 		<option>-- Select --</option>
			   					 	<option value="3">USER</option>
	   					 			<!-- <option value="3">HR</option> -->
			  					</select>
			  				</td>
			  			</tr>
			  			<tr>
							<td></td>
							<td><span id="dateOfBirth_err" style="color:red;font-size:10px"></span></td>
							<td></td>
							<td><span id="selectRole_err" style="color:red;font-size:10px"></span></td>
						</tr>
						<tr>		
							<td><label class="formLabel">UserName :</label></td>
							<td><input id="userName" type="text" placeholder="Username"name="userName"></td>
							<td></td>
							<td></td>
						</tr>
						<tr>
							<td></td>
							<td><span id="username_err" style="color:red;font-size:10px"></span></td>
							<td></td>
							<td></td>
						</tr>
						<tr>			
							<td style="width: 15%;"><label class="formLabel">Password :</label></td>
							<td><input id="password" type="password" placeholder="Password" name="password">	
							<span id="passwordErr" style="display: block;font-size: 10px;color: red;">
							</span><span id="messages"></span></td>		
							<td><label class="formLabel">Re-enter Password :</label></td>
							<td><input id="reEnterPass" type="password" placeholder="Re-enter Password"name="reEnterPass"></td>
								
						</tr>
						<tr>
							<td style="width: 15%;"><label class="formLabel">Status :</label></td>
							<td><label class="radio-inline modallabel"><input id="statusActive" type="radio" name="status" value="Active" checked="checked">Active</label>						
								<label class="radio-inline modallabel"><input id="statusDeactive" type="radio" name="status" value="Deactive">Deactive</label>
							</td> 
							<td style="width: 15%;"><label class="formLabel">Lock :</label></td>
							<td><label class="radio-inline modallabel"><input id="lockFalse" type="radio" name="lock" value="FALSE" checked="checked">False</label>						
								<label class="radio-inline modallabel"><input id="lockTrue" type="radio" name="lock" value="TRUE">True</label>
							</td> 
						</tr>
		
					</table>
					</form>	
				</div>
		       	<div class="modal-footer">
		         	<button id="addUserButton" type="button" class="btn btn-default" style="float: right;" title="Save user" onclick="saveUser()">Save</button>
		        </div>
			</div>
	      </div>
	    </div>
	  </div>
	  
	  
	  <!--Edit User Modal -->	
	<div class="modal fade" id="EditUserModal" role="dialog">
	    <div class="modal-dialog">
	      <div class="modal-content">
	        <div class="modal-header">
	          <button id="closeEditUserModal" type="button" class="close" data-dismiss="modal">&times;</button>
	          <h4 class="modal-title">Edit User</h4>
	        </div>
	        <div class="modal-body" style="padding: 0;">
				<div style="">
				<form role="form" method="POST" id="editUserForm" name="editUserForm" action="/updateUser" onsubmit="return validateEditUser()">
					<input id="editUserId" type="hidden" name="editUserId">
					<table class="table2">
						<tr>
							<td style="width: 15%;"><label class="formLabel">Name :</label></td>
							<td><input id="editName" type="text" placeholder="Name" name="editName"></td>
						<td style="width: 15%;"><label class="formLabel">Email :</label></td>
						<td><input id="editEmail" type="text" placeholder="Email" name="editEmail"></td>
						</tr>
						<tr>
							<td></td>
							<td><span id="editName_err" style="color:red;font-size:10px"></span></td>
							<td></td>
							<td><span id="editEmail_err" style="color:red;font-size:10px"></span></td>
						</tr>
						<tr>
							<td style="width: 15%;"><label class="formLabel">Contact no. :</label></td>
							<td><input id="editContactNo" type="text" placeholder="Contact No" name="editContactNo"></td>
							<td style="width: 15%;"><label class="formLabel">Address :</label></td>
							<td><input id="editAddress" type="text" placeholder="Address" name="editAddress"></td>
						</tr>
						<tr>
							<td></td>
							<td><span id="editContactNo_err" style="color:red;font-size:10px"></span></td>
							<td></td>
							<td><span id="editAddress_err" style="color:red;font-size:10px"></span></td>
						</tr>
						<tr>
							<td style="width: 15%;"><label class="formLabel">Date Of Birth :</label></td>
							<td><input id="editDateOfBirth" type="text" placeholder="Date Of Birth" name="editDateOfBirth"></td>
							<td><label class="formLabel">Role :</label></td>
							<td><select id="editSelectRole" class="selectDropdown" name="editSelectRole">
				         	 		<option>-- Select --</option>
			   					 	<option value="3">USER</option>
	   					 			<!-- <option value="3">HR</option> -->
			  					</select>
			  				</td>
			  			</tr>
			  			<tr>
							<td></td>
							<td><span id="editDateOfBirth_err" style="color:red;font-size:10px"></span></td>
							<td></td>
							<td><span id="editSelectRole_err" style="color:red;font-size:10px"></span></td>
						</tr>
						<!-- <tr>		
							<td><label class="formLabel">UserName :</label></td>
							<td><input id="editUserName" type="text" placeholder="Username"name="editUserName"></td>
							<td></td>
							<td></td>
						</tr>
						<tr>
							<td></td>
							<td><span id="editUserName_err" style="color:red;font-size:10px"></span></td>
							<td></td>
							<td></td>
						</tr>
						<tr>			
							<td style="width: 15%;"><label class="formLabel">Password :</label></td>
							<td><input id="editPassword" type="password" placeholder="Password" name="editPassword">	
							<span id="editPasswordErr" style="display: block;font-size: 10px;color: red;">
							</span><span id="messages"></span></td>		
							<td><label class="formLabel">Re-enter Password :</label></td>
							<td><input id="editReEnterPass" type="password" placeholder="Re-enter Password"name="editReEnterPass"></td>
								
						</tr> -->
						<tr>
							<td style="width: 15%;"><label class="formLabel">Status :</label></td>
							<td><label class="radio-inline modallabel"><input id="editStatusActive" type="radio" name="editStatus" value="Active" checked="checked">Active</label>						
								<label class="radio-inline modallabel"><input id="editStatusDeactive" type="radio" name="editStatus" value="Deactive">Deactive</label>
							</td> 
							<td style="width: 15%;"><label class="formLabel">Lock :</label></td>
							<td><label class="radio-inline modallabel"><input id="editLockFalse" type="radio" name="editLock" value="FALSE" checked="checked">False</label>						
								<label class="radio-inline modallabel"><input id="editLockTrue" type="radio" name="editLock" value="TRUE">True</label>
							</td> 
						</tr>
		
					</table>
					</form>	
				</div>
		       	<div class="modal-footer">
		         	<button id="editUserButton" type="button" class="btn btn-default" style="float: right;" title="Update User" onclick="updateUser()">Update</button>
		        </div>
			</div>
	      </div>
	    </div>
	  </div>  		
	  
	    		