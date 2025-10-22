package com.dtdc.demo_desk.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dtdc.demo_desk.model.User;
import com.dtdc.demo_desk.utility.Utility;

@WebServlet(urlPatterns = "/loginUser")
public class loginUser extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("loginUserName");
		String password = req.getParameter("loginPassword");
		
		Utility.entityTransaction.begin();
		User user = Utility.entityManager.find(User.class, username);
		Utility.entityTransaction.commit();
		if ((user.getPassword()).equals(password)) {
			resp.sendRedirect("dashboard.html");

		}
		else {
			PrintWriter writer = resp.getWriter();
			writer.println("<html><body>");
			writer.println("<h1 style=\"color:red\">Wrong Username or Password</h1>");
			writer.println("</body></html>");
			
			RequestDispatcher requestDispatcher = req.getRequestDispatcher("login_register.html");
			requestDispatcher.include(req, resp);
		}
	}
}
