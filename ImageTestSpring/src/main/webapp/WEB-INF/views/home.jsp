<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<form action="viewImage", method="get">
<input type="text" name="imageName"/>
<input type="submit" value="View Image"/>
</form>


<form action="uploadImage", method="get">
<input type="submit" value="Upload New Image"/>
</form>

<form action="uploadMulitImage", method="get">
<input type="submit" value="Upload New Multi Image"/>
</form>


<img src="data:image/jpeg;base64,${image}" alt="...">`
</body>
</html>
