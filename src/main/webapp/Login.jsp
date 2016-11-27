
<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>Login</title>
</head>

<body>
	<form name="login" action="User/UserServlet" method="post">
		<table align="center" cellspacing="2" cellpadding="2" border="0"
			width="700">
			<tr>
				<td colspan="2" align="center"><strong>User Login</strong></td>
			</tr>
			<tr>
				<td>Username</td>
				<td><input type="Text" name="${Constants.username}" size="20"
					required="required"></td>
			</tr>
			<tr>
				<td>Password</td>
				<td><input type="password" name="${Constants.password}"
					size="20" required="required"></td>
			</tr>
			<tr>
				<td>remember me</td>
				<td><input type="checkbox" name="${Constants.rememberme}"></td>
			</tr>
			<tr hidden="true">
				<td>Select Site</td>
				<td><select name="sitename">
						<option>10477 Tillsonburg</option>
						<option>10478 London</option>
				</select>&nbsp;&nbsp;(values should be pulled from DB as per registration
					details)</td>
			</tr>

			<tr>
				<td colspan="2"><input type="Submit" Value="Login"></td>

			</tr>
		</table>



	</form>


</body>
</html>
