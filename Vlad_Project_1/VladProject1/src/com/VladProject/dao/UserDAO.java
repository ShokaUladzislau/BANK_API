package com.VladProject.dao;

import java.util.List;

import com.VladProject.Models.User;

public interface UserDAO {
	
	public User getUserByUserId(int userId);
	public User userLogin(String username, String password);
	public User updateUserByUserId(int userId, String username, String password, String firstName, String lastName, String email, int numberOfRole);
	
	public List<User> getAllUsers();
	public List<User> getAllUsersForEmployee();
	public List<User> getAllUsersForAccount(int accountId);
	
	public boolean deleteUserByUserId(int userId);
	public boolean addUser(User user);

}
