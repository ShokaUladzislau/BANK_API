package com.VladProject.Servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.VladProject.Models.User;
import com.VladProject.Utilites.DAOUtilities;
import com.VladProject.dao.UserDAO;

@WebServlet("/UpToPemium")
public class UpToPemiumServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userId = request.getParameter("userId");

		UserDAO udao = DAOUtilities.getUserDAO();
		User user = udao.getUserByUserId(Integer.parseInt(userId));

		if (user != null) {

			if (user.getRole().getRoleId() == 0) {

				user = udao.updateUserByUserId((user.getUserId()), user.getUsername(), user.getPassword(),
						user.getFirstName(), user.getLastName(), user.getEmail(), 1);
				System.out.println("User upgraded to Premium Status!!!");
			} else if (user.getRole().getRoleId() == 1) {
				System.out.println("You already have Premium status!!!");
			} else if (user.getRole().getRoleId() == 2) {
				System.out.println("You are Employee!!!");
			} else if (user.getRole().getRoleId() == 3) {
				System.out.println("You are Admin!!!");
			} else {
				System.out.println("Unknown Role !!!");
			}

			request.setAttribute("user", user);

			if (user != null) {

				response.sendRedirect("UserView?userId=" + user.getUserId());
			} else {
				request.getRequestDispatcher("UserView").forward(request, response);
			}
		}
	}
}
