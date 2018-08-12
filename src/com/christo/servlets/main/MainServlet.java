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
 * Servlet implementation class MainServlet
 */
@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MainServlet() {
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
		out.println("<div class=\"flower\"></div>");
		out.println("<div class=\"post\">");
		out.println("<h1 class=\"title\"><a href=\"#\">Cash Book</a></h1>");
		out.println("<p class=\"byline\"><small>cash book </small></p>");
		out.println("<div class=\"entry\">");
		out.println("<p><strong>Cash book</strong> </p>");
		out.println("<p>Welcome to Cash Book.<br><br><br><br><br><br><br><br><br><br><br><br><br><br> </p>");
		out.println("</div>");
		out.println("</div>");
		out.println("</div>");
		out.println("<div style=\"clear: both;\">&nbsp;</div>");
		out.println("</div>");
		out.println("</div>");
		rd = request.getRequestDispatcher("Footer");
		rd.include(request, response);
		out.println("</body>");
		out.println("</html>");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
