package com.VladProject.Servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.VladProject.Models.Account;
import com.VladProject.Models.AccountStatus;
import com.VladProject.Models.AccountType;
import com.VladProject.Utilites.DAOUtilities;
import com.VladProject.dao.AccountDAO;


@WebServlet("/AddAccount")
public class AddAccountServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("addAccount.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		AccountDAO adao = DAOUtilities.getAccountDAO();

		Account account = new Account();
		AccountStatus status = new AccountStatus();
		AccountType type = new AccountType();
		
		status.setStatusId(Integer.parseInt(req.getParameter("statusId")));
		type.setTypeId(Integer.parseInt(req.getParameter("statusId")));
		
		account.setAccountId(Integer.parseInt(req.getParameter("accountId")));
		account.setBalance(Integer.parseInt(req.getParameter("balance")));
		account.setStatus(status);
		account.setType(type);

		boolean isSuccess = adao.addAccount(account);


		if (isSuccess) {
			System.out.println("New account added");
			resp.sendRedirect(req.getContextPath() + "/AdminView");
			
		} else {
			System.out.println("Problen with account added");
			req.getRequestDispatcher("admin.jsp").forward(req, resp);

		}
	}
}
