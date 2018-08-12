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
 * Servlet implementation class RegisterationConfirmation
 */
@WebServlet("/RegisterationConfirmation")
public class RegisterationConfirmation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterationConfirmation() {
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
		out.println("<div align=\"center\">");
		out.println("<br><font size=\"4\" color=\"black\">You have successfully Registered New User. Click Here to </font><a href=\"Login\"><font size=\"4\" color=\"Green\">Login.</font></a>");
		out.println("</div>");
		out.println("</strong> </p>");
		out.println("<div style=\"clear: both;\">&nbsp;</div>");
		out.println("<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>");
		rd = request.getRequestDispatcher("Footer");
		 rd.include(request, response);
		out.println("</body>");
		out.println("</html>");
	}

}
