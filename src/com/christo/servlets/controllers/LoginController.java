package com.christo.servlets.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.christo.servlets.daos.UsersDao;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// get request parameters for userID and password
		String alert = new String();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println("name : " + username);
		System.out.println("name : " + password);

		if (username == null && password == null) {
			response.sendRedirect("LoginForm");
		}
		int uid = UsersDao.verify(username, password);
		System.out.println("Username : " + username);
		if (uid != -1) {
			HttpSession session = request.getSession();
			session.setAttribute("userid", new Integer(uid));
			session.setAttribute("username", username);
			session.setAttribute("password", password);
			response.sendRedirect("AddExpenseController");
		} else {
			response.sendRedirect("LoginForm");
		}
	}

}
