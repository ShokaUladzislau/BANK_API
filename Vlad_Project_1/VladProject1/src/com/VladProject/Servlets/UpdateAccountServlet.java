package com.VladProject.Servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.VladProject.Models.Account;
import com.VladProject.Utilites.DAOUtilities;
import com.VladProject.dao.AccountDAO;

@WebServlet("/UpdateAccount")
public class UpdateAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String accountId = request.getParameter("accountId");

		AccountDAO adao = DAOUtilities.getAccountDAO();
		Account account = adao.getAccountByAccountId(Integer.parseInt(accountId));

		if (account != null) {

			account = adao.updateAccount(Integer.parseInt(request.getParameter("accountId")),
					Double.parseDouble(request.getParameter("balance")),
					Integer.parseInt(request.getParameter("statusId")), Integer.parseInt(request.getParameter("typeId")));

			request.setAttribute("account", account);

			if (account != null) {

				System.out.println("True");

				response.sendRedirect("AdminViewAccountDetails?accountId=" +accountId);
			} else {

				System.out.println("false");

				request.getRequestDispatcher("AdminViewAccountDetails?accountId="+accountId).forward(request, response);
			}
		}

	}
}
