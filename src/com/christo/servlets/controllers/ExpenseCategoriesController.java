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

import com.christo.servlets.daos.ExpensesCategoriesDao;
import com.christo.servlets.daos.IncomeCategoriesDao;
import com.christo.servlets.pojos.ExpensesCategories;
import com.christo.servlets.pojos.IncomeCategories;

/**
 * Servlet implementation class ExpenseCategoriesController
 */
@WebServlet("/ExpenseCategoriesController")
public class ExpenseCategoriesController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExpenseCategoriesController() {
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
		int categoryId = 0;
		if (request.getParameter("categoryid") != null && request.getParameter("categoryid").trim().length()>0)
			categoryId = Integer.parseInt(request.getParameter("categoryid"));
		String categoryName = request.getParameter("catname");
		if (categoryName == null) {
			categoryName = new String();
		}
		String categoryDetails = request.getParameter("catdetails");
		if (categoryDetails == null) {
			categoryDetails = new String();
		}
		String operation = request.getParameter("operation");
		if (operation == null) {
			operation = new String();
		}
		
		HttpSession session = request.getSession();
		Integer sessionid = (Integer) session.getAttribute("userid");
		
		ExpensesCategoriesDao catDao = new ExpensesCategoriesDao();

		if (operation.equals("create")) {
			ExpensesCategories cat = new ExpensesCategories(categoryName, categoryDetails, sessionid);
			catDao.create(cat);
		} else if (operation.equals("edit")) {
			ExpensesCategories cat = new ExpensesCategories(categoryId, categoryName, categoryDetails, sessionid);
			catDao.edit(cat);
		} else if (operation.equals("remove")) {
			catDao.remove(categoryId);
		} 

		ArrayList<ExpensesCategories> catList = catDao.findAllByUserId(sessionid);
		request.setAttribute("expCatList", catList);
		RequestDispatcher rd = request.getRequestDispatcher("ExpenseCat");
		rd.forward(request, response);

	}

}
