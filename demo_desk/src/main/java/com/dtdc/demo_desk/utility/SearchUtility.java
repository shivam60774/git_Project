package com.dtdc.demo_desk.utility;

import javax.servlet.http.HttpServletRequest;

import com.dtdc.demo_desk.model.Shipment;
import com.google.gson.Gson;

public class SearchUtility {
	public static String searchShipment(HttpServletRequest req) {
		String trackingNumber = req.getParameter("trackingNumber");
//		System.out.println("I was here in utility");
		Shipment shipment = Utility.entityManager.find(Shipment.class, trackingNumber);

		if (shipment != null) {
			Gson gson = new Gson();
			
			String json = gson.toJson(shipment);
			
			return json;
		}
		return null;
	}
}
