
<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>Activation</title>
</head>
<body>
	<form name="Activation" method="post" action="User/ActivationServlet">
		<table align="center" cellspacing="2" cellpadding="2" border="0">
			<tr>
				<td colspan="2" align="center"><strong>User Activation</strong></td>
			</tr>
			<tr>
				<td>Email ID</td>
				<td><input id="e1" type="email" name="${Constants.email}"
					size="30" maxlength="150" required="required"></td>
			</tr>
			<tr>
				<td>Verify Email</td>
				<td><input id="e2" type="Text" name="${Constants.code}"
					size="10" maxlength="11" required="required"></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="hidden"
					name="${Constants.activation}" value="${Constants.activation}" />
					<input id="subBtn" type="Submit" value="Activate">&nbsp;&nbsp;<input
					type="Reset" value="Clear"></td>
			</tr>
		</table>
	</form>
</body>
</html>

