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
import javax.servlet.http.HttpSession;

import com.christo.servlets.daos.IncomeCategoriesDao;
import com.christo.servlets.pojos.IncomeCategories;

/**
 * Servlet implementation class AddIncome
 */
@WebServlet("/AddIncome")
public class AddIncome extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddIncome() {
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
		
		out.println("<form name=\"AddIncome\" action=\"AddIncomeController\">");
		out.println("<table cellspacing=\"7\" width=\"100%\">");
		out.println("<tr>");
		out.println(
				"<th align = \"center\" colspan=\"3\" bgcolor=\"black\"><font color=\"white\"> Add Income</font></th>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td> Income* :</td>");
		out.println("<td colspan=\"2\"><input type=\"text\" required name=\"income\" value=\"\"> </td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td> Category* :</td>");
		
		ArrayList<IncomeCategories> catList = (ArrayList<IncomeCategories>) request.getAttribute("incomeCatList");
		out.println("<td colspan=\"2\"><select name=\"category\">");
		for (IncomeCategories cat : catList) {
			out.println("<option value=\"" + cat.getInc_catid() + "\">" + cat.getInc_catname() + "</option>");
		}
		out.println("<br>");
		out.println("</td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td> Amount* :</td>");
		out.println("<td colspan=\"2\"><input type=\"text\" required name=\"amount\" value=\"0.00\"> </td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td> Receive By* :</td>");
		out.println("<td colspan=\"2\"><select name=\"receiveby\" > ");
		out.println("<option value=\"Online\" selected> Online </option>");
		out.println("<option value=\"Cash\" > Cash </option>");
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
