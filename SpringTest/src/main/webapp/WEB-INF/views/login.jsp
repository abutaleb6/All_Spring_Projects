<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script>
	$(document).ready(function() {
		$("#submitLogin").click(function() {
			/* var table = $("#abc");
			table.find('tr').each(function (i, el) {
		        var $tds = $(this).find('td'),
		            name = $tds.eq(0).text(),
		            pass = $tds.eq(1).text();
		        // do something with productId, product, Quantity
		    }); */
		    
		    alert("HI");
			
		});
	});
</script>
<title>Home</title>
</head>
<h1 Style="color: red">${error}</h1>
<body>
	<form action="login.html" method="POST">

		<table id="abc">
			<tr>
				<td>User Name</td>
				<td>:</td>
				<td><input type="text" name="username" /></td>
			</tr>
			<tr>
				<td>Password</td>
				<td>:</td>
				<td><input type="password" name="password" /></td>
			</tr>
			<tr>
				<td></td>
				<td></td>
				<td id="submitLogin"><input type="submit" value="Login" /></td>
			</tr>
		</table>
		<!-- User Name : <input type="text" name="username" /> <br /> Password :
		<input type="password" name="password" /> <br /> <input
			type="submit" value="Login" /> -->
	</form>
</body>
</html>
