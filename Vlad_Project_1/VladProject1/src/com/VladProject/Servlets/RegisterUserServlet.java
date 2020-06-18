package com.VladProject.Servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.VladProject.Models.Role;
import com.VladProject.Models.User;
import com.VladProject.Utilites.DAOUtilities;
import com.VladProject.dao.UserDAO;


@WebServlet("/RegisterUser")
public class RegisterUserServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("registerUser.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		UserDAO udao = DAOUtilities.getUserDAO();

		User user = new User();
		Role role = new Role();
		
		role.setRoleId(0);

		user.setUsername(req.getParameter("username"));
		user.setPassword(req.getParameter("password"));
		user.setFirstName(req.getParameter("firstName"));
		user.setLastName(req.getParameter("lastName"));
		user.setEmail(req.getParameter("email"));
		user.setRole(role);

		boolean isSuccess = udao.addUser(user);


		if (isSuccess) {
			System.out.println("New user redistered");
			req.getRequestDispatcher("login.jsp").forward(req, resp);
			
		} else {
			System.out.println("Problen with user registration");
			req.getRequestDispatcher("uncorrectRegister.jsp").forward(req, resp);

		}
	}
}
