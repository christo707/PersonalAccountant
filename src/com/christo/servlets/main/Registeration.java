package com.christo.servlets.main;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UserProfile
 */
@WebServlet("/Registeration")
public class Registeration extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Registeration() {
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
		out.println("<div id=\"header\">");
		out.println("<div id=\"logo\">");
		out.println("<h1><a href=\"#\"><span>Personal Accountant</span></a></h1>");
		out.println("<p> Designed By CHRISTOPHER MICHAEL ROZARIO</p>");
		out.println("</div>");
		out.println("</div>");
//		out.println("<div id=\"wrapper\">");
//		out.println("<!-- start page -->");
//		out.println("<div id=\"page\">");
//		out.println("<div id=\"page-bg\">");
//		out.println("<div id=\"content\">");
//		out.println("<div class=\"post\">");
//		//out.println("<h1 class=\"title\"><a href=\"#\">Cash Book</a></h1>");
//		//out.println("<p class=\"byline\"><small>cash book </small></p>");
//		out.println("<div class=\"entry\">");
		out.println("<p><strong>");
		out.println("<div align=\"center\">");
		out.println("<form name=\"UserProfile\" action=\"RegisterationController\">");
		out.println("<table cellspacing=\"7\" width=\"600\">");
		out.println("<tr height=\"30\">");
		out.println(
				"<th align = \"center\" colspan=\"3\" bgcolor=\"black\"><font color=\"white\"> Register User </font></th>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td> User Name* :</td>");
		out.println("<td colspan=\"2\"><input type=\"text\" name=\"username\" value=\"\" required> </td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td> Password* :</td>");
		out.println("<td colspan=\"2\"><input type=\"password\" name=\"password\" value=\"\" required> </td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td> Full Name* :</td>");
		out.println("<td colspan=\"2\"><input type=\"text\" name=\"name\" value=\"\" required> </td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td> Address* :</td>");
		out.println("<td colspan=\"2\"><input type=\"text\" name=\"address\" value=\"\" required> </td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td> Mobile No. * :</td>");
		out.println("<td colspan=\"2\"><input type=\"text\" name=\"mobile\" value=\"\" pattern=\"[0-9]{10}\" required> </td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td> Email* :</td>");
		out.println("<td colspan=\"2\"><input type=\"email\" name=\"email\" value=\"\" required> </td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td> <input type=\"reset\" value=\"Reset\"></td>");
		out.println("<td colspan=\"2\"><input type=\"submit\" value=\"Register User\"></td>");
		out.println("</tr>");
		out.println("</table>");
		out.println("</form>");
		out.println("</div>");
		out.println("</strong> </p>");
//		out.println("</div>");
//		out.println("</div>");
		out.println("<div style=\"clear: both;\">&nbsp;</div>");
//		out.println("</div>");
		out.println("<br><br><br><br><br><br><br><br>");
		rd = request.getRequestDispatcher("Footer");
		rd.include(request, response);
		out.println("</body>");
		out.println("</html>");
	}

}
