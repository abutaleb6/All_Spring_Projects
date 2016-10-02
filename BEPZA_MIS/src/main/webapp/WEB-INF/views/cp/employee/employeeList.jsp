<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employee List</title>
</head>
<body>

	<c:if test="${!empty employeeInstanceList.employees}">

		<table align="left" border="1">
			<tr>
				<th>Employee ID</th>
				<th>Employee Name</th>
				<th>Employee Age</th>
				<th>Employee Salary</th>
				<th>Employee Address</th>
			</tr>

			<c:forEach items="${employeeInstanceList.employees}" var="employee">
				<tr>
					<td><a href="show/${employee.empId}"><c:out
								value="${employee.empId}" /></a></td>
					<td><c:out value="${employee.empName}" /></td>
					<td><c:out value="${employee.empAge}" /></td>
					<td><c:out value="${employee.salary}" /></td>
					<td><c:out value="${employee.empAddress}" /></td>
					<td align="center">
					 <a href="show/${employee.empId}">Show</a> |
					 <a href="edit/${employee.empId}">Edit</a> | 
					<a href="delete?empId=${employee.empId}">Delete</a></td>
					<%-- <td align="center">
					      <a href="show?empId=${employee.empId}">Show</a> |
						  <a href="edit?empId=${employee.empId}">Edit</a> | 
						  <a href="delete?empId=${employee.empId}">Delete</a>
						   <a href="${pageContext.request.contextPath}/cp/employee/edit/${employee.empId}">Edit</a><br>
					</td> --%>
				</tr>
			</c:forEach>

		</table>
		
	</c:if>
	<br>
	<br>
	<br>
	<br>
	<a href="create">Add</a> 
</body>



</html>