<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<html lang="pt-BR" id="ng-app" ng-app="">
<head>

 <script src="<c:url value='../../resources/jquery/jquery-2.1.3.min.js'/>"></script>
  <script src="<c:url value='../../resources/jquery/jquery-ui.min.js'/>"></script>
<link href="<c:url value='../../resources/css/home.css'/>" rel="stylesheet">
<link href="<c:url value='../../resources/css/jquery-ui.css'/>" rel="stylesheet">

<!-- ----------------------------------------------------------
-------------------- Datatables Start--------------------------
--------------------------------------------------------------->
  <script src="<c:url value='../../resources/jquery/datatables/js/jquery.dataTables.min.js'/>"></script>
 <script src="<c:url value='../../resources/jquery/datatables/js/dataTables.bootstrap.min.js'/>"></script>
 <link href="<c:url value='../../resources/jquery/datatables/css/datatables.bootstrap.css'/>" rel="stylesheet">
 <link href="<c:url value='../../resources/jquery/datatables/css/rowReorder.dataTables.min.css'/>" rel="stylesheet">

 <!-- -- ------------- bootstrap start ------------------------ -->
 <link href="<c:url value='/resources/bootstrap/bootstrap.css'/>" rel="stylesheet">
  <link href="<c:url value='/resources/bootstrap/css/bootstrap-combined.min.css'/>" rel="stylesheet">
  <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  

<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">


 </head>
    <body >
    	<div id="container">    	
   			<tiles:insertAttribute name="header" />
      		<tiles:insertAttribute name="body" />
      		<tiles:insertAttribute name="footer" />	       	
        </div><!-- container close here -->
    </body><!-- body close here -->
</html><!-- /html -->


