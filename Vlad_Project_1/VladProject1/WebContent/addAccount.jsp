<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add new account</title>
</head>
<body>
	<div>

		<h1>
			Bank API <small>Add new account</small>
		</h1>
		
		<hr>
		
		<form>
			<input type="button" value="Logout"
				onclick="window.location.href='Logout'" />
		</form>

		<hr>


		<form action="AddAccount" method="post">
			<div>
				<label for="accountId">Account Id</label>
				<div>
					<input type="text" id="accountId" name="accountId"
						placeholder="Account ID" required="required"
						value="${account.accountId }" />
				</div>
			</div>

			<div>
				<label for="balance">Balance</label>
				<div>
					<input type="text" id="balance" name="balance"
						placeholder="Balance" required="required"
						value="${account.balance }" />
				</div>
			</div>

			<div>
				<label for="statusId">Status Id</label>
				<div>
					<input type="text" id="statusId" name="statusId" placeholder="Status ID"
						required="required" value="${status.statusId }" />
				</div>
			</div>
			
			<div>
				<label for="typeId">Type Id</label>
				<div>
					<input type="text" id="typeId" name="typeId" placeholder="Type ID"
						required="required" value="${type.typeId }" />
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