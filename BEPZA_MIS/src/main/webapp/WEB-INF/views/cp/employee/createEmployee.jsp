<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style>
.error {
	color: #ff0000;
}

.errorblock {
	color: #000;
	background-color: #ffEEEE;
	border: 3px solid #ff0000;
	padding: 8px;
	margin: 16px;
}
</style>
</head>
<body>This is company create page.
</body>

<div class="error"></div>

<form:form method="POST" action="save" commandName="employee">
<form:errors path="*" cssStyle="color: #ff0000;" />
<div> 
	<table>
		<tr>
			<td><form:label path="empId">Employee ID:</form:label></td>
			<td><form:input path="empId" value="${employee.empId}"
					readonly="true" /></td>

		</tr>
		<tr>
			<td><form:label path="empName">Employee Name:</form:label></td>
			<td><form:input path="empName" value="${employee.empName}" /></td>
			<td><form:errors path="empName" cssStyle="color: #ff0000;"/></td>

			
		</tr>
		<tr>
			<td><form:label path="empAge">Employee Age:</form:label></td>
			<td><form:input path="empAge" value="${employee.empAge}" /></td>
			<td><form:errors path="empAge" cssStyle="color: #ff0000;"/></td>
		</tr>
		<tr>
			<td><form:label path="salary">Employee Salary:</form:label></td>
			<td><form:input path="salary" value="${employee.salary}" /></td>
		</tr>

		<tr>
			<td><form:label path="empAddress"><spring:message code="label.address" text="your default text here" /></form:label></td>
			<td><form:input path="empAddress" value="${employee.empAddress}" /></td>
			<td><form:errors path="empAddress" cssStyle="color: #ff0000;"/></td>
		</tr>
		<tr>
			<td colspan="2"><input type="submit" value="Submit" /></td>
		</tr>
	</table>
</div> 
</form:form>

</html>