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


@WebServlet("/DeleteUser")
public class DeleteUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		boolean isSuccess= false;
		
		UserDAO udao = DAOUtilities.getUserDAO();
		User user = udao.getUserByUserId(Integer.parseInt(request.getParameter("userId")));
		
		if(user != null){
			
			isSuccess = udao.deleteUserByUserId(Integer.parseInt(request.getParameter("userId")));
		}else {
			isSuccess = false;
		}
		
		if(isSuccess){			
			System.out.println("User deleted");
			response.sendRedirect(request.getContextPath() + "/AdminView");
		}else {			
			System.out.println("Problem with deleting");
			response.sendRedirect(request.getContextPath() + "/AdminView");
		}
	}	
}
