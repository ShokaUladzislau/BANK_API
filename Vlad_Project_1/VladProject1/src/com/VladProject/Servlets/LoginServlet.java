package com.VladProject.Servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.VladProject.Models.Role;
import com.VladProject.Models.User;
import com.VladProject.Utilites.DAOUtilities;
import com.VladProject.dao.UserDAO;

@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		UserDAO udao = DAOUtilities.getUserDAO();
		User user = udao.userLogin(username, password);

		RequestDispatcher rd = request.getRequestDispatcher("login.jsp");

		if (user != null) {
			System.out.println("Log IN !!!");

			Role role = user.getRole();

			if (role.getRoleId() == 0 || role.getRoleId() == 1) {
				System.out.println("You log in as Client");
				rd = request.getRequestDispatcher("/UserView?userId="+user.getUserId());


			} else if (role.getRoleId() == 2) {
				System.out.println("You log in as Employee");
				rd = request.getRequestDispatcher("/EmployeeView");

			} else if (role.getRoleId() == 3) {
				
				System.out.println("You log in as Admin");
				rd = request.getRequestDispatcher("/AdminView");
				
			}
			request.setAttribute("user", user);

		} else {
			rd = request.getRequestDispatcher("relogin.jsp"); // else, we send the user BACK to the login page...
		}
		rd.forward(request, response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		UserDAO udao = DAOUtilities.getUserDAO();
		User user = udao.userLogin(username, password);

		RequestDispatcher rd = request.getRequestDispatcher("login.jsp");

		if (user != null) {
			System.out.println("Log IN !!!");

			Role role = user.getRole();

			if (role.getRoleId() == 0) {
				System.out.println("You log in as Client");

			} else if (role.getRoleId() == 1) {
				System.out.println("You log in as Client");

			} else if (role.getRoleId() == 2) {
				System.out.println("You log in as Employee");
				rd = request.getRequestDispatcher("/EmployeeView");

			} else if (role.getRoleId() == 3) {
				
				System.out.println("You log in as Admin");
				rd = request.getRequestDispatcher("/AdminView");
				
			}
			request.setAttribute("user", user);

		} else {
			rd = request.getRequestDispatcher("relogin.jsp"); // else, we send the user BACK to the login page...
		}
		rd.forward(request, response);
	}

}
