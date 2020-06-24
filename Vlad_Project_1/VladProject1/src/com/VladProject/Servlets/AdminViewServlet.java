package com.VladProject.Servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.VladProject.Models.Account;
import com.VladProject.Models.User;
import com.VladProject.Utilites.DAOUtilities;
import com.VladProject.dao.AccountDAO;
import com.VladProject.dao.UserDAO;

@WebServlet("/AdminView")
public class AdminViewServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		UserDAO udao = DAOUtilities.getUserDAO();
		List<User> userList = udao.getAllUsers();
		
		AccountDAO adao = DAOUtilities.getAccountDAO();
		List<Account> accountList = adao.getAccounts();

		request.getSession().setAttribute("accounts", accountList);
		request.getSession().setAttribute("users", userList);

		request.getRequestDispatcher("admin.jsp").forward(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		UserDAO udao = DAOUtilities.getUserDAO();
		List<User> userList = udao.getAllUsers();
		
		AccountDAO adao = DAOUtilities.getAccountDAO();
		List<Account> accountList = adao.getAccounts();

		request.getSession().setAttribute("accounts", accountList);
		request.getSession().setAttribute("users", userList);

		request.getRequestDispatcher("admin.jsp").forward(request, response);
	}

}
