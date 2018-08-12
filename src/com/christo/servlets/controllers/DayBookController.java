package com.christo.servlets.controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.christo.servlets.daos.BankBookDao;
import com.christo.servlets.daos.ExpenseDao;
import com.christo.servlets.daos.IncomeDao;
import com.christo.servlets.pojos.BankBookPojo;
import com.christo.servlets.pojos.Expense;
import com.christo.servlets.pojos.Income;
import com.christo.servlets.utilities.DateUtils;

/**
 * Servlet implementation class DayBookController
 */
@WebServlet("/DayBookController")
public class DayBookController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DayBookController() {
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
		String dtf = request.getParameter("datefrom");
		java.util.Date date1 = null;
		if (dtf == null) {
			dtf = new String();
		} else {
			date1 = DateUtils.convertDate(dtf);
		}

		String dtt = request.getParameter("dateto");
		java.util.Date date2 = null;
		if (dtt == null) {
			dtt = new String();
		} else {
			date2 = DateUtils.convertDate(dtt);
		}

		HttpSession session = request.getSession();
		Integer sessionid = (Integer) session.getAttribute("userid");

		ArrayList<Income> incomeList = IncomeDao.findByDates(date1, date2, sessionid);
		request.setAttribute("incomeList", incomeList);
		ArrayList<Expense> expenseList = ExpenseDao.findByDates(date1, date2, sessionid);
		request.setAttribute("expenseList", expenseList);
		RequestDispatcher rd = request.getRequestDispatcher("DayBook");
		rd.forward(request, response);
	}

}
