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

@WebServlet(urlPatterns = "/register_user")
public class RegisterUser extends HttpServlet{
	
	static {
		System.out.println("Omaira ni mo shinde ure");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		String name = req.getParameter("name");
		String userName = req.getParameter("registerUserName");
		String password = req.getParameter("registerPassword");
		
		User user = new User();
		user.setName(name);
		user.setUserName(userName);
		user.setPassword(password);
		
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		System.out.println("Hello there i am here");
		
		boolean registrationSucessfull = completeRegistration(user);
		if (registrationSucessfull) {
			out.println("<html><head>");
			out.println("<script src='assets/js/login.js'></script>");
			out.println("</head><body>");
			out.println("<script type='text/javascript'>");
			out.println("showMessageAndRedirect();"); 
			out.println("</script>");
			out.println("</body></html>");
		}
		else {
			out.println("<html><body>");
			out.println("<h2>Something went wrong. Please try again..</h2>");
			out.println("</body></html>");
			RequestDispatcher requestDispatcher = req.getRequestDispatcher("login_register.html");
			requestDispatcher.include(req, resp);
		}
	}
	
	public static boolean completeRegistration(User user) {
		User checkUser = Utility.entityManager.find(User.class, user.getUserName());
		if ((user != null) && (checkUser == null)) {
			System.out.println("Hello there i am here");
			Utility.entityTransaction.begin();
			System.out.println("Hello there i am here");
			Utility.entityManager.persist(user);
			System.out.println("Data entered sucessfully");
			Utility.entityTransaction.commit();
			return true;
		}
		return false;
	}
}
