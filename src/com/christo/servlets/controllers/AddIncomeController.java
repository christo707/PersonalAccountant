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
import com.christo.servlets.daos.IncomeCategoriesDao;
import com.christo.servlets.daos.IncomeDao;
import com.christo.servlets.pojos.BankBookPojo;
import com.christo.servlets.pojos.CashBookPojo;
import com.christo.servlets.pojos.Income;
import com.christo.servlets.pojos.IncomeCategories;
import com.christo.servlets.utilities.DateUtils;

/**
 * Servlet implementation class AddIncomeController
 */
@WebServlet("/AddIncomeController")
public class AddIncomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddIncomeController() {
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
		String income = request.getParameter("income");
		if (income == null) {
			income = new String();
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
		
		String receiveby = request.getParameter("receiveby");
		if (receiveby == null) {
			receiveby = new String();
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
		
		IncomeDao catDao = new IncomeDao();
		
		if (receiveby.equals("Cash")) {
			CashBookPojo cat = new CashBookPojo(income, date, amount, sessionid, "Receive");
			CashBookDao cashDao = new CashBookDao();
			cashDao.create(cat);
			Income i = new Income(income, sessionid, catid , amount, date, receiveby, remark);
			catDao.create(i);
		} else if (receiveby.equals("Online")) {
			BankBookPojo cat = new BankBookPojo(income, date, amount, sessionid, "Receive");
			BankBookDao bankDao = new BankBookDao();
			bankDao.create(cat);
			Income i = new Income(income, sessionid, catid , amount, date, receiveby, remark);
			catDao.create(i);
		} 
		
		ArrayList<IncomeCategories> catList = IncomeCategoriesDao.findAllByUserId(sessionid);
		request.setAttribute("incomeCatList",catList);
		RequestDispatcher rd = request.getRequestDispatcher("AddIncome");
		rd.forward(request, response);

	}

}
