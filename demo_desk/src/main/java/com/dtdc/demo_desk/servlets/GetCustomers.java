package com.dtdc.demo_desk.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dtdc.demo_desk.model.Customers;
import com.dtdc.demo_desk.utility.FetchCustomers;
import com.dtdc.demo_desk.utility.Utility;
import com.google.gson.Gson;

@WebServlet(urlPatterns = "/getCustomers")
public class GetCustomers extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<String> customerList = FetchCustomers.getCustomerList();
		PrintWriter writer = resp.getWriter();
		if (customerList != null) {
			Gson gson = new Gson();
			String jsonList = gson.toJson(customerList);
			writer.write(jsonList);
		}
		else {
			writer.write("{}");
		}
		writer.flush();
	}
}
