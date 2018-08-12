package com.christo.servlets.design;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SideBar
 */
@WebServlet("/SideBar")
public class SideBar extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SideBar() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.println("<div id=\"sidebar1\" class=\"sidebar\">");
		out.println("<ul>");
		out.println("<li>");
		out.println("<h2> Services </h2>");
		out.println("<ul>");
		out.println("<li><a href=\"AddIncomeController\"><strong>Add Income</strong></a></li>");
		out.println("<li><a href=\"AddExpenseController\"><strong>Add Expense</strong></a></li>");
		out.println("<li><a href=\"IncomeCategoriesController\"><strong>Income Categories</strong></a></li>");
		out.println("<li><a href=\"ExpenseCategoriesController\"><strong>Expense Categories</strong></a></li>");
		out.println("<li><a href=\"BankBook\"><strong>Bank Book</strong></a></li>");
		out.println("<li><a href=\"CashBook\"><strong>Cash Book</strong></a></li>");
		out.println("<li><a href=\"DayBook\"><strong>Day Book</strong></a></li>");
		out.println("<li><a href=\"BalanceSheet\"><strong>Balance Sheet</a></strong></li>");
		out.println("<li><a href=\"Logout\"><strong>Logout</a></strong></li>");
		out.println("</ul>");
		out.println("</li>");
		out.println("</ul>");
		out.println("</div>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
