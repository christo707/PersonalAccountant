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
import com.christo.servlets.daos.CashBookDao;
import com.christo.servlets.daos.ExpenseDao;
import com.christo.servlets.daos.ExpensesCategoriesDao;
import com.christo.servlets.daos.IncomeCategoriesDao;
import com.christo.servlets.daos.IncomeDao;
import com.christo.servlets.pojos.BankBookPojo;
import com.christo.servlets.pojos.CashBookPojo;
import com.christo.servlets.pojos.Expense;
import com.christo.servlets.pojos.ExpensesCategories;
import com.christo.servlets.pojos.Income;
import com.christo.servlets.pojos.IncomeCategories;
import com.christo.servlets.utilities.DateUtils;

/**
 * Servlet implementation class AddExpenseController
 */
@WebServlet("/AddExpenseController")
public class AddExpenseController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddExpenseController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int amount = 0;
		int catid = 0;
		String expense = request.getParameter("expense");
		if (expense == null) {
			expense = new String();
		}
		String category = request.getParameter("category");
		if (category == null) {
			category = new String();
		}
		else
		{
			catid = Integer.parseInt(category);
		}
		
		String am = request.getParameter("amount");
		if (am == null) {
			am = new String();
		}
		else
		{
			amount = Integer.parseInt(am);
		}
		
		String payby = request.getParameter("payby");
		if (payby == null) {
			payby = new String();
		}
		String remark = request.getParameter("remark");
		if (remark == null) {
			remark = new String();
		}
		String dt = request.getParameter("date");
		java.util.Date date = null;
		if (dt == null) {
			dt = new String();
		}
		else
		{
			date = DateUtils.convertDate(dt);
		}
		
		HttpSession session = request.getSession();
		Integer sessionid = (Integer) session.getAttribute("userid");
		
		ExpenseDao catDao = new ExpenseDao();
		
		if (payby.equals("Cash")) {
			CashBookPojo cat = new CashBookPojo(expense, date, amount, sessionid, "Pay");
			CashBookDao cashDao = new CashBookDao();
			cashDao.create(cat);
			Expense i = new Expense(expense, sessionid, catid , amount, date, payby, remark);
			catDao.create(i);
		} else if (payby.equals("Online")) {
			BankBookPojo cat = new BankBookPojo(expense, date, amount, sessionid, "Pay");
			BankBookDao bankDao = new BankBookDao();
			bankDao.create(cat);
			Expense i = new Expense(expense, sessionid, catid , amount, date, payby, remark);
			catDao.create(i);
		} 
		
		ArrayList<ExpensesCategories> catList = ExpensesCategoriesDao.findAllByUserId(sessionid);
		request.setAttribute("expenseCatList",catList);
		RequestDispatcher rd = request.getRequestDispatcher("AddExpense");
		rd.forward(request, response);

	}

}
