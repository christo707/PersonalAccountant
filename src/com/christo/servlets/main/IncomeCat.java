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

import com.christo.servlets.pojos.IncomeCategories;

/**
 * Servlet implementation class IncomeCat
 */
@WebServlet("/IncomeCat")
public class IncomeCat extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public IncomeCat() {
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
		PrintWriter out = response.getWriter();
		RequestDispatcher rd = null;
		out.println(
				"<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">");
		out.println("<html xmlns=\"http://www.w3.org/1999/xhtml\">");
		out.println("<head>");
		out.println("<meta http-equiv=\"content-type\" content=\"text/html; charset=utf-8\" />");
		out.println("<script>");
		out.println("function del(productId) {");
		out.println("document.getElementById(\"categoryid\").value = productId;");
		out.println("document.getElementById(\"operation\").value = 'remove';");
		out.println("document.IncomeCat.submit();");
		out.println("}");
		out.println("function mod(catid, catname, catdetails) {");
		out.println("document.getElementById(\"categoryid\").value = catid;");
		out.println("document.getElementById(\"catname\").value = catname;");
		out.println("document.getElementById(\"catdetails\").value = catdetails;");
		out.println("document.getElementById(\"add\").value = 'Save!';");
		out.println("document.getElementById(\"operation\").value = 'edit';");
		out.println("}");
		out.println("</script>");

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
		// out.println("<h1 class=\"title\"><a href=\"#\">Cash Book</a></h1>");
		// out.println("<p class=\"byline\"><small>cash book </small></p>");
		out.println("<div class=\"entry\">");
		out.println("<p><strong>");
		out.println("<div align=\"center\">");
		out.println("<form name=\"IncomeCat\" action=\"IncomeCategoriesController\">");
		out.println("<table cellspacing=\"5\" width=\"100%\">");
		out.println("<tr>");
		out.println(
				"<th align = \"center\" colspan=\"3\" bgcolor=\"black\"><font color=\"white\"> Income Categories</font></th>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td> Category Name* :</td>");
		out.println("<td colspan=\"2\"><input type=\"text\" required name=\"catname\" id=\"catname\" value=\"\"> </td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td> Category Details* :</td>");
		out.println("<td colspan=\"2\"><input type=\"text\" required height=\"30\" name=\"catdetails\" id=\"catdetails\" value=\"\"> </td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td> <input type=\"reset\" value=\"Reset\"></td>");
		out.println("<td colspan=\"2\"><input type=\"submit\" value=\"Add Category\" id=\"add\"></td>");
		out.println("</tr>");
		out.println("<input name=\"operation\" id=\"operation\"  value=\"create\" type=\"hidden\">");
		out.println("<input name=\"categoryid\" id=\"categoryid\"   type=\"hidden\">");
		out.println("</form>");
		out.println("</table><br>");
		out.println("<table  cellspacing=\"1\" width = \"100%\" border=\"1px solid\" bordercolor = \"black\">");
		out.println("<tr bgcolor=\"black\"><font color=\"white\">");
		out.println("<th> <font color=\"white\">Category Name </font></th>");
		out.println("<th colspan = \"2\"> <font color=\"white\">Category Details </font></th>");
		out.println("<th> <font color=\"white\">Edit </font></th>");
		out.println("<th> <font color=\"white\">Delete </font></th>");
		out.println("</tr>");
		
		ArrayList<IncomeCategories> catList = (ArrayList<IncomeCategories>) request.getAttribute("incCatList");
		for (IncomeCategories cat : catList) {
			out.println("<tr>");
			out.println("<td colspan = \"2\">" + cat.getInc_catname() + "</td>");
			out.println("<td>" + cat.getInc_catdetails() + "</td>");
			out.println(
					"<td ><input  name=\"edit\" id=\"edit\" value=\"Edit!\" type=\"button\" "
					+ "onclick=\"mod('"+cat.getInc_catid()+"','"+cat.getInc_catname()+"','"+cat.getInc_catdetails()+"');\"></td>");
			out.println(
					"<td ><input name=\"delete\" id=\"delete\" value=\"Delete!\" type=\"button\" onclick=\"del('"+cat.getInc_catid()+"');\" ></td>");
			out.println("</tr>");
		}
	
		
		out.println("</table>");

		out.println("</div>");
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
