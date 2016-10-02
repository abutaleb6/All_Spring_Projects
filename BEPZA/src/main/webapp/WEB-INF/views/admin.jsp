<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page session="true"%>
<html>
<head>
<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.9.2/themes/base/jquery-ui.css" />
<script src="http://code.jquery.com/jquery-1.8.3.js"></script>
<script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>

<style>
body {
	font-size: 62.5%;
}

label, input {
	display: block;
}

input.text {
	margin-bottom: 12px;
	width: 95%;
	padding: .4em;
}

fieldset {
	padding: 0;
	border: 0;
	margin-top: 25px;
}

h1 {
	font-size: 1.2em;
	margin: .6em 0;
}

div#users-contain {
	width: 350px;
	margin: 20px 0;
}

div#users-contain table {
	margin: 1em 0;
	border-collapse: collapse;
	width: 100%;
}

div#users-contain table td, div#users-contain table th {
	border: 1px solid #eee;
	padding: .6em 10px;
	text-align: left;
}

.ui-dialog .ui-state-error {
	padding: .3em;
}

.validateTips {
	border: 1px solid transparent;
	padding: 0.3em;
}
</style>
<script>
	$(function() {
		var dialog, form, dialogRole,

		// From http://www.whatwg.org/specs/web-apps/current-work/multipage/states-of-the-type-attribute.html#e-mail-state-%28type=email%29
		emailRegex = /^[a-zA-Z0-9.!#$%&'*+\/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$/, name = $("#name"), email = $("#email"), password = $("#password"), allFields = $(
				[]).add(name).add(email).add(password), tips = $(".validateTips");

		function updateTips(t) {
			tips.text(t).addClass("ui-state-highlight");
			setTimeout(function() {
				tips.removeClass("ui-state-highlight", 1500);
			}, 500);
		}

		function checkLength(o, n, min, max) {
			if (o.val().length > max || o.val().length < min) {
				o.addClass("ui-state-error");
				updateTips("Length of " + n + " must be between " + min
						+ " and " + max + ".");
				return false;
			} else {
				return true;
			}
		}

		function checkRegexp(o, regexp, n) {
			if (!(regexp.test(o.val()))) {
				o.addClass("ui-state-error");
				updateTips(n);
				return false;
			} else {
				return true;
			}
		}

		function addUser() {
			alert("Do you want to Save a User?");

			var username = $("#username").val();
			var password = $("#password").val();

			var jsonStringObj = {
				"username" : username,
				"password" : password
			};

			var jsonData = JSON.stringify(jsonStringObj);

			$.ajax({
				/* url : 'http://localhost:8282/ACL/adduser?parameters='+encodeURIComponent(jsonData), */
				url : 'http://localhost:8282/BEPZA/adduser?username='+username+'&password='+password,
				/* data : jsonData, */
				contentType : "application/json",
				success : function(data) {
					if (data == "success") {
						window.location = "http://localhost:8282/BEPZA/admin";
					}
				},
				error : function(data) {
					alert("Your User Name or Password is Error");
				},
				type : 'GET'
			});

			dialog.dialog("close");
		}

		function addRole() {
			alert("Do you want to Save a Role?");

			var username = $("#user").val();
			var role = $("#role").val();

			var jsonStringObj = {
				"username" : username,
				"password" : password
			};

			var jsonData = JSON.stringify(jsonStringObj);

			$.ajax({
				url : 'http://localhost:8282/BEPZA/addrole',
				data : jsonData,
				contentType : "application/json",
				success : function(data) {
					if (data == "success") {
						window.location = "http://localhost:8282/BEPZA/admin";
					}
				},
				error : function(data) {
					alert("Your User Name or Password is Error");
				},
				type : 'GET'
			});

			dialog.dialog("close");
		}

		dialog = $("#dialog-form-user").dialog({
			autoOpen : false,
			height : 230,
			width : 360,
			modal : true,
			buttons : {
				"Create a User" : addUser,
				Cancel : function() {
					dialog.dialog("close");
				}
			},
			close : function() {
				form[0].reset();
				allFields.removeClass("ui-state-error");
			}
		});

		dialogRole = $("#dialog-form-role").dialog({
			autoOpen : false,
			height : 230,
			width : 360,
			modal : true,
			buttons : {
				"Create a User" : addRole,
				Cancel : function() {
					dialogRole.dialog("close");
				}
			},
			close : function() {
				form[0].reset();
				allFields.removeClass("ui-state-error");
			}
		});

		form = dialog.find("form").on("submit", function(event) {
			event.preventDefault();
			addUser();
		});

		form = dialogRole.find("form").on("submit", function(event) {
			event.preventDefault();
			addRole();
		});

		$("#create-role").button().on("click", function() {
			dialogRole.dialog("open");
			/* var bankId = $('[name="accbank1"]').val();
			$("#br_accbnkid").val(bankId); */
		});

		$("#create-user").button().on("click", function() {
			dialog.dialog("open");
			/* var bankId = $('[name="accbank1"]').val();
			$("#br_accbnkid").val(bankId); */
		});

	});
</script>
</head>
<body>
	<h1>Title : ${title}</h1>
	<h1>Message : ${message}</h1>

	<c:url value="/j_spring_security_logout" var="logoutUrl" />
	<form action="${logoutUrl}" method="post" id="logoutForm">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</form>
	<script>
		function formSubmit() {
			document.getElementById("logoutForm").submit();
		}
	</script>

	<c:if test="${pageContext.request.userPrincipal.name != null}">
		<h2>
			Welcome : ${pageContext.request.userPrincipal.name} | <a
				href="javascript:formSubmit()"> Logout</a>
		</h2>
	</c:if>

	<hr />
	<br />
	<div>
		<table>
			<tr>
				<td>
					
						<input type="button" value="Add User" id="create-user" />
					
				</td>
				<td>
					
						<input type="button" value="Add Role" id="create-role" />
					
				</td>
			</tr>
		</table>
	</div>
	<!-- New User Form -->
	<div id="dialog-form-user" title="Create new User">
		<p class="validateTips">All form fields are required.</p>

		<form:form method="GET" action="adduser" commandName="add_user">
			<fieldset>
				<table>
					<tr>
						<td><input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" /></td>
					</tr>
					<tr>
						<td>User Name *</td>
						<td>:</td>
						<td><input type="text" name="username" id="username" /></td>
					</tr>
					<tr>
						<td>Password *</td>
						<td>:</td>
						<td><input type="text" name="password" id="password" /></td>
					</tr>
				</table>
			</fieldset>
		</form:form>
	</div>
	<!-- New Role Form -->
	<div id="dialog-form-role" title="Create new Role">
		<p class="validateTips">All form fields are required.</p>

		<form:form method="GET" action="addrole" commandName="add_role">
			<fieldset>
				<table>
					<tr>
						<td>Role Name *</td>
						<td>:</td>
						<td><input type="text" name="role" id="role" /></td>
					</tr>
					<tr>
						<td>User Name *</td>
						<td>:</td>
						<td><input type="text" name="username" id="user" /></td>
					</tr>
				</table>
			</fieldset>
		</form:form>
	</div>
</body>
</html>