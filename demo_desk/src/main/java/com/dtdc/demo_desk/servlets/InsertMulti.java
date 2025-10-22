package com.dtdc.demo_desk.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dtdc.demo_desk.model.Shipment;
import com.dtdc.demo_desk.utility.Utility;

@WebServlet(urlPatterns = "/insertMulti")
public class InsertMulti extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Shipment> list = getList(req);
		System.out.println(list.size());

		int count = 0;
		try {
			Utility.entityTransaction.begin();
			for (Shipment shipment : list) {
				Utility.entityManager.persist(shipment);
				count++;
				if (count % 5 == 0) {
					Utility.entityManager.flush();
					Utility.entityManager.clear();
					count = 0;
				}
			}
			Utility.entityTransaction.commit();
			PrintWriter writer = resp.getWriter();
			writer.print("<html><body>");
			writer.print("<h2 style=\"color:green\">Shipment Data Saved Sucessfully</h2>");
			writer.print("</body></html>");

			RequestDispatcher dispatcher = req.getRequestDispatcher("insertMulti.html");
			dispatcher.include(req, resp);
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static List<Shipment> getList(HttpServletRequest req) {
		List<Shipment> shipmentList = new ArrayList<Shipment>();
		Shipment shipment;

		int rowCount = Integer.parseInt(req.getParameter("rowCount"));

		for (int i = 0; i < rowCount; i++) {
			try {
				String cnote = req.getParameter("cNote_" + i);
				String date = req.getParameter("date_" + i).trim();
				String content = req.getParameter("content_" + i);
				String consignorName = req.getParameter("consignorName_" + i);
				long consignorMob = Long.parseLong(req.getParameter("consignorMob_" + i).trim());
				String consigneeName = req.getParameter("consigneeName_" + i);
				long consigneeMob = Long.parseLong(req.getParameter("consigneeMob_" + i).trim());
				String consigneeAddress = req.getParameter("consigneeAddress_" + i);
				String destination = req.getParameter("destination_" + i);
				int pincode = Integer.parseInt(req.getParameter("pincode_" + i).trim());
				int noOfPieces = Integer.parseInt(req.getParameter("pieces_" + i).trim());
				double actualWeight = Double.parseDouble(req.getParameter("actualWeight_" + i).trim());
				String dimensions = req.getParameter("dimensions_" + i);
				String riskSurcharge = req.getParameter("riskSurcharge_" + i);
				double declaredValue = Double.parseDouble(req.getParameter("declaredValue_" + i).trim());

				shipment = new Shipment();
				shipment.setEntry_date(date);
				shipment.setTracking_no(cnote);
				shipment.setConsignor_name(consignorName);
				shipment.setConsignor_contact(consignorMob);
				shipment.setConsignee_name(consigneeName);
				shipment.setConsignee_contact_number(consigneeMob);
				shipment.setConsignee_address(consigneeAddress);
				shipment.setDestination(destination);
				shipment.setPincode(pincode);
				shipment.setNo_of_pieces(noOfPieces);
				shipment.setActual_weight(actualWeight);
				shipment.setShipment_dimension(dimensions);
				shipment.setShipment_type(content);
				shipment.setRisk_surcharge(riskSurcharge);
				shipment.setDeclared_value(declaredValue);

				shipmentList.add(shipment);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return shipmentList;
	}
}
