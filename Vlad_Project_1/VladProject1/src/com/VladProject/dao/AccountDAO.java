package com.VladProject.dao;

import java.util.List;

import com.VladProject.Models.Account;


public interface AccountDAO {
	
	public Account getAccountByAccountId(int accountId);
	public Account createAccount(int accountId, double balance, int status, int type);
	public Account updateAccount(int accountId, double balance, int status, int type);
	public Account withdrawAccount(int accountId, double balance);
	public Account depositAccount(int accountId, double balance);
	
	public List<Account> getAccounts();
	public List<Account> getAccountsByStatus(int status);
	public List<Account> getAccountsByUser(String firstName, String lastName);
	public List<Account> transferBetweenAccounts(int accountId1, int accountId2, double balance);
	
	public boolean addAccount(Account account);

	public void passTimeAccount(int months);
	
}
