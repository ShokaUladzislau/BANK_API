<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add new user</title>
</head>
<body>
	<div>

		<h1>
			Bank API <small>Register new user</small>
		</h1>
		<hr>


		<form action="RegisterUser" method="post">
			<div>
				<label for="username">Username</label>
				<div>
					<input type="text" id="username" name="username"
						placeholder="Username" required="required"
						value="${user.username }" />
				</div>
			</div>

			<div>
				<label for="password">Password</label>
				<div>
					<input type="password" id="password" name="password"
						placeholder="Password" required="required"
						value="${user.password }" />
				</div>
			</div>

			<div>
				<label for="firstName">First Name</label>
				<div>
					<input type="text" id="firstName" name="firstName"
						placeholder="FirstName" required="required"
						value="${user.firstName }" />
				</div>
			</div>

			<div>
				<label for="lastName">Last Name</label>
				<div>
					<input type="text" id="lastName" name="lastName"
						placeholder="LastName" required="required"
						value="${user.lastName }" />
				</div>
			</div>

			<div>
				<label for="email">@mail</label>
				<div>
					<input type="text" id="email" name="email" placeholder="Email"
						required="required" value="${user.email }" />
				</div>
			</div>


			<div class="form-group">
				<div>
					<button type="submit" class="btn btn-info">Create</button>
				</div>
			</div>
		</form>

	</div>
</body>
</html>