<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Your Id Is : ${message}</h1>
	<a href="back.html"> Back </a>
<br/>
<hr/>
<br/>
<h1>Customers Information </h1>

	<table border="1">
		<tr>
			<th>Sirial No</th>
			<th>Customer Name</th>
			<th>Address</th>
			<th>Email Address</th>
			<th>Mobile</th>
		</tr>
		<c:if test="${empty allcustomers}">
			<tr>
				<td><c:out value="Sorry!! No Data Has Found!"></c:out></td>
			</tr>
		</c:if>


		<c:if test="${! empty allcustomers}">
			<c:forEach var="customer" items="${allcustomers}">
				<tr>
					<input type="hidden" name="customername" value="" ${customer.customername}"/>
					<td><c:out value="${customer.customerid}"></c:out></td>
					<td><c:out value="${customer.customername}"></c:out></td>
					<td><c:out value="${customer.address}"></c:out></td>
					<td><c:out value="${customer.email}"></c:out></td>
					<td><c:out value="${customer.mobile}"></c:out></td>
					<td><a
						href="updateCustomer.html?customerid=${customer.customerid}&customername=${customer.customername}&address=${customer.address}&email=${customer.email}&mobile=${customer.mobile}">
							update</a></td>
				</tr>
			</c:forEach>
		</c:if>


	</table>
</body>
</html>