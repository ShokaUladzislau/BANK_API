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

@WebServlet("/UserUpdateByUser")
public class UserUpdateByUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userId = request.getParameter("userId");

		UserDAO udao = DAOUtilities.getUserDAO();
		User user = udao.getUserByUserId(Integer.parseInt(userId));

		if (user != null) {

			user = udao.updateUserByUserId(Integer.parseInt(request.getParameter("userId")),
					request.getParameter("username"), request.getParameter("password"),
					request.getParameter("firstName"), request.getParameter("lastName"), request.getParameter("email"),
					user.getRole().getRoleId());

			request.setAttribute("user", user);

			if (user != null) {

				response.sendRedirect("UserView?userId="+user.getUserId());
			} else {
				request.getRequestDispatcher("UserView").forward(request, response);
			}
		}

	}
}
