<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User information</title>
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

		<form action="UserUpdateByUser" method="post">

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
				<button type="submit" class="btn btn-info">Update User</button>
			</div>
		</form>
	</div>

	<form>
		<input type="button" value="Up to Premium"
			onclick="window.location.href='UpToPemium?userId=${user.userId}'" />
	</form>

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
								action="UserViewAccountDetails?accountId=${account.accountId}"
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