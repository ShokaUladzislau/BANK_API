package com.VladProject.dao;

import java.util.List;

import com.VladProject.Models.Account;
import com.VladProject.Models.AccountStatus;
import com.VladProject.Models.AccountType;

public class TestAccountDAOImpl {

	public static void main(String[] args) {
		//getAllAccounts();
		//getAccountByAccountId();
		//getAllAccountsByStatus();
		getAllAccountsByUser();
		//createAccount();
		//updateAccount();
		//getAllAccounts();
		//withdrawAccount();
		//getAllAccounts();		
		//depositAccount();
		//getAllAccounts();
		//transferBetweenAccounts();
		//getAllAccounts();
		//passTimeAccount();
	}

	public static void getAllAccounts() {

		AccountDAOImpl accountDAOImpl = new AccountDAOImpl();

		List<Account> accounts = accountDAOImpl.getAccounts();

		for (Account account : accounts) {

			AccountStatus accountStatus = account.getStatus();
			AccountType accountType = account.getType();

			System.out.println(
					account.getAccountId() + " " + account.getBalance() + " " + accountStatus.getStatusId() + " " + accountStatus.getStatus() + " " + accountType.getTypeId() + " " + accountType.getType());
		}
	}

	public static void getAccountByAccountId() {

		AccountDAOImpl accountDAOImpl = new AccountDAOImpl();

		Account account = accountDAOImpl.getAccountByAccountId(1);
		AccountStatus accountStatus = account.getStatus();
		AccountType accountType = account.getType();

		System.out
				.println(account.getAccountId() + " " + account.getBalance() + " " + accountStatus.getStatusId() + " " + accountStatus.getStatus() + " " + accountType.getTypeId() + " " + accountType.getType());
	}

	public static void getAllAccountsByStatus() {

		AccountDAOImpl accountDAOImpl = new AccountDAOImpl();

		List<Account> accounts = accountDAOImpl.getAccountsByStatus(0);

		for (Account account : accounts) {

			AccountStatus accountStatus = account.getStatus();
			AccountType accountType = account.getType();

			System.out.println(
					account.getAccountId() + " " + account.getBalance() + " " + accountStatus.getStatusId() + " " + accountStatus.getStatus() + " " + accountType.getTypeId() + " " + accountType.getType());
		}
	}

	public static void getAllAccountsByUser() {

		AccountDAOImpl accountDAOImpl = new AccountDAOImpl();

		List<Account> accounts = accountDAOImpl.getAccountsByUser("testfirstname", "testlastname");

		for (Account account : accounts) {

			AccountStatus accountStatus = account.getStatus();
			AccountType accountType = account.getType();

			System.out.println(
					account.getAccountId() + " " + account.getBalance() + " " + accountStatus.getStatusId() + " " + accountStatus.getStatus() + " " + accountType.getTypeId() + " " + accountType.getType());
		}
	}
	
	public static void createAccount() {

		AccountDAOImpl accountDAOImpl = new AccountDAOImpl();

		Account account = accountDAOImpl.createAccount(4, 3.0, 1, 0);
		AccountStatus accountStatus = account.getStatus();
		AccountType accountType = account.getType();

		System.out
				.println(account.getAccountId() + " " + account.getBalance() + " " + accountStatus.getStatusId() + " " + accountStatus.getStatus() + " " + accountType.getTypeId() + " " + accountType.getType());
	}
	
	public static void updateAccount() {

		AccountDAOImpl accountDAOImpl = new AccountDAOImpl();

		Account account = accountDAOImpl.updateAccount(4, 6.0, 1, 0);
		AccountStatus accountStatus = account.getStatus();
		AccountType accountType = account.getType();

		System.out
				.println(account.getAccountId() + " " + account.getBalance() + " " + accountStatus.getStatusId() + " " + accountStatus.getStatus() + " " + accountType.getTypeId() + " " + accountType.getType());
	}

	public static void withdrawAccount() {

		AccountDAOImpl accountDAOImpl = new AccountDAOImpl();

		Account account = accountDAOImpl.withdrawAccount(4, 7);
		AccountStatus accountStatus = account.getStatus();
		AccountType accountType = account.getType();

		System.out
				.println(account.getAccountId() + " " + account.getBalance() + " " + accountStatus.getStatusId() + " " + accountStatus.getStatus() + " " + accountType.getTypeId() + " " + accountType.getType());
	}
	
	public static void depositAccount() {

		AccountDAOImpl accountDAOImpl = new AccountDAOImpl();

		Account account = accountDAOImpl.depositAccount(4, 2.5);
		AccountStatus accountStatus = account.getStatus();
		AccountType accountType = account.getType();

		System.out
				.println(account.getAccountId() + " " + account.getBalance() + " " + accountStatus.getStatusId() + " " + accountStatus.getStatus() + " " + accountType.getTypeId() + " " + accountType.getType());
	}

	public static void transferBetweenAccounts() {


		AccountDAOImpl accountDAOImpl = new AccountDAOImpl();

		List<Account> accounts = accountDAOImpl.transferBetweenAccounts(4, 3, 5);

		for (Account account : accounts) {

			AccountStatus accountStatus = account.getStatus();
			AccountType accountType = account.getType();

			System.out.println(
					account.getAccountId() + " " + account.getBalance() + " " + accountStatus.getStatusId() + " " + accountStatus.getStatus() + " " + accountType.getTypeId() + " " + accountType.getType());
		}
	}

	public static void passTimeAccount() {
		AccountDAOImpl accountDAOImpl = new AccountDAOImpl();
		accountDAOImpl.passTimeAccount(4);
	}
	

}
