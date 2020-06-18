package com.VladProject.dao;

import java.util.List;

import com.VladProject.Models.Role;
import com.VladProject.Models.User;

public class TestUserDAOImpl {

	public static void main(String[] args) {
		
		//getUserByUserId();
		//userLogin();
		//updateUserByUserId();
		//getAllUsersForAccount();
		//getUserByUserId();


	}

	public static void getUserByUserId() {
		UserDAOImpl userDaoImpl = new UserDAOImpl();
		

		User user = userDaoImpl.getUserByUserId(25);
		Role role = user.getRole();

		System.out.println(user.getUserId() + " " + user.getUsername() + " " + user.getPassword() + " "
				+ user.getFirstName() + " " + user.getLastName() + " " + user.getEmail() + " " + role.getRoleId() + " " + role.getRole());

	}
	
	public static void getAllUsers() {
		UserDAOImpl userDaoImpl = new UserDAOImpl();

		List<User> users = userDaoImpl.getAllUsers();

		for (User myuser : users) {
			Role role = myuser.getRole();

			System.out.println(myuser.getUserId() + " " + myuser.getUsername() + " " + myuser.getPassword() + " "
					+ myuser.getFirstName() + " " + myuser.getLastName() + " " + myuser.getEmail() + " "
					+ role.getRoleId() + " " + role.getRole());
		}

	}
	
	public static void getAllUsersForAccount() {
		UserDAOImpl userDaoImpl = new UserDAOImpl();

		List<User> users = userDaoImpl.getAllUsersForAccount(1);

		for (User myuser : users) {
			Role role = myuser.getRole();

			System.out.println(myuser.getUserId() + " " + myuser.getUsername() + " " + myuser.getPassword() + " "
					+ myuser.getFirstName() + " " + myuser.getLastName() + " " + myuser.getEmail() + " "
					+ role.getRoleId() + " " + role.getRole());
		}

	}
	
	public static void userLogin() {
		UserDAOImpl userDaoImpl = new UserDAOImpl();

		User loginuser = userDaoImpl.userLogin("testuser1", "testpass1");
		Role role = loginuser.getRole();


		System.out.println(loginuser.getUserId() + " " + loginuser.getUsername() + " " + loginuser.getPassword() + " "
				+ loginuser.getFirstName() + " " + loginuser.getLastName() + " " + loginuser.getEmail() + " "
				+ role.getRoleId() + " " + role.getRole());
	}
	
	public static void updateUserByUserId() {
		UserDAOImpl userDaoImpl = new UserDAOImpl();
		

		User user = userDaoImpl.updateUserByUserId(22, "vd1", "vd2", "VfirstName", "VlastName", "Vemail", 2);
		Role role = user.getRole();

		System.out.println(user.getUserId() + " " + user.getUsername() + " " + user.getPassword() + " "
				+ user.getFirstName() + " " + user.getLastName() + " " + user.getEmail() + " " + role.getRoleId() + " " + role.getRole());

	}

}
