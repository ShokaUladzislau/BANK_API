package com.VladProject.Servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.VladProject.Models.Account;
import com.VladProject.Models.Role;
import com.VladProject.Models.User;
import com.VladProject.Utilites.DAOUtilities;
import com.VladProject.dao.AccountDAO;
import com.VladProject.dao.UserDAO;

@WebServlet("/UserView")
public class UserViewServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String userId = request.getParameter("userId");


		UserDAO udao = DAOUtilities.getUserDAO();
		User user = udao.getUserByUserId(Integer.parseInt(userId));
		Role role = user.getRole();
		
		AccountDAO adao = DAOUtilities.getAccountDAO();
		List<Account> accountList = adao.getAccountsByUser(user.getFirstName(), user.getLastName());
		
		HttpSession session = request.getSession();
        session.setAttribute("user", user);
        session.setAttribute("role", role);
        session.setAttribute("accounts", accountList);		

		request.getRequestDispatcher("user.jsp").forward(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String userId = request.getParameter("userId");

		UserDAO udao = DAOUtilities.getUserDAO();
		User user = udao.getUserByUserId(Integer.parseInt(userId));
		Role role = user.getRole();
		
		AccountDAO adao = DAOUtilities.getAccountDAO();
		List<Account> accountList = adao.getAccountsByUser(user.getFirstName(), user.getLastName());

		HttpSession session = request.getSession();
        session.setAttribute("user", user);
        session.setAttribute("role", role);
        session.setAttribute("accounts", accountList);
        
		request.getRequestDispatcher("user.jsp").forward(request, response);
	}


}
