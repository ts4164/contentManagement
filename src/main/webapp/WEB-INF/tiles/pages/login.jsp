<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<link href="../../resources/css/login.css" rel="stylesheet">

<form action="/logout" method="post" id="logout">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
</form>
<c:choose>
  <c:when test="${pageContext.request.remoteUser!=null}">
<script>

	$('#logout').submit();
	
</script>

</c:when>
</c:choose>

<script type="text/javascript">
$(document).ready(function(){
	$(".navigation").hide();
	});		 		

function viewPass(){
	 if($("#vis").css('display') == 'none'){
		 $("#vis").show();
		 $("#invis").hide();
		 $("#Password").prop('type','text');
	 }else{
		 $("#vis").hide();
		 $("#invis").show();
		 $("#Password").prop('type','password');
	 }
}
</script>	

<div id="container" class="content">	
	<div class="wrapper">
		<div id="box">
			<div id="signin">
			<h3 style="margin:5% 0;background-color:#fff;">SIGN IN</h3>
				<form action="/login" autocomplete="off" method="post">
				    <input id="Username" type="text" placeholder="Username" name="username" required>
				    <span class="prevue-wrapper" style="position: relative;">
				    	<input id="Password" type="password" placeholder="Password" name="password" required>
				    	<a id="viewPass" onclick="viewPass()" style="text-decoration: none;position: absolute;right: -325px;color: rgb(153, 153, 153);margin: auto;top: 0px;">
				    		<i id="vis" class="material-icons" style="font-size: 16px;color: #3675bc;display:none">visibility</i>
				    		<i id="invis" class="material-icons" style="font-size: 16px;">visibility_off</i>
				    	</a>
				    </span>
				    
				   <!--  <input id="Password" type="password" placeholder="Password" name="password" required> -->
				    <button type="submit">Login</button>
				    <div style="margin-top: 30px;float:left;width:100%" class="form-group">
								<!-- Button -->
								<div  style="width:100%">
		<!-- 							<input id="btn-login" class="btnlogin " type="submit" value="Login" />
		 -->					<c:if test="${param.error ne null}">
		 							<script type="text/javascript">
		 							$("#box").addClass("shakeAnim");
		 							</script>	
									<div style="color:#ffc5c5">Invalid username or password.</div>
								</c:if>
								<c:if test="${param.logout ne null}">
									<div style="color:red" >You have been logged out.</div>
								</c:if>
								<c:if test="${param.expired ne null}">
									<div style="color:red" >Your Account is Logged in on other browser or system.</div>
								</c:if>
								<c:if test="${param.block ne null}">
									<div style="color:red" >Your Previous Session is Still Active Please Logout.</div>
								</c:if>
								<c:if test="${param.disabled ne null}">
									<div style="color:red" >Sorry! Your Account Is Disabled.</div>
								</c:if>
								<c:if test="${param.locked ne null}">
									<div style="color:red" >Sorry! Your Account Is Locked.</div>
								</c:if>
								</div>
							</div>
				    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				    
				</form>		
			</div>
			
		</div>
	</div>
</div>