package com.christo.servlets.main;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.christo.servlets.pojos.ExpensesCategories;
import com.christo.servlets.pojos.IncomeCategories;

/**
 * Servlet implementation class AddExpense
 */
@WebServlet("/AddExpense")
public class AddExpense extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddExpense() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		RequestDispatcher rd = null;
		out.println(
				"<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">");
		out.println("<html xmlns=\"http://www.w3.org/1999/xhtml\">");
		out.println("<head>");
		out.println("<meta http-equiv=\"content-type\" content=\"text/html; charset=utf-8\" />");
		out.println("<title>PERSONAL ACCOUNTANT</title>");
		out.println("<meta name=\"keywords\" content=\"\" />");
		out.println("<meta name=\"Premium Series\" content=\"\" />");
		out.println(
				"<link href=\"/CommonProject/Template/default.css\" rel=\"stylesheet\" type=\"text/css\" media=\"screen\" />");
		out.println("</head>");
		out.println("<body>");
		rd = request.getRequestDispatcher("Header");
		rd.include(request, response);
		out.println("<div id=\"wrapper\">");
		out.println("<!-- start page -->");
		out.println("<div id=\"page\">");
		out.println("<div id=\"page-bg\">");
		rd = request.getRequestDispatcher("SideBar");
		rd.include(request, response);
		out.println("<div id=\"content\">");
		out.println("<div class=\"post\">");
		//out.println("<h1 class=\"title\"><a href=\"#\">Cash Book</a></h1>");
		//out.println("<p class=\"byline\"><small>cash book </small></p>");
		out.println("<div class=\"entry\">");
		out.println("<p><strong>");
		
		out.println("<form name=\"AddExpense\" action=\"AddExpenseController\">");
		out.println("<table cellspacing=\"7\" width=\"100%\">");
		out.println("<tr>");
		out.println(
				"<th align = \"center\" colspan=\"3\" bgcolor=\"black\"><font color=\"white\"> Add Expense</font></th>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td> Expense* :</td>");
		out.println("<td colspan=\"2\"><input type=\"text\" required name=\"expense\" value=\"\"> </td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td> Category* :</td>");
		
		ArrayList<ExpensesCategories> catList = (ArrayList<ExpensesCategories>) request.getAttribute("expenseCatList");
		out.println("<td colspan=\"2\"><select name=\"category\">");
		for (ExpensesCategories cat : catList) {
			out.println("<option value=\"" + cat.getExp_catid() + "\">" + cat.getExp_catname() + "</option>");
		}
		out.println("<br>");
		out.println("</td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td> Amount* :</td>");
		out.println("<td colspan=\"2\"><input type=\"text\" required name=\"amount\" value=\"0.00\"> </td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td> Pay By* :</td>");
		out.println("<td colspan=\"2\"><select name=\"payby\" > ");
		out.println("<option value=\"Cash\" selected> Cash </option>");
		out.println("<option value=\"Online\" > Online </option>");
		out.println("</td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td> Remark* :</td>");
		out.println("<td colspan=\"2\"><input type=\"text\" required name=\"remark\" value=\"\"> </td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td> Date* :</td>");
		out.println("<td colspan=\"2\"><input type=\"date\" required name=\"date\" > </td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td> <input type=\"reset\" value=\"Reset\"></td>");
		out.println("<td colspan=\"2\"><input type=\"submit\" value=\"Add Expense\"></td>");
		out.println("</tr>");
		out.println("</table>");
		out.println("</form>");
	
		out.println("</strong> </p>");
		out.println("</div>");
		out.println("</div>");
		out.println("</div>");
		out.println("<div style=\"clear: both;\">&nbsp;</div>");
		out.println("</div>");
		out.println("</div><br><br><br><br>");
		rd = request.getRequestDispatcher("Footer");
		rd.include(request, response);
		out.println("</body>");
		out.println("</html>");
	}

}
