package com.VladProject.Utilites;

import java.sql.Connection;
import java.sql.SQLException;

public class TestConnect {
	public static void main(String[] args) {
		TestMyConnect();
	}
	
	public static void TestMyConnect ()
	{
		Connection conn = null;
		try {
			conn = DAOUtilities.getConnection();
			System.out.println("Sucsess!");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(conn);
			e.printStackTrace();
		}
	}

}
