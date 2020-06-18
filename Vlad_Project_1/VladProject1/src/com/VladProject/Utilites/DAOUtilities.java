package com.VladProject.Utilites;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.VladProject.dao.AccountDAO;
import com.VladProject.dao.AccountDAOImpl;
import com.VladProject.dao.UserDAO;
import com.VladProject.dao.UserDAOImpl;

public class DAOUtilities {

	private static final String CONNECTION_USERNAME = "admin";
	private static final String CONNECTION_PASSWORD = "12345678";
	private static final String URL = "jdbc:oracle:thin:@database-2.c0rzi76acgyn.us-east-1.rds.amazonaws.com:1521:FIRSTDB";
	private static Connection connection;

	public static synchronized Connection getConnection() throws SQLException {
		if (connection == null) {
			try {
				Class.forName("oracle.jdbc.OracleDriver");
				System.out.println("Opening connection...");
			} catch (ClassNotFoundException e) {
				System.out.println("Could not register driver!");
				e.printStackTrace();
			}
			
			connection = DriverManager.getConnection(URL, CONNECTION_USERNAME, CONNECTION_PASSWORD);
		}

		// If connection was closed then retrieve a new connection
		if (connection.isClosed()) {
			System.out.println("Opening new connection...");
			connection = DriverManager.getConnection(URL, CONNECTION_USERNAME, CONNECTION_PASSWORD);
		}
		return connection;
	}

	public static UserDAO getUserDAO() {
		// TODO Auto-generated method stub
		return new UserDAOImpl();
	}

	public static AccountDAO getAccountDAO() {
		// TODO Auto-generated method stub
		return new AccountDAOImpl();
	}
}