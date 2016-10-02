<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Pre Qualification Form</title>
</head>
<body>

	<form action="preQualiInfoIns.htm" method="POST">
		<table>
			<tr>
				<th colspan="3">
					<h1>Pre Qualification Form</h1>
				<th>
			</tr>
			<tr>
				<th colspan="3">
					<h1>PART - A</h1>
				<th>
			</tr>
			<!-- General Information -->
			<tr>
				<td rowspan="7" valign="top">1.</td>
				<th colspan="2">General Information</th>
			</tr>


			<tr>
				<th>Name of Applicant</th>
				<td colspan="2"></td>
			</tr>

			<tr>
				<th>Name of Firm</th>
				<td colspan="2"></td>
			</tr>

			<tr>
				<th>Address</th>
				<td colspan="2"></td>
			</tr>

			<tr>
				<th>Phone No.</th>
				<td colspan="2"></td>
			</tr>

			<tr>
				<th>Fax No.</th>
				<td colspan="2"></td>
			</tr>


			<tr>
				<th>Email</th>
				<td colspan="2"></td>
			</tr>
			
			
			<!-- Land and Factory Building -->
			<tr>
				<td rowspan="3" valign="top">2.</td>
				<th colspan="2">Land and Factory Building</th>
			</tr>


			<tr>
				<th>a) Land Required (sq. meters) :</th>
				<td colspan="2"></td>
			</tr>

			<tr>
				<th>b) Space requirement in standard factory building (sq. meters) :</th>
				<td colspan="2"></td>
			</tr>
			
			<!-- Name of the proposed project -->
			<tr>
				<td>3.</td>
				<th colspan="2">Name of the proposed project</th>
				<td></td>
			</tr>
			
			<!-- Type of Business / Industry -->
			<tr>
				<td>4.</td>
				<th colspan="2">Type of Business / Industry</th>
				<td></td>
			</tr>
			
			<!-- Type of Products -->
			<tr>
				<td>5.</td>
				<th colspan="2">Type of Products</th>
				<td></td>
			</tr>
			
			<!-- Type of organization -->
			<tr>
				<td>6.</td>
				<th colspan="2">Type of organization</th>
				<td></td>
			</tr>
			
			<!-- Name of the Zone -->
			<tr>
				<td>7.</td>
				<th colspan="2">Name of the Zone</th>
				<td></td>
			</tr>		
			
			
			<!-- Manpower Requirements : -->
			<tr>
				<td rowspan="3" valign="top">8.</td>
				<th colspan="2">Manpower Requirements :</th>
			</tr>


			<tr>
				<th>Bangladeshi</th>
				<td colspan="2"></td>
			</tr>

			<tr>
				<th>Non-Bangladeshi</th>
				<td colspan="2"></td>
			</tr>
			
			<!-- Investments : (All cost in '000'US $) -->
			<tr>
				<td rowspan="4" valign="top">9.</td>
				<th colspan="2">Investments : (All cost in '000'US $)</th>
			</tr>


			<tr>
				<th>Foreign</th>
				<td colspan="2"></td>
			</tr>

			<tr>
				<th>Local</th>
				<td colspan="2"></td>
			</tr>
			
			<tr>
				<th>Total</th>
				<td colspan="2"></td>
			</tr>
			
			<tr>
				<td colspan="3" align="right"><input type="submit" value="Create"/></td>
			</tr>

		</table>
	</form>
</body>
</html>
