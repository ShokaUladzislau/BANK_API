package com.VladProject.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.VladProject.Models.Account;
import com.VladProject.Models.AccountStatus;
import com.VladProject.Models.AccountType;
import com.VladProject.Utilites.DAOUtilities;

public class AccountDAOImpl implements AccountDAO {

	Connection connection = null; // Our connection to the database
	PreparedStatement stmt = null; // We use prepared statements to help protect against SQL injection
	Statement mystmt = null; // We use this statements to help update SQL
	
	Boolean accessTransferPermition = true; // Permition boolean for our operations
	
	/*------------------------------------------------------------------------------------------------*/

	@Override
	public Account getAccountByAccountId(int accountId) {
		Account account = null;
		AccountStatus accountStatus = null;
		AccountType accountType = null;

		try {
			connection = DAOUtilities.getConnection();
			String sql = "select * from vpdb_accounts\n"
					+ "left join vpdb_accountstatus on vpdb_accounts.status = vpdb_accountstatus.statusid\n"
					+ "left join vpdb_accounttype on vpdb_accounts.type = vpdb_accounttype.typeid\n"
					+ "where accountid = ?\n";

			stmt = connection.prepareStatement(sql);

			stmt.setInt(1, accountId);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {

				account = new Account();
				accountStatus = new AccountStatus();
				accountType = new AccountType();

				accountStatus.setStatusId(rs.getInt("STATUSID"));
				accountStatus.setStatus(rs.getString("ACCSTATUS"));

				accountType.setTypeId(rs.getInt("TYPEID"));
				accountType.setType(rs.getString("ACCTYPE"));

				account.setAccountId(rs.getInt("ACCOUNTID"));
				account.setBalance(rs.getFloat("BALANCE"));
				account.setStatus(accountStatus);
				account.setType(accountType);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		return account;
	}

	/*------------------------------------------------------------------------------------------------*/

	@Override
	public Account createAccount(int accountId, double balance, int status, int type) {
		Account account = null;

		try {
			connection = DAOUtilities.getConnection();
			String sql = "insert into vpdb_accounts\n" + "(accountid, balance, status, type)\n" + "values \n" + "("
					+ accountId + ", " + balance + ", " + status + ", " + type + ")";

			mystmt = connection.createStatement();

			mystmt.executeUpdate(sql);
			mystmt.close();
			connection.close();
			String message = "Status Code 201 CREATED";
			System.out.println(message);

			account = getAccountByAccountId(accountId);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		return account;

	}

	/*------------------------------------------------------------------------------------------------*/

	@Override
	public Account updateAccount(int accountId, double balance, int status, int type) {
		Account account = null;

		try {
			connection = DAOUtilities.getConnection();
			String sql = "update\n" + "\n" + "(select \n" + "vpdb_accounts.accountid,\n" + "vpdb_accounts.balance,\n"
					+ "vpdb_accounts.status,\n" + "vpdb_accounts.type,\n" + "vpdb_accountstatus.statusid,\n"
					+ "vpdb_accountstatus.accstatus,\n" + "vpdb_accounttype.typeid,\n" + "vpdb_accounttype.acctype\n"
					+ "from vpdb_accounts\n"
					+ "left join vpdb_accountstatus on vpdb_accountstatus.statusid = vpdb_accounts.status\n"
					+ "left join vpdb_accounttype on vpdb_accounttype.typeid = vpdb_accounts.type\n"
					+ "where vpdb_accounts.accountid = " + accountId + ")\n" + "myaccount\n" + "set\n"
					+ "myaccount.balance = " + balance + ",\n" + "myaccount.status = " + status + ",\n"
					+ "myaccount.type = " + type + "\n";

			mystmt = connection.createStatement();

			mystmt.executeUpdate(sql);
			mystmt.close();
			connection.close();
			String message = "Status Code UPDATED";
			System.out.println(message);

			account = getAccountByAccountId(accountId);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		return account;

	}

	/*------------------------------------------------------------------------------------------------*/
	
	

	@Override
	public Account withdrawAccount(int accountId, double balance) {
		Account account = null;

		account = getAccountByAccountId(accountId);

		if (balance > account.getBalance()) {
			System.out.println("You can't approve this operation: not enough money...");
			
			accessTransferPermition = false;
			
			return account;	
		} else {
			balance = account.getBalance() - balance;
		}
		
		try {
			connection = DAOUtilities.getConnection();
			String sql = "update\n" + "					\n" + "(select\n" + "vpdb_accounts.accountid,\n"
					+ "vpdb_accounts.balance,\n" + "vpdb_accounts.status,\n" + "vpdb_accounts.type,\n"
					+ "vpdb_accountstatus.statusid,\n" + "vpdb_accountstatus.accstatus,\n"
					+ "vpdb_accounttype.typeid,\n" + "vpdb_accounttype.acctype\n" + "from vpdb_accounts\n"
					+ "left join vpdb_accountstatus on vpdb_accountstatus.statusid = vpdb_accounts.status\n"
					+ "left join vpdb_accounttype on vpdb_accounttype.typeid = vpdb_accounts.type\n"
					+ "where vpdb_accounts.accountid = " + accountId + ")\n" + "myaccount  \n" + "set \n"
					+ "myaccount.balance = " + balance + "";

			mystmt = connection.createStatement();

			mystmt.executeUpdate(sql);
			mystmt.close();
			connection.close();
			String message = "Status Code withdrawed";
			System.out.println(message);

			account = getAccountByAccountId(accountId);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		return account;

	}

	/*------------------------------------------------------------------------------------------------*/

	@Override
	public Account depositAccount(int accountId, double balance) {
		Account account = null;

		account = getAccountByAccountId(accountId);
		balance = account.getBalance() + balance;

		try {
			connection = DAOUtilities.getConnection();
			String sql = "update\n" + "					\n" + "(select\n" + "vpdb_accounts.accountid,\n"
					+ "vpdb_accounts.balance,\n" + "vpdb_accounts.status,\n" + "vpdb_accounts.type,\n"
					+ "vpdb_accountstatus.statusid,\n" + "vpdb_accountstatus.accstatus,\n"
					+ "vpdb_accounttype.typeid,\n" + "vpdb_accounttype.acctype\n" + "from vpdb_accounts\n"
					+ "left join vpdb_accountstatus on vpdb_accountstatus.statusid = vpdb_accounts.status\n"
					+ "left join vpdb_accounttype on vpdb_accounttype.typeid = vpdb_accounts.type\n"
					+ "where vpdb_accounts.accountid = " + accountId + ")\n" + "myaccount  \n" + "set \n"
					+ "myaccount.balance = " + balance + "";

			mystmt = connection.createStatement();

			mystmt.executeUpdate(sql);
			mystmt.close();
			connection.close();
			String message = "Status Code deposied";
			System.out.println(message);

			account = getAccountByAccountId(accountId);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		return account;

	}

	/*------------------------------------------------------------------------------------------------*/
	
	@Override
	public List<Account> transferBetweenAccounts(int accountId1, int accountId2, double balance) {
		List<Account> accounts = new ArrayList<>();
		
		Account account1 = null;
		Account account2 = null;
		
		account1 = withdrawAccount(accountId1, balance);
		if (accessTransferPermition == true) {
			account2 = depositAccount(accountId2, balance);
			System.out.println("We transfered " +balance+ " dollars from " +account1.getAccountId()+ " to " +accountId2);
			accounts.add(account1);
			accounts.add(account2);
			return accounts;
		} else {
			System.out.println("We can't transfered " +balance+ " dollars from " +account1.getAccountId()+ " to " +accountId2);
			account1 = getAccountByAccountId(accountId1);
			account2 = getAccountByAccountId(accountId2);			
			accounts.add(account1);
			accounts.add(account2);
			accessTransferPermition = true;
			return accounts;
		}
	}

	/*------------------------------------------------------------------------------------------------*/

	@Override
	public List<Account> getAccounts() {
		List<Account> accounts = new ArrayList<>();

		try {
			connection = DAOUtilities.getConnection();
			String sql = "select * from vpdb_accounts\n"
					+ "left join vpdb_accountstatus on vpdb_accounts.status = vpdb_accountstatus.statusid\n"
					+ "left join vpdb_accounttype on vpdb_accounts.type = vpdb_accounttype.typeid";

			stmt = connection.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				Account account = new Account();
				AccountStatus accountStatus = new AccountStatus();
				AccountType accountType = new AccountType();

				accountStatus.setStatusId(rs.getInt("STATUSID"));
				accountStatus.setStatus(rs.getString("ACCSTATUS"));

				accountType.setTypeId(rs.getInt("TYPEID"));
				accountType.setType(rs.getString("ACCTYPE"));

				account.setAccountId(rs.getInt("ACCOUNTID"));
				account.setBalance(rs.getFloat("BALANCE"));
				account.setStatus(accountStatus);
				account.setType(accountType);

				accounts.add(account);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		return accounts;

	}

	/*------------------------------------------------------------------------------------------------*/

	@Override
	public List<Account> getAccountsByStatus(int status) {
		List<Account> accounts = new ArrayList<>();

		try {
			connection = DAOUtilities.getConnection();
			String sql = "select * from vpdb_accounts\n"
					+ "left join vpdb_accountstatus on vpdb_accounts.status = vpdb_accountstatus.statusid\n"
					+ "left join vpdb_accounttype on vpdb_accounts.type = vpdb_accounttype.typeid\n"
					+ "where status = ?";

			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, status);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				Account account = new Account();
				AccountStatus accountStatus = new AccountStatus();
				AccountType accountType = new AccountType();

				accountStatus.setStatusId(rs.getInt("STATUSID"));
				accountStatus.setStatus(rs.getString("ACCSTATUS"));

				accountType.setTypeId(rs.getInt("TYPEID"));
				accountType.setType(rs.getString("ACCTYPE"));

				account.setAccountId(rs.getInt("ACCOUNTID"));
				account.setBalance(rs.getFloat("BALANCE"));
				account.setStatus(accountStatus);
				account.setType(accountType);

				accounts.add(account);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		return accounts;
	}

	/*------------------------------------------------------------------------------------------------*/

	@Override
	public List<Account> getAccountsByUser(String firstName, String lastName) {
		List<Account> accounts = new ArrayList<>();

		try {
			connection = DAOUtilities.getConnection();
			String sql = "select * from vpdb_accounts\n"
					+ "left join vpdb_accountstatus on vpdb_accounts.status = vpdb_accountstatus.statusid\n"
					+ "left join vpdb_accounttype on vpdb_accounts.type = vpdb_accounttype.typeid\n"
					+ "left join vpdb_registries on vpdb_registries.idofaccount = vpdb_accounts.accountid\n"
					+ "left join vpdb_users on vpdb_users.userid = vpdb_registries.idofuser\n"
					+ "where vpdb_users.firstname = ?\n" + "and vpdb_users.lastname = ?";

			stmt = connection.prepareStatement(sql);
			stmt.setString(1, firstName);
			stmt.setString(2, lastName);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				Account account = new Account();
				AccountStatus accountStatus = new AccountStatus();
				AccountType accountType = new AccountType();

				accountStatus.setStatusId(rs.getInt("STATUSID"));
				accountStatus.setStatus(rs.getString("ACCSTATUS"));

				accountType.setTypeId(rs.getInt("TYPEID"));
				accountType.setType(rs.getString("ACCTYPE"));

				account.setAccountId(rs.getInt("ACCOUNTID"));
				account.setBalance(rs.getFloat("BALANCE"));
				account.setStatus(accountStatus);
				account.setType(accountType);

				accounts.add(account);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		return accounts;
	}

	/*------------------------------------------------------------------------------------------------*/
	
	@Override
	public void passTimeAccount(int months) {
			
		System.out.println("message: "+months+" months of interest has been accrued for all Savings Accounts");
	}

	/*------------------------------------------------------------------------------------------------*/

	private void closeResources() {
		try {
			if (stmt != null)
				stmt.close();
		} catch (SQLException e) {
			System.out.println("Could not close statement!");
			e.printStackTrace();
		}
		try {
			if (mystmt != null)
				mystmt.close();
		} catch (SQLException e) {
			System.out.println("Could not close statement!");
			e.printStackTrace();
		}
		try {
			if (connection != null)
				connection.close();
		} catch (SQLException e) {
			System.out.println("Could not close connection!");
			e.printStackTrace();
		}
	}


	/*------------------------------------------------------------------------------------------------*/
	
}
