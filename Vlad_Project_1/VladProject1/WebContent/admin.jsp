<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>AdminPage</title>
</head>
<body>

	<div>

		<h1>
			Bank API<small> Admin page </small>
		</h1>

		<hr>

		<form>
			<input type="button" value="Logout"
				onclick="window.location.href='Logout'" />
		</form>


		<hr>

		<form>
			<input type="button" value="Add user"
				onclick="window.location.href='AddUser'" />
		</form>

		<table
			style="text-align: center; width: 100%; border: 1px solid black;">
			<caption>
				<b>Users:</b>
			</caption>
			<thead>
				<tr>
					<td>User ID:</td>
					<td>Username:</td>
					<td>Password:</td>
					<td>First name:</td>
					<td>Last name:</td>
					<td>@mail:</td>
					<td>Type:</td>
					<td></td>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="user" items="${users}">

					<tr>
						<td><c:out value="${user.userId}" /></td>
						<td><c:out value="${user.username}" /></td>
						<td><c:out value="${user.password}" /></td>
						<td><c:out value="${user.firstName}" /></td>
						<td><c:out value="${user.lastName}" /></td>
						<td><c:out value="${user.email}" /></td>
						<td><c:out value="${user.role.role}" /></td>
						<td><form action="AdminViewUserDetails?userid=${user.userId}"
								method="get">
								<input type="hidden" name="userId" value="${user.userId}">
								<button>Details</button>
							</form></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
		<hr>

		<div>
		
		<form>
			<input type="button" value="Add account"
				onclick="window.location.href='AddAccount'" />
		</form>
		
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
		<hr>



	</div>

</body>
</html>