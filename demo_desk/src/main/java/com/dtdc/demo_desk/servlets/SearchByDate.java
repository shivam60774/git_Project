package com.dtdc.demo_desk.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dtdc.demo_desk.model.Shipment;
import com.dtdc.demo_desk.utility.Utility;
import com.google.gson.Gson;

@WebServlet(urlPatterns = "/searchByDate")
public class SearchByDate extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String date = req.getParameter("date");
		PrintWriter writer = resp.getWriter();
		List<Shipment> shipments = Utility.entityManager.createQuery("SELECT s FROM Shipment s WHERE s.entry_date =: date", Shipment.class)
				.setParameter("date",date).getResultList();
		if (shipments != null) {
			resp.setContentType("application/json");
			Gson gson = new Gson();
			String json = gson.toJson(shipments);
			writer.write(json);
		}else {
			writer.write("{}");
		}
		writer.flush();
	}
}
