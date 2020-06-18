<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Page</title>
</head>
<body>
	<div class="container">

		<h1>
			Bank API<small> Login</small>
		</h1>
		<hr>
		<h2>
			Incorrect login or password
		</h2>

		<form action="Login" method="post">
			<div>
				<label for="username">Username</label>
				<div>
					<input type="text" id="username" name="username"
						placeholder="Username" required="required"
						value="${param.username }" />
				</div>
			</div>
			<div>
				<label for="password">Password</label>
				<div>
					<input type="text" id="password" name="password"
						placeholder="Password" required="required"
						value="${param.password }" />
				</div>
			</div>
			<hr>
			<div>
				<div>
					<button type="submit">Login</button>
				</div>
			</div>
		</form>
		
		<form>
			<input type="button" value="RegisterUser"
				onclick="window.location.href='Register'" />
		</form>

	</div>
</body>
</html>