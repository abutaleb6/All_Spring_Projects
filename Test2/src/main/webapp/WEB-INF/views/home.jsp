<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Home</title>
<link rel="stylesheet" href="resources/css/test.css">
<script src="resources/lib/jquery.js"></script>
<script src="resources/dist/jquery.validate.js"></script>
<!-- <script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script> -->

<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.9.2/themes/base/jquery-ui.css" />
<script src="http://code.jquery.com/jquery-1.8.3.js"></script>
<script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>

<script>
	$(document).ready(function() {
		$("#eduDtlBtn").click(function() {
			$("#eduDtls").show();
			$(this).hide();
		});

		$("#sscBtn").click(function() {
			$("#hsc").show();
			$(this).hide();
		});

		$("#hscBtn").click(function() {
			$("#bachelor").show();
			$(this).hide();
		});

		$("#bachelorBtn").click(function() {
			$("#masters").show();
			$(this).hide();
		});

		$("#addEmpForm").submit(function(event) {
			var designation = $("#designation").val();
			var empName = $("#empName").val();
			var empAddress = $("#empAddress").val();
			var dob = $("#dob").val();
			var birthdate = $.datepicker.formatDate('yy-mm-dd', new Date(dob));

			var gender = $('input[name=gender]:checked', '#addEmpForm').val()

			var languages = new Array();
			var n = jQuery(".languages:checked").length;
			if (n > 0) {
				jQuery(".languages:checked").each(function() {
					languages.push($(this).val());
				});
			}

			var exmName = new Array();
			if ($(".exmName").val()) {
				$(".exmName").each(function() {
					exmName.push($(this).val());
				});
			}

			var exmYear = new Array();
			if ($(".exmYear").val()) {
				$(".exmYear").each(function() {
					exmYear.push($(this).val());
				});
			}
			var sortOrder = new Array();
			if ($(".sortOrder").val()) {
				$(".sortOrder").each(function() {
					sortOrder.push($(this).val());
				});
			}

			var jsonStringObj = {
				"designation" : designation,
				"empName" : empName,
				"empAddress" : empAddress,
				"dob" : birthdate,
				"gender" : gender,
				"languages" : languages,
				"exmName" : exmName,
				"exmYear" : exmYear,
				"sortOrder" : sortOrder
			};

			var jsonData = JSON.stringify(jsonStringObj);
			//alert("submitted!");
			$.ajax({
				url : 'http://localhost:8282/Test2/insertEmployee.html',
				data : jsonData,				
				async : false,
				contentType : "application/json",
				success : function(data) {
					if (data == "success") {
						//alert("hellow");
						//request.abort();
						window.location = "http://localhost:8282/Test2/insertSuccess.html";
					}
				},
				error : function(data) {
					alert("Your Data not be inserted");
				},
				//dataType : 'jsonp',
				type : 'POST'
			});
		});
	});
</script>

<style type="text/css">
/* input.eduDtlBtn{
    background: #ccc url('reources/images/plus.png') no-repeat top left;
    padding-left: 16px;
    height: 20px;
}

.addNewEduBtn {
   background:url('resources/images/Plus_Circle_Green.png') no-repeat left center;
   padding-left:0px;
   width: 20px;
   height: 20px;
}   

a.button {
    -webkit-appearance: button;
    -moz-appearance: button;
    appearance: button;
	width: 135px;
    text-decoration: none;
    color: initial;
}  */
</style>
</head>
<body>
	<h1>Employee Details Form</h1>
	<form method="post" action="insertEmployee.html" id="addEmpForm">

		<table>
			<tr>
				<td>Designation</td>
				<td>:</td>
				<td><select name="designation" id="designation">
						<option value="mr">Mr.</option>
						<option value="mrs">Mrs.</option>
				</select></td>
			</tr>
			<tr>
				<td>Employee Name</td>
				<td>:</td>
				<td><input type="text" name="empName" id="empName"
					required="required" /></td>
			</tr>
			<tr>
				<td>Employee Address</td>
				<td>:</td>
				<td><input type="text" name="empAddress" id="empAddress"
					required="required" /></td>
			</tr>
			<tr>
				<td>Gender</td>
				<td>:</td>
				<td><input type="radio" name="gender" class="gender"
					value="male" checked="checked">Male <input type="radio"
					class="gender" name="gender" value="female">Female</td>
			</tr>
			<tr>
				<td>Date of Birth</td>
				<td>:</td>
				<td><input type="date" name="dob" id="dob" required /></td>
			</tr>
			<tr>
				<td>Language</td>
				<td>:</td>
				<td><input type="checkbox" name="languages" class="languages"
					value="java">Java <input type="checkbox" name="languages"
					class="languages" value="javaScript">JavaScript <input
					type="checkbox" name="languages" class="languages" value="html">HTML</td>
			</tr>
			<tr>
				<td colspan="3" align="center">
					<!-- <td colspan="3" align="center"><input type="button" value="Add Edu Details" id="eduDtlBtn"> -->
					<a class="button" id="eduDtlBtn"> <img
						src="resources/images/plus.png" /> Add Edu Details
				</a>
				</td>
			</tr>
		</table>

		<table id="eduDtls" hidden="true">
			<tr>
				<th colspan="3">Education Details Record</th>

			</tr>
			<tr>
				<th>Education Name</th>
				<th>Passing Year</th>
				<th>Sort Order</th>
			</tr>

			<tr id="ssc">
				<td><input type="text" name="exmName" class="exmName" /></td>
				<td><input type="text" name="exmYear" min="4" maxlength="4"
					id="exmYear" class="exmYear" /></td>
				<td><input type="text" name="sortOrder" min="1" maxlength="1"
					id="sortOrder" class="sortOrder" /></td>
				<td><input type="button" id="sscBtn" class="addNewEduBtn"></td>
			</tr>

			<tr id="hsc" hidden="true">
				<td><input type="text" name="exmName" class="exmName" /></td>
				<td><input type="text" name="exmYear" min="4" maxlength="4"
					id="exmYear1" class="exmYear" /></td>
				<td><input type="text" name="sortOrder" min="1" maxlength="1"
					id="sortOrder1" class="sortOrder" /></td>
				<td><input type="button" id="hscBtn" class="addNewEduBtn"></td>
			</tr>

			<tr id="bachelor" hidden="true">
				<td><input type="text" name="exmName" class="exmName" /></td>
				<td><input type="text" name="exmYear" min="4" maxlength="4"
					id="exmYear2" class="exmYear" /></td>
				<td><input type="text" name="sortOrder" min="1" maxlength="1"
					id="sortOrder2" class="sortOrder" /></td>
				<td><input type="button" id="bachelorBtn" class="addNewEduBtn"></td>
			</tr>
			<tr id="masters" hidden="true">
				<td><input type="text" name="exmName" class="exmName" /></td>
				<td><input type="text" name="exmYear" min="4" maxlength="4"
					id="exmYear3" class="exmYear" /></td>
				<td><input type="text" name="sortOrder" min="1" maxlength="1"
					id="sortOrder3" class="sortOrder" /></td>
			</tr>
		</table>

		<p style="margin-left: 250">
			<input type="submit" value="Save Employee" />
		</p>

	</form>
</body>

<script>
	addEvent(document.getElementById('exmYear'), 'keyup', validate);
	addEvent(document.getElementById('exmYear'), 'mouseover', validate);
	addEvent(document.getElementById('exmYear1'), 'keyup', validate);
	addEvent(document.getElementById('exmYear1'), 'mouseover', validate);
	addEvent(document.getElementById('exmYear2'), 'keyup', validate);
	addEvent(document.getElementById('exmYear2'), 'mouseover', validate);
	addEvent(document.getElementById('exmYear3'), 'keyup', validate);
	addEvent(document.getElementById('exmYear3'), 'mouseover', validate);

	addEvent(document.getElementById('sortOrder'), 'keyup', validate);
	addEvent(document.getElementById('sortOrder'), 'mouseover', validate);
	addEvent(document.getElementById('sortOrder1'), 'keyup', validate);
	addEvent(document.getElementById('sortOrder1'), 'mouseover', validate);
	addEvent(document.getElementById('sortOrder2'), 'keyup', validate);
	addEvent(document.getElementById('sortOrder2'), 'mouseover', validate);
	addEvent(document.getElementById('sortOrder3'), 'keyup', validate);
	addEvent(document.getElementById('sortOrder3'), 'mouseover', validate);

	function validate(event) {

		var str = this.value;

		var charsAllowed = "0123456789";
		var allowed;

		for (var i = 0; i < this.value.length; i++) {

			allowed = false;

			for (var j = 0; j < charsAllowed.length; j++) {
				if (this.value.charAt(i) == charsAllowed.charAt(j)) {
					allowed = true;
				}
			}

			if (allowed == false) {
				this.value = this.value.replace(this.value.charAt(i), "");
				i--;
			}
		}

		return true;
	}
	function addEvent(obj, type, fn) {

		if (obj.addEventListener) {
			obj.addEventListener(type, fn, false);
			return true;
		} else if (obj.attachEvent) {
			obj['e' + type + fn] = fn;
			obj[type + fn] = function() {
				obj['e' + type + fn](window.event);
			}
			var r = obj.attachEvent('on' + type, obj[type + fn]);
			return r;
		} else {
			obj['on' + type] = fn;
			return true;
		}
	}
</script>

</html>
