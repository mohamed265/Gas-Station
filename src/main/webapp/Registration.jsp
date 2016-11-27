
<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>Registration</title>
<script type="text/javascript" src="js/ajax.js"></script>
<script type="text/javascript">
	var passwordMatched = false;

	var emailMatched = false;

	var isUsernameUnique = false;

	var isEmailUnique = false;

	function notMatchedCheck(id, id1, msg) {
		var e = document.getElementById(id);
		var e1 = document.getElementById(id1);

		if (e.value != e1.value) {
			if (id == 'p1') {
				passwordMatched = false;
				document.getElementById('err_password').style.color = "red";
			} else {
				emailMatched = false;
				document.getElementById('err_email2').style.color = "red";
			}
			e.style.borderColor = "red";
			e1.style.borderColor = "red";
		} else {
			if (id == 'p1') {
				passwordMatched = true;
				document.getElementById('err_password').style.color = "white";
			} else {
				emailMatched = true;
				document.getElementById('err_email2').style.color = "white";
			}
			e.style.borderColor = "";
			e1.style.borderColor = "";
		}
		activationSubmit();
	}

	function activationSubmit() {
		passwordMatched = (document.getElementById('p1').value == document
				.getElementById('p2').value);
		emailMatched = (document.getElementById('e1').value == document
				.getElementById('e2').value);
		if (passwordMatched && emailMatched && isUsernameUnique
				&& isEmailUnique) {
			document.getElementById("subBtn").disabled = false;
			return true;
		} else {
			document.getElementById("subBtn").disabled = true;
			return false;
		}
	}

	function usernameUniqeCheck() {
		var v = document.getElementById('username').value;
		ajaxCall(
				"ValidationServlet?type=username&username=" + v,
				"GET",
				function(response) {
					if (response == "true") {
						document.getElementById('err_username').style.color = "red";
						isUsernameUnique = false;
					} else {
						document.getElementById('err_username').style.color = "white";
						isUsernameUnique = true;
					}
					activationSubmit();
				}, null);
	}

	function emailUniqeCheck() {
		var v = document.getElementById('e1').value;
		ajaxCall("ValidationServlet?type=email&email=" + v, "GET", function(
				response) {
			if (response == "true") {
				document.getElementById('err_email').style.color = "red";
				isEmailUnique = false;
			} else {
				document.getElementById('err_email').style.color = "white";
				isEmailUnique = true;
			}
			activationSubmit();
		}, null);
	}
</script>
</head>

<body>
	<form name="registration" method="post" action="User/UserServlet"
		onsubmit="return activationSubmit();">
		<table align="center" cellspacing="2" cellpadding="2" border="0">
			<tr>
				<td colspan="2" align="center"><strong>User
						Registration</strong></td>
			</tr>
			<tr>
				<td>Username</td>
				<td><input id="${Constants.username}" type="Text"
					name="${Constants.username}" onchange="usernameUniqeCheck()"
					maxlength="100" size="20" required="required"></td>
				<td><label id="err_username" style="color: white">Username
						Already Exists</label></td>
			</tr>
			<tr>
				<td>Password</td>
				<td><input id="p1" type="password" name="${Constants.password}"
					size="20" maxlength="100" required="required"></td>
			</tr>
			<tr>
				<td>Retype Password</td>
				<td><input id="p2" type="password" name="${Constants.password}"
					size="20"
					onchange="notMatchedCheck('p1','p2','password not matched')"
					maxlength="100" required="required"></td>
				<td><label id="err_password" style="color: white">Password
						not matched</label></td>
			</tr>
			<tr>
				<td>Email ID</td>
				<td><input id="e1" type="email" name="${Constants.email}"
					onchange="emailUniqeCheck()" size="30" maxlength="150"
					required="required"></td>
				<td><label id="err_email" style="color: white">Email
						Already Exists</label></td>
			</tr>
			<tr>
				<td>Verify Email</td>
				<td><input id="e2" type="email" name="${Constants.email}"
					onchange="notMatchedCheck('e1','e2','email not matched')" size="30"
					maxlength="150" required="required"></td>
				<td><label id="err_email2" style="color: white">Email
						not matched</label></td>
			</tr>
			<tr hidden="true">
				<td>Site Number</td>
				<td><input type="Text" name="${Constants.siteNumber}" size="20"
					maxlength="20" required="required"></td>
			</tr>
			<tr hidden="true">
				<td>Site Name</td>
				<td><input type="Text" name="${Constants.siteName}" size="20"
					maxlength="100" required="required"></td>
			</tr>
			<tr>
				<td>Security Question</td>
				<td><input type="Text" name="${Constants.securityQuestion}"
					size="30" maxlength="500" required="required"></td>
			</tr>
			<tr>
				<td>Security Answer</td>
				<td><input type="Text" name="${Constants.securityAnswer}"
					size="30" maxlength="500" required="required"></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="hidden"
					name="${Constants.put}" value="${Constants.put}" /> <input
					id="subBtn" type="Submit" value="Register">&nbsp;&nbsp;<input
					type="Reset" value="Clear"></td>
			</tr>
		</table>



	</form>


</body>
</html>
