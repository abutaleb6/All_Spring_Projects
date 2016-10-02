<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
<!-- <meta charset="utf-8"> -->
<title>Forms</title>
<link rel="stylesheet" href="resources/css/style.css" />
<link href='resources/css/Engagement.css' rel='stylesheet'
	type='text/css'>
<!--[if IE]>
  <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
<![endif]-->
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js" type="text/javascript"> </script>
<script src="resources/js/jquery.uniform.min.js" type="text/javascript">
<!-- charset="utf-8" -->
	
</script>
<script type="text/javascript">
<!-- charset="utf-8" -->
	$(function() {
		$("input:checkbox, input:radio, input:file, select").uniform();
	});
</script>
</head>
<body>
	<article>
		<h1>form elements</h1>
		<form action="myForm.htm" method="post" enctype="multipart/form-data">

			<ul>
				<li><label for="name">Your Name:</label> <input type="text"
					size="40" id="name" name="name" /></li>
				<li><label for="email">Your Email:</label> <input type="email"
					size="40" id="email" name="email" /></li>
				<li><label for="date">Date of Birth:</label> <input type="date"
					size="40" id="date" name="dob" /></li>
				<li><label for="car">What's my options:</label> <select
					id="car" name="car">
						<option>Volvo</option>
						<option>Saab</option>
						<option>Mercedes</option>
						<option>Audi</option>
						<option>Other&hellip;</option>
				</select></li>
				<li><label><input type="radio" name="radio" value="1"
						checked="checked" /> Pick one</label> <label><input type="radio"
						name="radio" /> And stick with it</label></li>
				<li><label><input type="checkbox" name="checkbox"
						value="tickbox" /> Can has tickbox?</label></li>
				<li><label><input type="checkbox" name="checkbox"
						value="siliender" /> Can has siliender?</label></li>
				<li><label>Upload a file:</label> <input type="file"
					name="file" /></li>
				<li><label for="message">Message:</label> <textarea cols="50"
						rows="5" id="message" name="message"></textarea></li>
			</ul>
			<p>
				<button type="submit" class="action">Call to action</button>
				<button type="reset" class="right">Reset</button>
			</p>
		</form>
	</article>
	<footer>
		<p>
			a Software Solution Company at <a href="http://www.ibcs-primax.com">IBCS-PRIMAX</a>
		</p>
	</footer>
</html>