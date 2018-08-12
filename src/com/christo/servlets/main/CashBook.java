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

import com.christo.servlets.pojos.BankBookPojo;
import com.christo.servlets.pojos.CashBookPojo;

/**
 * Servlet implementation class CashBook
 */
@WebServlet("/CashBook")
public class CashBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CashBook() {
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
		int total = 0;
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
		// out.println("<h1 class=\"title\"><a href=\"#\">Cash Book</a></h1>");
		// out.println("<p class=\"byline\"><small>cash book </small></p>");
		out.println("<div class=\"entry\">");
		out.println("<p><strong>");
		out.println("<div align=\"center\">");
		out.println("<form name=\"CashBook\" action=\"CashBookController\">");
		out.println("<table  width = \"600\" border=\"1px solid\" bordercolor = \"black\">");
		out.println("<tr height=\"50\">");
		out.println(
				"<th align = \"center\" colspan=\"3\"  bgcolor=\"black\"><font color=\"white\"> Cash Book</font></th>");
		out.println(
				"<th align = \"center\" colspan=\"5\" bgcolor=\"black\"><font color=\"white\"> Date From : <input type=\"date\" required name=\"datefrom\" ></font></th>");
		out.println(
				"<th align = \"center\" colspan=\"5\"  bgcolor=\"black\"><font color=\"white\"> Date To : <input type=\"date\" required name=\"dateto\" ></font></th>");
		out.println(
				"<th align = \"center\" colspan=\"3\"   bgcolor=\"black\"><font color=\"white\"> <input type = \"submit\" value = \"search\" name = \"search\"> </font></th>");
		out.println("</tr>");
		out.println("<tr height=\"30\">");
		out.println(
				"<th align = \"center\" colspan=\"3\"  bgcolor=\"black\"><font color=\"white\"> Serial No. </font></th>");
		out.println(
				"<th align = \"center\" colspan=\"5\" bgcolor=\"black\"><font color=\"white\"> Date </font></th>");
		out.println(
				"<th align = \"center\" colspan=\"5\"  bgcolor=\"black\"><font color=\"white\"> Amount </font></th>");
		out.println(
				"<th align = \"center\" colspan=\"3\"   bgcolor=\"black\"><font color=\"white\">  Pay/Receive </font></th>");
		out.println("</tr>");
		int i = 1;
		ArrayList<CashBookPojo> cashbook = (ArrayList<CashBookPojo>) request.getAttribute("CashBook");
		for(CashBookPojo b : cashbook){
			out.println("<tr height=\"30\">");
			out.println(
					"<th align = \"center\" colspan=\"3\" > " + i++ +" </th>");
			out.println(
					"<th align = \"center\" colspan=\"5\" > "+ new java.util.Date(b.getTran_date().getTime()) +"  </th>");
			out.println(
					"<th align = \"center\" colspan=\"5\" >" +  b.getAmount() + " </font></th>");
			out.println(
					"<th align = \"center\" colspan=\"3\" >  " + b.getOperation() + " </font></th>");
			if(b.getOperation().equals("Receive")){
				total += b.getAmount();
			}
			else
			{
				total -= b.getAmount();
			}
			out.println("</tr>");
		}
		
		out.println("<tr height=\"30\">");
		out.println(
				"<th align = \"center\" colspan=\"8\"  bgcolor=\"black\"><font color=\"white\"> Closing Balance :</font></th>");
		out.println(
				"<th align = \"center\" colspan=\"8\" bgcolor=\"black\"><font color=\"white\">" + total + " </font></th>");
		out.println("</tr>");
		out.println("</table>");
		out.println("</form>");

		out.println("</div>");
		out.println("</strong> </p>");
		out.println("</div>");
		out.println("</div>");
		out.println("</div>");
		out.println("<div style=\"clear: both;\">&nbsp;</div>");
		out.println("</div>");
		out.println("</div><br><br><br><br><br><br>");
		rd = request.getRequestDispatcher("Footer");
		rd.include(request, response);
		out.println("</body>");
		out.println("</html>");
	}

}
