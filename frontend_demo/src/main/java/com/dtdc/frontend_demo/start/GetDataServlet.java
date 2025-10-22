package com.dtdc.frontend_demo.start;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value="/fetchData")
public class GetDataServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("user");
		String password = req.getParameter("password");
		
		PrintWriter writer = resp.getWriter();
		writer.print("Data fetched sucessfulyy. Go check your console!");
		System.out.println(username +" \t\t "+password);

	}
		
}
