<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<h1 Style="color:red">${error}</h1>
<body>
<form action="login.html" method="POST">
User Name : <input type="text" name="username"/> <br/>
Password : <input type="password" name="password"/> <br/>
<input type="submit" value="Login"/>
</form>
</body>
</html>
