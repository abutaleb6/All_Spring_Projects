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
	$.validator.setDefaults({
		submitHandler : function() {
			alert("submitted!");
		}
	});

	$()
			.ready(
					function() {
						// validate the comment form when it is submitted
						$("#commentForm").validate();

						// validate signup form on keyup and submit
						$("#signupForm")
								.validate(
										{
											rules : {
												firstname : "required",
												lastname : "required",
												username : {
													required : true,
													minlength : 4
												},
												password : {
													required : true,
													minlength : 4
												},
												confirm_password : {
													required : true,
													minlength : 4,
													equalTo : "#password"
												},
												email : {
													required : true,
													email : true
												},
												topic : {
													required : "#newsletter:checked",
													minlength : 2
												},
												agree : "required"
											},
											messages : {
												firstname : "Please enter your firstname",
												lastname : "Please enter your lastname",
												username : {
													required : "Please enter a username",
													minlength : "Your username must consist of at least 4 characters"
												},
												password : {
													required : "Please provide a password",
													minlength : "Your password must be at least 4 characters long"
												},
												confirm_password : {
													required : "Please provide a password",
													minlength : "Your password must be at least 4 characters long",
													equalTo : "Please enter the same password as above"
												},
												email : "Please enter a valid email address",
												agree : "Please accept our policy",
												topic : "Please select at least 2 topics"
											}
										});

						// propose username by combining first- and lastname
						$("#username").focus(function() {
							var firstname = $("#firstname").val();
							var lastname = $("#lastname").val();
							if (firstname && lastname && !this.value) {
								this.value = firstname + "." + lastname;
							}
						});

						//code to hide topic selection, disable for demo
						var newsletter = $("#newsletter");
						// newsletter topics are optional, hide at first
						var inital = newsletter.is(":checked");
						var topics = $("#newsletter_topics")[inital ? "removeClass"
								: "addClass"]("gray");
						var topicInputs = topics.find("input").attr("disabled",
								!inital);
						// show when newsletter is checked
						newsletter.click(function() {
							topics[this.checked ? "removeClass" : "addClass"]
									("gray");
							topicInputs.attr("disabled", !this.checked);
						});
					});
</script>
<style>
#commentForm {
	width: 500px;
}

#commentForm label {
	width: 250px;
}

#commentForm label.error, #commentForm input.submit {
	margin-left: 253px;
}

#signupForm {
	width: 670px;
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
	<form class="cmxform" id="signupForm" method="get" action="">
		<fieldset>
			<table id="abc">
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

	<!-- <form class="cmxform" id="commentForm" method="get" action="">
		<fieldset>
			<legend>Please provide your name, email address (won't be
				published) and a comment</legend>
			<p>
				<label for="cname">Name (required, at least 2 characters)</label> <input
					id="cname" name="name" minlength="2" type="text" required>
			</p>
			<p>
				<label for="cemail">E-Mail (required)</label> <input id="cemail"
					type="email" name="email" required>
			</p>
			<p>
				<label for="curl">URL (optional)</label> <input id="curl" type="url"
					name="url">
			</p>
			<p>
				<label for="ccomment">Your comment (required)</label>
				<textarea id="ccomment" name="comment" required></textarea>
			</p>
			<p>
				<input class="submit" type="submit" value="Submit">
			</p>
		</fieldset>
	</form> -->
</body>
</html>
