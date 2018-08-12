package com.christo.servlets.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.christo.servlets.daos.UsersDao;

/**
 * Servlet Filter implementation class AuthenticationFilter
 */
@WebFilter("/*")
public class AuthenticationFilter implements Filter {

	private ServletContext context;

	public void init(FilterConfig fConfig) throws ServletException {
		this.context = fConfig.getServletContext();
		this.context.log("AuthenticationFilter initialized");
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		String uri = req.getRequestURI();
		this.context.log("Requested Resource::" + uri);

		if (uri.endsWith("Login") || uri.endsWith("LoginController") || uri.endsWith("html") || uri.endsWith("js")
				|| uri.endsWith("css") || uri.endsWith("png") || uri.endsWith("Registeration")
				|| uri.endsWith("RegisterationConfirmation") || uri.endsWith("Logout")
				|| uri.endsWith("RegisterationController") || uri.endsWith("LogoutConfirmation")) {
			this.context.log("Home Page Access");
			chain.doFilter(request, response);
		} else {
			HttpSession session = req.getSession();
			int userid = 0;
			String username = new String();
			String password = new String();
			if (session != null) {
				Integer sessionid = (Integer) session.getAttribute("userid");
				username = (String) session.getAttribute("username");
				password = (String) session.getAttribute("password");
				if (sessionid != null && username != null && password != null) {
					userid = UsersDao.verify(username, password);
					if (userid == sessionid) {
						chain.doFilter(request, response);
					} else {
						res.sendRedirect("Login");
					}
				} else
					res.sendRedirect("Login");
			}
		}

	}

	public void destroy() {
		// close any resources here
	}

}
