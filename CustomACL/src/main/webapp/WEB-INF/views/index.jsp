<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome page</title>
<link href="<c:url value='/resources/css/bootstrap.css' />"
	rel="stylesheet"></link>
<link href="<c:url value='/resources/css/app.css' />" rel="stylesheet"></link>
</head>
<body>
	<div class="success">
		${user} | <a href="<c:url value="/logout" />">Logout</a>

		<hr />
		<h1>
		<c:forEach items="${reqMap}" var="requestMap">
		<c:out value="${requestMap.url}"></c:out>
		
		</c:forEach>
		
		<%-- <h1>${reqMap[0].url} --%></h1>
<h1>I am Index Page</h1>
</body>
</html>