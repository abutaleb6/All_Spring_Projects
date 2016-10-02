<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Home</title>
</head>
<body>
<%
String id=request.getParameter("customerid");
String name=request.getParameter("customername");
String address=request.getParameter("address");
String email=request.getParameter("email");
String mobile=request.getParameter("mobile");
%>


	<h1>Update Customer Information</h1>
	<form method="POST" action="updateCustomer.html">
		Customer id :<input type="text" name="customerid" value="<%=id %>" /><br />
		Customer Name :<input type="text" name="customername" value="<%=name %>" /><br />
		Customer Address :<input type="text" name="address"  value="<%=address %>"/><br />
		Customer Email :<input type="text" name="email"  value="<%=email %>"/><br />
		Customer Mobile : <input type="text" name="mobile"  value="<%=mobile %>"/><br /> 
			<input type="submit" value="update Customer" />
	</form>

</body>
</html>
