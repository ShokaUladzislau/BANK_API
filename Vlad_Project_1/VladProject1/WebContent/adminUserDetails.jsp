<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>AdminUserDetails</title>
</head>
<body>
	<div>


		<h1>
			Bank API <small> User Details - ${user.userId }</small>
		</h1>

		<hr>

		<form>
			<input type="button" value="Logout"
				onclick="window.location.href='Logout'" />
		</form>

		<hr>

		<form action="UpdateUser" method="post">

			<input type="hidden" id="userId" name="userId" required="required"
				value="${user.userId }" />

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
					<input type="text" id="password" name="password"
						placeholder="Password" required="required"
						value="${user.password }" />
				</div>
			</div>

			<div>
				<label for="firstName">FirstName</label>
				<div>
					<input type="text" id="firstName" name="firstName"
						placeholder="FirstName" required="required"
						value="${user.firstName }" />
				</div>
			</div>

			<div>
				<label for=lastName>LastName</label>
				<div>
					<input type="text" id="lastName" name="lastName"
						placeholder="LastName" required="required"
						value="${user.lastName }" />
				</div>
			</div>

			<div>
				<label for=email>Email</label>
				<div>
					<input type="text" id="email" name="email" placeholder="Email"
						required="required" value="${user.email }" />
				</div>
			</div>

			<div>
				<label for=roleId>Role</label>
				<div>
					<select id="roleId" name="roleId" required="required">
						<option value=${role.roleId } selected>${role.role }</option>
						<option value="0">Standart</option>
						<option value="1">Premium</option>
						<option value="2">Employee</option>
						<option value="3">Admin</option>
					</select>
				</div>
			</div>

			<div>
				<button type="submit" class="btn btn-info">Update User</button>
			</div>
		</form>

		<form action="DeleteUser?userId=${user.userId}" method="get">
			<div>
				<div>
					<input type="hidden" name="userId" value="${user.userId}">
					<button class="btn btn-info">Delete User</button>
				</div>
			</div>
		</form>
	</div>

	<div>
		<table
			style="text-align: center; width: 100%; border: 1px solid black;">
			<caption>
				<b>Accounts:</b>
			</caption>
			<thead>
				<tr>
					<td>Account ID:</td>
					<td>Balance:</td>
					<td>Status:</td>
					<td>Type:</td>
					<td></td>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="account" items="${accounts}">

					<tr>
						<td><c:out value="${account.accountId}" /></td>
						<td><c:out value="${account.balance}" /></td>
						<td><c:out value="${account.status.status}" /></td>
						<td><c:out value="${account.type.type}" /></td>
						<td><form
								action="AdminViewAccountDetails?accountId=${account.accountId}"
								method="get">
								<input type="hidden" name="accountId"
									value="${account.accountId}">
								<button>Details</button>
							</form></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>