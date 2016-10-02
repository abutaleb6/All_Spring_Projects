<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Home</title>
<style>
table, td, th {
	border: 1px solid green;
}

th {
	background-color: green;
	color: white;
}
</style>
</head>
<body>
	<h2>Login As : ${username}</h2>
	<form method="post" action="insertCustomer.html">
		<table>
			<tr>
				<th colspan="3">Customer Single Form</th>
			</tr>
			<tr>
				<td>Customer Name</td>
				<td>:</td>
				<td><input type="text" name="customername" /></td>
			</tr>
			<tr>
				<td>Customer Address</td>
				<td>:</td>
				<td><input type="text" name="address" /></td>
			</tr>
			<tr>
				<td>Customer Email</td>
				<td>:</td>
				<td><input type="email" name="email" /></td>
			</tr>

			<tr>
				<td>Mobile</td>
				<td>:</td>
				<td><input type="text" name="mobile" /></td>
			</tr>
			<tr>
				<td colspan="3"><input type="submit" value="Save Customer" /></td>
			</tr>
		</table>

	</form>

	<br />
	<br />
	<h1>Employee Details Form</h1>
	<form method="post" action="insertEmployee.html">

		<table>
			<tr>
				<td>Designation</td>
				<td>:</td>
				<td><select name="designation">
						<option value="mr">Mr.</option>
						<option value="mrs">Mrs.</option>
				</select></td>
			</tr>
			<tr>
				<td>Employee Name</td>
				<td>:</td>
				<td><input type="text" name="empName" /></td>
			</tr>
			<tr>
				<td>Employee Address</td>
				<td>:</td>
				<td><input type="text" name="empAddress" /></td>
			</tr>
			<tr>
				<td>Gender</td>
				<td>:</td>
				<td><input type="radio" name="gender" value="male"
					checked="checked">Male <input type="radio" name="gender"
					value="female">Female</td>
			</tr>
			<tr>
				<td>Date of Birth</td>
				<td>:</td>
				<td><input type="date" name="dob" /></td>
			</tr>
			<tr>
				<td>Language</td>
				<td>:</td>
				<td><input type="checkbox" name="languages" value="java">Java
					<input type="checkbox" name="languages" value="javaScript">JavaScript
					<input type="checkbox" name="languages" value="html">HTML</td>
			</tr>
		</table>
		<br /> <br />

		<table name="eduDtls">

			<tr>
				<th colspan="3">Employee Education Details :</th>
			</tr>
			<tr>
				<th>Education Name</th>
				<th>Passing Year</th>
				<th>Sort Order</th>
			</tr>

			<tr>
				<td><input type="text" name="exmName" /></td>
				<td><input type="text" name="exmYear" /></td>
				<td><input type="text" name="sortOrder" /></td>
			</tr>
			<tr>
				<td><input type="text" name="exmName" /></td>
				<td><input type="text" name="exmYear" /></td>
				<td><input type="text" name="sortOrder" /></td>
			</tr>
			<tr>
				<td><input type="text" name="exmName" /></td>
				<td><input type="text" name="exmYear" /></td>
				<td><input type="text" name="sortOrder" /></td>
			</tr>
		</table>

		<input type="submit" value="Save Employee" />
	</form>

	<br />
	<br />

	<a href="viewAllData.html"> show All Data</a>
</body>
</html>
