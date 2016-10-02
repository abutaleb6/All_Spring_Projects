<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Account Bank</title>
<!-- <script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script> -->

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
		var dialog, form,

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

		/* function addUser() {
			var valid = true;
			allFields.removeClass("ui-state-error");

			valid = valid && checkLength(name, "username", 3, 16);
			valid = valid && checkLength(email, "email", 6, 80);
			valid = valid && checkLength(password, "password", 5, 16);

			valid = valid
					&& checkRegexp(
							name,
							/^[a-z]([0-9a-z_\s])+$/i,
							"Username may consist of a-z, 0-9, underscores, spaces and must begin with a letter.");
			valid = valid
					&& checkRegexp(email, emailRegex, "eg. ui@jquery.com");
			valid = valid
					&& checkRegexp(password, /^([0-9a-zA-Z])+$/,
							"Password field only allow : a-z 0-9");

			if (valid) {
				$("#users tbody").append(
						"<tr>" + "<td>" + name.val() + "</td>" + "<td>"
								+ email.val() + "</td>" + "<td>"
								+ password.val() + "</td>" + "</tr>");
				dialog.dialog("close");
			}
			return valid;
		} */

		function addBranch() {
			alert("Do you want to Save a Branch?");			

			var accbnkid = $("#br_accbnkid").val();
			var code = $("#br_code").val();
			var title = $("#br_title").val();
			var email = $("#email").val();
			var contactNo = $("#contactNo").val();
			var contactPrsn = $("#contactPrsn").val();

			var jsonStringObj = {
				"code" : code,
				"title" : title,
				"email" : email,
				"contactNo" : contactNo,
				"contactPrsn" : contactPrsn,
				"accbnkid" : accbnkid
			};

			var jsonData = JSON.stringify(jsonStringObj);

			$.ajax({
				url : 'http://localhost:8282/IBCS-Bank/addAccBnkBrnch.php',
				data : jsonData,
				contentType : "application/json",
				success : function(data) {
					if (data == "success") {
						window.location = "http://localhost:8282/IBCS-Bank/";
					}
				},
				error : function(data) {
					alert("Your User Name or Password is Error");
				},
				type : 'POST'
			});

			dialog.dialog("close");
		}

		dialog = $("#dialog-form").dialog({
			autoOpen : false,
			height : 350,
			width : 360,
			modal : true,
			buttons : {
				"Create a Branch" : addBranch,
				Cancel : function() {
					dialog.dialog("close");
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

/* 		$("#create-Branch").button().on("click", function() {
			dialog.dialog("open");
			var bankId = $('[name="accbank1"]').val();
			$("#br_accbnkid").val(bankId);
			//alert(bankId);
		}); */
		
		$(".abc").button().on("click", function() {
			dialog.dialog("open");
			var bankId = $('[name="accbank1"]').val();
			$("#br_accbnkid").val(bankId);
			//alert(bankId);
		});
		
	});
</script>
</head>
<body>
	<h2>Add Bank</h2>
	<div>
		<form:form method="POST" action="addAccBnk.php" commandName="acc_bank">
			<table>
				<tr>
					<td><form:label path="code">Bank Code</form:label></td>
					<td>:</td>
					<td><form:input path="code" /></td>
				</tr>
				<tr>
					<td><form:label path="title">Title</form:label></td>
					<td>:</td>
					<td><form:input path="title" /></td>
				</tr>
				<tr>
					<td><form:label path="titleBng">Bank Title</form:label></td>
					<td>:</td>
					<td><form:input path="titleBng" /></td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="Submit" /></td>
				</tr>
			</table>
		</form:form>
	</div>	

	<div id="dialog-form" title="Create new Branch">
		<p class="validateTips">All form fields are required.</p>

		<form:form method="POST" action="addAccBnkBrnch.php"
			commandName="acc_bank_brnch">
			<fieldset>
				<table>
					<tr>
						<td><input type="hidden" name="accbnkid" value=""
							id="br_accbnkid" /></td>
					</tr>
					<tr>
						<td>Code</td>
						<td>:</td>
						<td><input type="text" name="code" id="br_code" /></td>
					</tr>
					<tr>
						<td>Title</td>
						<td>:</td>
						<td><input type="text" name="title" id="br_title" /></td>
					</tr>
					<tr>
						<td>email</td>
						<td>:</td>
						<td><input type="email" name="email" id="email" /></td>
					</tr>
					<tr>
						<td>Contact No</td>
						<td>:</td>
						<td><input type="text" name="contactNo" id="contactNo" /></td>
					</tr>
					<tr>
						<td>Contact Person</td>
						<td>:</td>
						<td><input type="text" name="contactPrsn" id="contactPrsn" /></td>
					</tr>
				</table>
				<!-- <label for="name">Name</label> <input type="text" name="name"
					id="name" value="Jane Smith"
					class="text ui-widget-content ui-corner-all"> <label
					for="email">Email</label> <input type="text" name="email"
					id="email" value="jane@smith.com"
					class="text ui-widget-content ui-corner-all"> <label
					for="password">Password</label> <input type="password"
					name="password" id="password" value="xxxxxxx"
					class="text ui-widget-content ui-corner-all">

				Allow form submission with keyboard without duplicating the dialog button
				<input type="submit" tabindex="-1"
					style="position: absolute; top: -1000px"> -->
			</fieldset>
		</form:form>

		<%-- <form:form method="POST" action="addAccBnkBrnch.php" commandName="acc_bank">>
			<fieldset>
			<table>
				<tr>					
					<td><form:input path="accbnkid" readonly="true" hidden="true" value="${accBank.id}"/></td>
				</tr>
				<tr>
					<td><form:label path="code">Branch Code</form:label></td>
					<td>:</td>
					<td><form:input path="code" /></td>
				</tr>
				<tr>
					<td><form:label path="title">Branch Title</form:label></td>
					<td>:</td>
					<td><form:input path="title" /></td>
				</tr>				
				<tr>
					<td><form:label path="email">Email</form:label></td>
					<td>:</td>
					<td><form:input path="email" /></td>
				</tr>
				<tr>
					<td><form:label path="contactNo">Contact No</form:label></td>
					<td>:</td>
					<td><form:input path="contactNo" /></td>
				</tr>
				<tr>
					<td><form:label path="contactPrsn">Contact Person</form:label></td>
					<td>:</td>
					<td><form:input path="contactPrsn" /></td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="Submit" /></td>
				</tr>
			</table>
			</fieldset>
		</form:form> --%>
	</div>
	
	<div>
		<c:if test="${!empty listAccBanks}">
			<h2>List Banks</h2>
			<table align="left" border="1" id="accountBankTable">
				<tr>
					<th>BANK ID</th>
					<th>BANK CODE</th>
					<th>TITLE</th>
					<th>BANK TITLE</th>
					<th>Actions on Row</th>
				</tr>

				<c:forEach items="${listAccBanks}" var="accBank">
					<tr>
						<td><input type="hidden" name="accbank1" value="${accBank.id}" hidden="true"/> <c:out value="${accBank.id}"/></td>
						<td><c:out value="${accBank.code}" /></td>
						<td><c:out value="${accBank.title}" /></td>
						<td><c:out value="${accBank.titleBng}" /></td>
						<td align="center"> <button id="create-Branch" class="abc">Create
								new Branch</button> | <a href="delete.php?id=${accBank.id}">Delete</a></td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
	</div>

</body>
</html>
