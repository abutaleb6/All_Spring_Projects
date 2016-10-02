<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">

<title>BEPZA Template using Bootstrap</title>

<!-- Bootstrap core CSS -->
<link href="<c:url value="/resources/css/bootstrap.min.css" />"
	rel="stylesheet">



<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>

	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">Bangladesh Export Processing
					Zones Authoritiy (BEPZA)</a>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<form class="navbar-form navbar-right">
					<div class="form-group">
						<input type="text" placeholder="Email" class="form-control">
					</div>
					<div class="form-group">
						<input type="password" placeholder="Password" class="form-control">
					</div>
					<button type="submit" class="btn btn-success">Sign in</button>
				</form>
			</div>
			<!--/.navbar-collapse -->
		</div>
	</nav>

	<!-- Main jumbotron for a primary marketing message or call to action -->
	<div class="jumbotron">
		<div class="container">
			<h1>Hello, ${pageContext.request.contextPath}!</h1>
			<P>The number of row is ${serverTime}.</P>
			<p>This is a template for a simple marketing or informational
				website. It includes a large callout called a jumbotron and three
				supporting pieces of content. Use it as a starting point to create
				something more unique.</p>
			<!--  <p><a class="btn btn-primary btn-lg" href="<c:url value="/company/list.do" />" role="button"> -->

			<p>
				<a class="btn btn-primary btn-lg" href="#" id="viweAllCompany"
					role="button"> View all Enterprizes &raquo;</a>
			</p>
		</div>
	</div>

	<div class="container">
		<!-- Example row of columns -->
		<div class="row">
			<div class="col-md-4">

				<form:form
					action="${pageContext.request.contextPath}/company/save.do"
					method="post">
					<div class="form-group">
						<input type="text" class="form-control" id="name" name="name"
							placeholder="Enter Company Name">
					</div>
					<div class="form-group">
						<input type="text" class="form-control" id="zone" name="zone"
							placeholder="Enter Zone Name">
					</div>
					<div class="form-group">
						<input type=number class="form-control" id="investment"
							name="investment"
							placeholder="Enter Total Investment in Million USD">
					</div>
					<div class="form-group">
						<input type="number" class="form-control" id="employment"
							name="employment" placeholder="Enter Employment Size">
					</div>
					<div class="text-center">
						<button type="submit" class="btn btn-info">Save</button>
					</div>
				</form:form>


			</div>

			<div class="col-md-8">
			
			<table id="companytable" class="table table-striped">

	</table>
				<%-- <c:if test="${!empty companyList}">

					<table class="table table-striped">
						<tr>
							<th>Company ID</th>
							<th>Company Name</th>
							<th>EPZ Name</th>
							<th>Investment (Million USD)</th>
							<th>Employment</th>
							<th>Edit</th>
							<th>Delete</th>

						</tr>

						<c:forEach var="row" items="${companyList}">
							<tr>
								<td><c:out value="${row.id}" /></td>
								<td><c:out value="${row.name}" /></td>
								<td><c:out value="${row.zone}" /></td>
								<td><c:out value="${row.investment}" /></td>
								<td><c:out value="${row.employment}" /></td>
								<td><a href="company/edit.do?id=${row.id}"
									class="btn btn-success" />Edit</a></td>
								<td><a href="company/delete.html?id=${row.id}"
									class="btn btn-danger" />Delete</a></td>
							</tr>
						</c:forEach>
					</table>
				</c:if> --%>
			</div>
		</div>

		<hr>

		<footer>
			<div>
				<div class="col-md-6 text-left">
					<a class="btn btn-default"
						href="${pageContext.request.contextPath}" role="button">Home
						&raquo;</a>
				</div>
				<div class="col-md-6 text-right">&copy; BEPZA 2015</div>
			</div>

		</footer>
	</div>
	<!-- /container -->


	<!-- Required JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="<c:url value="/resources/js/jquery.min.js"/>"></script>
	<script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
	<script src="<c:url value="/resources/js/home.js"/>"></script>

	<script type="text/javascript">
		$(document).ready(function() {
			$("#viweAllCompany").click(function() {
				$.ajax({
					url : 'http://localhost:8282/mis/company/list.do',
					data : '',
					contentType : 'application/json',
					success : function(response) {
						var res = JSON.parse(response);
						var count = $('#companytable tr').length;
						if (count != null) {
							$("#companytable tr").remove();
						}
						var tr1;
						tr1 = $('<tr/>');
						tr1.append('<td> id </td>');
						tr1.append('<td> name </td>');
						tr1.append('<td> zone </td>');
						tr1.append('<td> investment </td>');
						tr1.append('<td> employment </td>');
						$('#companytable').append(tr1);
						var tr;
						for (var i = 0; i < res.length; i++) {
							tr = $('<tr/>');
							tr.append("<td>" + res[i].id + "</td>");
							tr.append("<td>" + res[i].name + "</td>");
							tr.append("<td>" + res[i].zone + "</td>");
							tr.append("<td>" + res[i].investment + "</td>");
							tr.append("<td>" + res[i].employment + "</td>");							
							$('#companytable').append(tr);
						}
					},
					error : function(response) {
						alert('failed');
						console.log(response);
					},
					type : 'POST'

				});
			});
		});
	</script>

	<!-- <table id="companytable" class="table table-striped">

	</table> -->
</body>
</html>
