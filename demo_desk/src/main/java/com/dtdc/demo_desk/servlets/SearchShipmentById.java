package com.dtdc.demo_desk.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dtdc.demo_desk.utility.SearchUtility;

@WebServlet(urlPatterns = "/searchShipmentById")
public class SearchShipmentById extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		System.out.println("I am here");
		String shipmentJsonData = SearchUtility.searchShipment(req);
		PrintWriter writer = resp.getWriter();
		if (shipmentJsonData != null) {
//			System.out.println("Data fetched sucessfully");
			resp.setContentType("application/json");
			writer.print(shipmentJsonData);
		}else {
//			System.out.println("Data couldn't be fetched");
			writer.print("{}");
		}
		writer.flush();
	}
}
