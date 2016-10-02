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
	<h2>Add Employee Data</h2>
	<form:form method="POST" action="save.do">
		<table>
			<tr>
				<td><form:label path="id">Employee ID:</form:label></td>
				<td><form:input path="id" value="${employee.id}"
						readonly="true" /></td>
			</tr>
			<tr>
				<td><form:label path="name">Employee Name:</form:label></td>
				<td><form:input path="name" value="${employee.name}" /></td>
			</tr>
			<tr>
				<td><form:label path="age">Employee Age:</form:label></td>
				<td><form:input path="age" value="${employee.age}" /></td>
			</tr>
			<tr>
				<td><form:label path="salary">Employee Salary:</form:label></td>
				<td><form:input path="salary" value="${employee.salary}" /></td>
			</tr>

			<tr>
				<td><form:label path="address">Employee Address:</form:label></td>
				<td><form:input path="address" value="${employee.address}" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Submit" /></td>
			</tr>
		</table>
	</form:form>

	<c:if test="${!empty employees}">
		<h2>List Employees</h2>
		<table align="left" border="1">
			<tr>
				<th>Employee ID</th>
				<th>Employee Name</th>
				<th>Employee Age</th>
				<th>Employee Salary</th>
				<th>Employee Address</th>
				<th>Actions on Row</th>
			</tr>

			<c:forEach items="${employees}" var="employee">
				<tr>
					<td><c:out value="${employee.id}" /></td>
					<td><c:out value="${employee.name}" /></td>
					<td><c:out value="${employee.age}" /></td>
					<td><c:out value="${employee.salary}" /></td>
					<td><c:out value="${employee.address}" /></td>
					<td align="center"><sec:authorize
							access="hasPermission(#employee, 'EDIT')">
							<a href="edit.do?id=${employee.id}">Edit</a>
						</sec:authorize> <sec:authorize access="hasPermission(#employee, 'DELETE')">
							| <a
								href="${pageContext.request.contextPath}/delete.do?id=${employee.id}">Delete</a>
						</sec:authorize></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</body>
</html>