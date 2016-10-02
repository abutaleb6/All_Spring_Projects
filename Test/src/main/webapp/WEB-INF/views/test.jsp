<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>main demo</title>
<link rel="stylesheet" href="resources/css/screen.css">
<script src="resources/lib/jquery.js"></script>
<script src="resources/dist/jquery.validate.js"></script>
<script>
	$.validator
			.setDefaults({
				submitHandler : function() {
					var uname = $("#username").val();
					var password = $("#password").val();
					var jsonStringObj = {
						"userName" : uname,
						"password" : password
					};

					var jsonData = JSON.stringify(jsonStringObj);
					//alert("submitted!");
					$
							.ajax({
								url : 'http://localhost:8080/Test/login.html',
								data : jsonData,
								async : false,
								contentType : "application/json",
								success : function(data) {
									if (data == "success") {
										window.location = "http://localhost:8080/Test/loginSuccess.html";
									}
								},
								error : function(data) {
									alert("Your User Name or Password is Error");
								},
								type : 'POST'
							});
				}
			});

	$()
			.ready(
					function() {
						$("#signupForm")
								.validate(
										{
											rules : {
												username : {
													required : true,
													minlength : 4
												},
												password : {
													required : true,
													minlength : 4
												},
												agree : "required"
											},
											messages : {
												username : {
													required : "Please enter a username",
													minlength : "Your username must consist of at least 4 characters"
												},
												password : {
													required : "Please provide a password",
													minlength : "Your password must be at least 4 characters long"
												}
											}
										});
					});
</script>
<style>
#signupForm {
	width: 300px;
}

#signupForm label.error {
	margin-left: 10px;
	width: auto;
	display: inline;
}

#newsletter_topics label.error {
	display: none;
	margin-left: 103px;
}
</style>
</head>
<h1 Style="color: red">${error}</h1>
<body>
	<!-- <form action="login.html" method="POST"> -->
	<form class="cmxform" id="signupForm" method="POST" action="login.html">
		<fieldset>
			<table>
				<tr>
					<td>User Name</td>
					<td>:</td>
					<td><input id="username" type="text" name="username" required /></td>
				</tr>
				<tr>
					<td>Password</td>
					<td>:</td>
					<td><input id="password" type="password" name="password"
						required /></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td id="submitLogin"><input class="submit" type="submit"
						value="Login" /></td>
				</tr>
			</table>
			<fieldset>
	</form>
</body>
</html>
