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
								value="${account.balance }" disabled/>
						</div>
					</div>

					<div>
						<label for="status">Status</label>
						<div>
							<select id="statusId" name="statusId" required="required">
								<option value=${account.status.statusId } selected>${account.status.status }</option>
								<option value="0">Pending</option>
								<option value="1">Open</option>
								<option value="2">Closed</option>
								<option value="3">Denied</option>
							</select>
						</div>
					</div>

					<div>
						<label for="type">Type</label>
						<div>
							<select id="typeId" name="typeId" required="required">
								<option value=${account.type.typeId } selected>${account.type.type }</option>
								<option value="0">Checking</option>
								<option value="1">Savings</option>
							</select>
						</div>
					</div>

				</div>

				<div>
					<button type="submit" class="btn btn-info">Update Account</button>
				</div>
			</form>
		</div>
		
		<hr>
		<form action="WithdrawAccount" method="post">
			<span> <input type="hidden" id="accountId" name="accountId"
				required="required" value="${account.accountId }" />
				
				 <label
				for="withdraw">Withdraw account</label> 
				
				<input type="text" id="withdraw"
				name="withdraw" placeholder="insert sum" required="required"/>

				<button type="submit" class="btn btn-info">Withdraw</button>
			</span>
		</form>
		<hr>
		
		<hr>
		<form action="DepositAccount" method="post">
			<span> <input type="hidden" id="accountId" name="accountId"
				required="required" value="${account.accountId }" />
				
				 <label
				for="deposit">Deposit account</label> 
				
				<input type="text" id="deposit"
				name="deposit" placeholder="insert sum" required="required"/>

				<button type="submit" class="btn btn-info">Deposit</button>
			</span>
		</form>
		<hr>
		
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