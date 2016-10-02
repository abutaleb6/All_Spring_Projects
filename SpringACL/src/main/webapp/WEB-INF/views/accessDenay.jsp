<%--
    Document   : Source
    Created on : Nob 15, 2015, 10:35:33 AM
    Author     : Ahasanul Ashid, IBCS, And Abu Taleb, IBCS
--%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page contentType="text/html; charset=iso-8859-1" language="java"
	import="java.util.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page session="true"%>
<%
	String getURL = request.getRequestURL().toString();
%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<title>Bootstrap 101 Template</title>

<!-- Bootstrap -->
<link href="resources/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

	<sec:authentication property="principal" var="anonymousUser" />


	<c:if test="${anonymousUser eq 'anonymousUser' }">

		<%
			response.sendRedirect("auth/login.do");
		%>

	</c:if>
	<c:if test="${anonymousUser ne 'anonymousUser' }">
	
	<span id="text-invitation">Welcome <b> 
	<sec:authentication	property="principal.username" />&nbsp;&nbsp;
	</b></span>
	</c:if>
	 |&nbsp;&nbsp;
	<a href="<c:url value="/logout.do" />">Logout</a>
	<hr />
	<h1>Access Denayed</h1>
</body>
</html>