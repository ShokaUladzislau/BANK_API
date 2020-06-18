<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>AdminAccountDetails</title>
</head>
<body>
	<div>


		<h1>
			Bank API <small> Account Details - ${account.accountId }</small>
		</h1>
		
		<hr>
		
		<form>
			<input type="button" value="Logout"
				onclick="window.location.href='Logout'" />
		</form>

		<hr>
		
		<div>
			<form action="UpdateAccount" method="post">

				<div>
					<input type="hidden" id="accountId" name="accountId"
						required="required" value="${account.accountId }" />

					<div>
						<label for="balance">Balance</label>
						<div>
							<input type="text" id="balance" name="balance"
								placeholder="Balance" required="required"
								value="${account.balance }" />
						</div>
					</div>

					<div>
						<label for="status">Status</label>
						<div>
							<input type="text" id="statusId" name="statusId" placeholder="StatusId"
								required="required" value="${account.status.statusId }" />
						</div>
					</div>

					<div>
						<label for="type">Type</label>
						<div>
							<input type="text" id="typeId" name="typeId" placeholder="TypeId"
								required="required" value="${account.type.typeId }" />
						</div>
					</div>

				</div>

					<div>
						<button type="submit" class="btn btn-info">Update Account</button>
					</div>
			</form>
		</div>
	</div>

	<div>
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
	</div>
</body>
</html>