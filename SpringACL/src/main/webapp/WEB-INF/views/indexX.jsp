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

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
	<span id="text-invitation">Welcome <b><sec:authentication
				property="principal.username" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	</b></span>
	<h1>Hello, world!</h1>
	<P>The time on the server is ${serverTime}.</P>

	<%-- <sec:authentication property="principal.username" var="username" />
	<sec:authentication property="principal.authorities[0]" var="prin" />
	 <c:out value="${prin.toString()}"/>
	
	<c:if test="${prin eq 'ROLE_ADMIN'}">
		<h1>
			<a href="addNew.do">Add New form</a>
		</h1>
	</c:if> --%>

	<%-- <c:out value="${permissionList}"></c:out> --%>

	<%-- <c:forEach items="${permissionList}" var="permisson">
		<c:if test="${permisson[0]=='BtrcIfwDestDesc8'}">
			<c:if test="${permisson[2]==1}">
				<h1>I am working</h1>
			</c:if>

			<c:if test="${permisson[3]==1}">
				<h1>Hi</h1>
				<c:out value="${permisson[3]}"></c:out>
			</c:if>

			<c:out value="${permisson[0]}"></c:out>
			<c:out value="${permisson[1]}"></c:out>
			<c:out value="${permisson[2]}"></c:out>
			<c:out value="${permisson[3]}"></c:out>
			<c:out value="${permisson[4]}"></c:out>
			<c:out value="${permisson[5]}"></c:out>
		</c:if>
	</c:forEach> --%>

	<a href="${pageContext.request.contextPath}/addNew.do">Add New form Controller</a>

	<%-- 	<sec:authentication property="principal.authorities[0]" var="prin" />
	
	 <c:out value="${prin}"></c:out>
	
	<sec:authorize access="hasRole('${'ROLE_ADMIN'}')">
		This content will only be visible to users who have
		the "ROLE_ADMIN" authority in their list of <tt>GrantedAuthority</tt>s.
	</sec:authorize> --%>

	<%-- <sec:authorize access="hasPermission(${user},'READ')">
		<a href="${pageContext.request.contextPath}/addNew.do">Add 222</a>
	</sec:authorize> --%> 
	
	
			
			<%-- <c:out value="${user}"></c:out>   			
			<c:out value="${abc}"></c:out> --%>   	 
		

	<%-- <c:out value="${pageConext.findAttribute('com.ibcs.acl.model.User')}"></c:out> --%>
	 <sec:authorize access="hasPermission(#user, 'READ')">
		<a href="addNew.do">Add New success from Gui </a>
	</sec:authorize> 

  
<%-- <sec:accesscontrollist hasPermission="1"
		domainObject="#addNew.do">
		<a href="addNew.do">Add New 2</a>
	</sec:accesscontrollist>

	<sec:authorize ifAnyGranted="ROLE_ADMIN">
	<a href="addNew.do">Add 555</a>
	</sec:authorize> --%>

	<%-- <sec:authentication property="principal.store" var="userStore"/>
		
		
                    <c:if test="${userStore eq lovStoreNone}">
                    <sec:authorize access="hasRole('ROLE_LEVEL_7')">
                    <div class="ym-fbox" id="store">
                        <form:label path="store"><spring:message code="label.store"/></form:label>
                        <form:select path="store">
                            <form:options items="${stores}" />
                        </form:select>  
                    </div>                                  
                    </sec:authorize>                                                                    
                    </c:if> --%>

	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="resources/js/jquery.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="resources/js/bootstrap.min.js"></script>
</body>
</html>