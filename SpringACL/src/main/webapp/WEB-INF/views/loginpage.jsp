<%--
    Document   : Source
    Created on : Nob 15, 2015, 10:35:33 AM
    Author     : Ahasanul Ashid, IBCS, And Abu Taleb, IBCS
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Pragma" content="no-cache">
 <meta http-equiv="Cache-Control" content="no-cache">
<jsp:include page="CommonInclude.jsp" />
</head>
<body>
	<div id="header">
		<!-- Header. Status part -->
		<div id="header-status">
			<div class="container_12">
				<div class="grid_8">
					<a href="default.htm" id="logout"> Login </a>
				</div>
			</div>
			<div style="clear: both;"></div>
		</div>
		<!-- End #header-status -->
		<!-- Header. Main part -->
		<div id="header-main">
			<div class="container_12">
				<div class="grid_12">
					<div id="logo"></div>
				</div>
				<!-- End. .grid_12-->
				<div style="clear: both;"></div>
			</div>
			<!-- End. .container_12 -->
		</div>
		<!-- End #header-main -->
		<div style="clear: both;"></div>
		<!-- Sub navigation -->
		<div id="subnav">
			<div style="clear: both;"></div>
		</div>
		<!-- End #subnav -->
	</div>
	<div class="container_12">
		<div id="login-Div">
			<form action="../j_spring_security_check" method="post">
				<table border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td>
							<p align="center" class="login-p">
								<img
									src="${pageContext.request.contextPath}/resources/html/admin/images/login.png"
									alt="" />
							</p>
						</td>
					</tr>
					<tr>
						<td>
							<div id="innerLogin">
								<table border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="20">&nbsp;</td>
									</tr>
									<tr>
										<td>User Name:</td>
										<td>
											<div class="loginDiv">
												<input id="j_username" name="j_username" type="text"
													class="input-fields-login"
													onfocus="if(this.value=='User Name'){this.value=''};"
													onblur="if(this.value==''){this.value='User Name'};"
													class="loginInput" />
											</div>
										</td>
									</tr>
									<tr>
										<td>password:</td>
										<td>
											<div class="loginDiv">
												<input id="j_password" name="j_password" type="password"
													onfocus="if(this.value=='Password'){this.value=''};"
													onblur="if(this.value==''){this.value='Password'};"
													class="loginInputSmall" /> <a href="#"> <span>Forgot
														your Password?</span>
												</a>
											</div>
										</td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td><input name="" type="checkbox" value=""
											class="checkInput" /> Remember me on this Computer</td>
									</tr>
								</table>
							</div> <!--innerLogin-->
						</td>
					</tr>
					<tr>
						<td><input name="" type="submit" value="" class="submutInput" />
						</td>
					</tr>
				</table>
			</form>
		</div>
		<!--login-Div-->
		<div style="clear: both;"></div>
	</div>
	<!-- End .container_12 -->
</body>
</html>