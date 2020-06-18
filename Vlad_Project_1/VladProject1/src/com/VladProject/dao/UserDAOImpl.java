package com.VladProject.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.VladProject.Models.Role;
import com.VladProject.Models.User;
import com.VladProject.Utilites.DAOUtilities;

public class UserDAOImpl implements UserDAO {

	Connection connection = null; // Our connection to the database
	PreparedStatement stmt = null; // We use prepared statements to help protect against SQL injection
	Statement mystmt = null; // We use this statements to help update SQL

	/*------------------------------------------------------------------------------------------------*/

	@Override
	public User getUserByUserId(int userId) {
		User user = null;
		Role role = null;

		try {
			connection = DAOUtilities.getConnection();
			String sql = "select * from vpdb_users\n" + "left join vpdb_roles on vpdb_users.role = vpdb_roles.roleid\n"
					+ "where userid = ?";

			stmt = connection.prepareStatement(sql);

			stmt.setInt(1, userId);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				user = new User();
				role = new Role();

				role.setRoleId(rs.getInt("ROLEID"));
				role.setRole(rs.getString("ROLENAME"));

				user.setUserId(rs.getInt("USERID"));
				user.setUsername(rs.getString("USERNAME"));
				user.setPassword(rs.getString("PASSWORD"));
				user.setFirstName(rs.getString("FIRSTNAME"));
				user.setLastName(rs.getString("LASTNAME"));
				user.setEmail(rs.getString("EMAIL"));
				user.setRole(role);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		return user;
	}

	/*------------------------------------------------------------------------------------------------*/

	@Override
	public User userLogin(String username, String password) {
		User user = null;
		Role role = null;

		try {
			connection = DAOUtilities.getConnection();
			String sql = "select * from vpdb_users\n" + "left join vpdb_roles on vpdb_users.role = vpdb_roles.roleid\n"
					+ "where username = ? and password = ?";

			stmt = connection.prepareStatement(sql);

			stmt.setString(1, username);
			stmt.setString(2, password);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				user = new User();
				role = new Role();

				role.setRoleId(rs.getInt("ROLEID"));
				role.setRole(rs.getString("ROLENAME"));

				user.setUserId(rs.getInt("USERID"));
				user.setUsername(rs.getString("USERNAME"));
				user.setPassword(rs.getString("PASSWORD"));
				user.setFirstName(rs.getString("FIRSTNAME"));
				user.setLastName(rs.getString("LASTNAME"));
				user.setEmail(rs.getString("EMAIL"));
				user.setRole(role);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		if (user != null) {
			return user;
		} else {
			String logmessage = "message :  Invalid Credentials";
			System.out.println(logmessage);
			return null;
		}

	}

	/*------------------------------------------------------------------------------------------------*/

	public User updateUserByUserId(int userId, String username, String password, String firstName, String lastName,
			String email, int numberOfRole) {
		User user = null;
		
		try {
			
			connection = DAOUtilities.getConnection();
			
			System.out.println("connection" + connection);
			
			String sql = "update \n" + 
					"(select vpdb_users.username, vpdb_users.password, vpdb_users.firstname, vpdb_users.lastname, vpdb_users.email, vpdb_users.role, vpdb_roles.roleid, vpdb_roles.rolename \n" + 
					"from vpdb_users \n" + 
					"left join vpdb_roles on vpdb_users.role = vpdb_roles.roleid \n" + 
					"where vpdb_users.userid = "+userId+") \n" + 
					"\n" + 
					"users \n" + 
					"\n" + 
					"set \n" + 
					"users.username = '"+username+"',\n" + 
					"users.password = '"+password+"', \n" + 
					"users.firstname = '"+firstName+"', \n" + 
					"users.lastname = '"+lastName+"',\n" + 
					"users.email = '"+email+"',\n" + 
					"users.role = "+numberOfRole+"";

			mystmt = connection.createStatement();			
			
			mystmt.executeUpdate(sql);
			mystmt.close();
			connection.close();

			user = getUserByUserId(userId);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		
		return user;
	}

	/*------------------------------------------------------------------------------------------------*/
	
	@Override
	public List<User> getAllUsers() {
		List<User> users = new ArrayList<>();

		try {
			connection = DAOUtilities.getConnection();
			String sql = "select * from vpdb_users\n" + "left join vpdb_roles on vpdb_users.role = vpdb_roles.roleid";

			stmt = connection.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				User user = new User();
				Role role = new Role();

				role.setRoleId(rs.getInt("ROLEID"));
				role.setRole(rs.getString("ROLENAME"));

				user.setUserId(rs.getInt("USERID"));
				user.setUsername(rs.getString("USERNAME"));
				user.setPassword(rs.getString("PASSWORD"));
				user.setFirstName(rs.getString("FIRSTNAME"));
				user.setLastName(rs.getString("LASTNAME"));
				user.setEmail(rs.getString("EMAIL"));
				user.setRole(role);

				users.add(user);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		return users;

	}
	
	/*------------------------------------------------------------------------------------------------*/

	@Override
	public List<User> getAllUsersForAccount(int accountId) {
		List<User> users = new ArrayList<>();

		try {
			connection = DAOUtilities.getConnection();
			String sql = "select * from vpdb_users\n" + 
					"left join vpdb_roles on vpdb_users.role = vpdb_roles.roleid\n" + 
					"left join vpdb_registries on vpdb_users.userid = vpdb_registries.idofuser \n" + 
					"left join vpdb_accounts on vpdb_accounts.accountid = vpdb_registries.idofaccount\n" + 
					"where vpdb_registries.idofaccount IS NOT NULL\n" + 
					"and vpdb_registries.idofuser IS NOT NULL\n" + 
					"and vpdb_accounts.accountid = ?";
			
			stmt = connection.prepareStatement(sql);

			stmt.setInt(1, accountId);

			ResultSet rs = stmt.executeQuery();


			while (rs.next()) {

				User user = new User();
				Role role = new Role();

				role.setRoleId(rs.getInt("ROLEID"));
				role.setRole(rs.getString("ROLENAME"));

				user.setUserId(rs.getInt("USERID"));
				user.setUsername(rs.getString("USERNAME"));
				user.setPassword(rs.getString("PASSWORD"));
				user.setFirstName(rs.getString("FIRSTNAME"));
				user.setLastName(rs.getString("LASTNAME"));
				user.setEmail(rs.getString("EMAIL"));
				user.setRole(role);

				users.add(user);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		return users;

	}
	
	/*------------------------------------------------------------------------------------------------*/

	@Override
	public List<User> getAllUsersForEmployee() {
		List<User> users = new ArrayList<>();

		try {
			connection = DAOUtilities.getConnection();
			String sql = "select * from vpdb_users\n" + "left join vpdb_roles on vpdb_users.role = vpdb_roles.roleid where vpdb_roles.roleid < 2";

			stmt = connection.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				User user = new User();
				Role role = new Role();

				role.setRoleId(rs.getInt("ROLEID"));
				role.setRole(rs.getString("ROLENAME"));

				user.setUserId(rs.getInt("USERID"));
				user.setUsername(rs.getString("USERNAME"));
				user.setPassword(rs.getString("PASSWORD"));
				user.setFirstName(rs.getString("FIRSTNAME"));
				user.setLastName(rs.getString("LASTNAME"));
				user.setEmail(rs.getString("EMAIL"));
				user.setRole(role);

				users.add(user);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		return users;

	}

	/*------------------------------------------------------------------------------------------------*/
	
	@Override
	public boolean addUser(User user) {
		
		try {
			connection = DAOUtilities.getConnection();
			String sql = "insert into vpdb_users\n" + 
					"(username, password, firstname, lastname, email, role)\n" + 
					"VALUES (?, ?, ?, ?, ?, ?)"; 
			stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, user.getUsername());
			stmt.setString(2, user.getPassword());
			stmt.setString(3, user.getFirstName());
			stmt.setString(4, user.getLastName());
			stmt.setString(5, user.getEmail());
			stmt.setInt(6, user.getRole().getRoleId());
	
			if (stmt.executeUpdate() != 0)
				return true;
			else
				return false;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			closeResources();
		}
	}
	
	/*------------------------------------------------------------------------------------------------*/
	
	@Override
	public boolean deleteUserByUserId(int userId) {
		try {
			connection = DAOUtilities.getConnection();
			String sql = "DELETE from vpdb_users WHERE userId=?";
			stmt = connection.prepareStatement(sql);
			
			stmt.setInt(1, userId);

			if (stmt.executeUpdate() != 0)
				return true;
			else
				return false;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			closeResources();
		}
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
