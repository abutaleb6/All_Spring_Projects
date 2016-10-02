<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="false"%>
<html>
<head>
<title>Home</title>

<style>
    .error {
        color: red; font-weight: bold;
    }
</style>
</head>
<body>
	<h1>Form for Validation!</h1>

	<form:form action="signup" method="POST" commandName="userForm">
		<table>
			<tr>
				<td>Name</td>
				<td>:</td>
				<td><input type="text" name="name" /></td>
				<td><form:errors path="name" cssClass="error"/></td>
			</tr>
			
			<tr>
				<td>Password</td>
				<td>:</td>
				<td><input type="password" name="password"/></td>
				<td><form:errors path="password" cssClass="error"/></td>
			</tr>
			
			<tr>
				<td>Date of Birth</td>
				<td>:</td>
				<td><input type="date" name="dob"/></td>
				<td><form:errors path="dob" cssClass="error"/></td>
			</tr>
			
			<tr>
				<td>Age</td>
				<td>:</td>
				<td><input type="text" name="age"/></td>
				<td><form:errors path="age" cssClass="error"/></td>
			</tr>
			
			<tr>
				<td>Mobile No</td>
				<td>:</td>
				<td><input type="text" name="mobile"/></td>
				<td><form:errors path="mobile" cssClass="error"/></td>
			</tr>
			
			<tr>
				<td>Email</td>
				<td>:</td>
				<td><input type="email" name="email"/></td>
				<td><form:errors path="email" cssClass="error"/></td>
			</tr>
			
			<tr>
				<td>WebSite</td>
				<td>:</td>
				<td><input type="url" name="website"/></td>
				<td><form:errors path="website" cssClass="error"/></td>
			</tr>
			
			<tr>
				<td>Salary</td>
				<td>:</td>
				<td><input type="text" name="salary"/></td>
				<td><form:errors path="salary" cssClass="error"/></td>
			</tr>	
			
			<tr>
				<td valign="top">Comment</td>
				<td valign="top">:</td>
				<td><textarea rows="5" cols="30" name="comment"></textarea></td>
				<td><form:errors path="comment" cssClass="error"/></td>
			</tr>
			
			<tr>				
				<td colspan="3" align="center"><input type="submit" value="submit"/></td>
			</tr>
		</table>
	</form:form>

</body>
</html>
