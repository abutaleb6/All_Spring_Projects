<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	${name}  
</h1>
<h3 color="#00ff00">${success}</h3>
<form action="login.html" method="POST">
User Name : <input type="text" name="name"/> <br/>
Password : <input type="password" name="password"/> <br/>
<input type="submit" value="Login"/>
</form>
</body>
</html>
