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
import com.VladProject.Models.AccountStatus;
import com.VladProject.Models.AccountType;
import com.VladProject.Models.User;
import com.VladProject.Utilites.DAOUtilities;
import com.VladProject.dao.AccountDAO;
import com.VladProject.dao.UserDAO;


@WebServlet("/EmployeeViewAccountDetails")
public class EmployeeViewAccountDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String accountId = request.getParameter("accountId");
		
		AccountDAO adao = DAOUtilities.getAccountDAO();
		Account account = adao.getAccountByAccountId(Integer.parseInt(accountId));
		AccountStatus accountStatus = new AccountStatus();
		AccountType accountType = new AccountType();
		
		UserDAO udao = DAOUtilities.getUserDAO();
		List<User> userList = udao.getAllUsersForAccount(Integer.parseInt(accountId));
				
			
		HttpSession session = request.getSession();
        session.setAttribute("account", account);
        session.setAttribute("accountStatus", accountStatus);
        session.setAttribute("accountType", accountType);
        session.setAttribute("users", userList);


        getServletContext().getRequestDispatcher("/employeeAccountDetails.jsp").forward(request, response);

		
	}

}

