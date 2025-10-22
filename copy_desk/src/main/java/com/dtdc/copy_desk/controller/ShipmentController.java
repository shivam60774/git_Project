package com.dtdc.copy_desk.controller;

import java.util.ArrayList;
import java.util.List;

import com.dtdc.copy_desk.model.Shipment;
import com.dtdc.copy_desk.utility.Utility;

public class ShipmentController {

	public static List<Shipment> fetchDataFromFrontend() {
		//To be continued
		return null;
	}
	
	public static void displayDataToFrontend(Shipment shipment) {
		//To be continued
	}

	public static void insertSingleShipment(Shipment shipment) {
		System.out.println("I came here");
		if (shipment != null) {
			Utility.entityTransaction.begin();
			Utility.entityManager.persist(shipment);
			Utility.entityTransaction.commit();
			System.out.println("Data entered sucessfully");
		}
		else {
			System.out.println("Could not enter data. try again");
		}
	}
	
	public static void insertDataIntoDatabase(List<Shipment> shipmentList) {
		if (shipmentList != null) {
			Utility.entityTransaction.begin();
			int listSize = shipmentList.size();
			while (listSize > 0) {
				for (Shipment shipment : shipmentList) {
					Shipment shipmentData = (Shipment) shipment;
					Utility.entityManager.merge(shipmentData);
					listSize--;
				}
				Utility.entityTransaction.commit();

			}
		}
	}
	
	public static Shipment findSingleShipment(String trackingNumber) {
		Utility.entityTransaction.begin();
		return Utility.entityManager.find(Shipment.class, trackingNumber);
	}

	public static List<Shipment> findMultipleShipment(List<String> trackingNumberList) {
		if (trackingNumberList != null) {
			Utility.entityTransaction.begin();
			List<Shipment> shipmentDataList = new ArrayList<Shipment>();
			int listSize = trackingNumberList.size();
			for (String trackingNumber : trackingNumberList) {
				shipmentDataList.add(Utility.entityManager.find(Shipment.class, trackingNumber));
			}
			Utility.entityTransaction.commit();
			return shipmentDataList;
		} else {
			return null;
		}
	}

	public static void deleteSingleShipmentRecord(String trackingNumber) {
		Shipment singleShipment = findSingleShipment(trackingNumber);
		if (singleShipment != null) {
			Utility.entityTransaction.begin();
			Utility.entityManager.remove(singleShipment);
			Utility.entityTransaction.commit();
			System.out.println("Shipment data removed from the database");
		}
	}

	public static void deleteMultipleShipmentRecord(List<String> trackingNumberList) {
		if (trackingNumberList != null) {
			int listCount = trackingNumberList.size();
			Utility.entityTransaction.begin();
			while (listCount > 0) {
				for (String trackingNo : trackingNumberList) {
					Shipment shipment = findSingleShipment(trackingNo);
					Utility.entityManager.remove(shipment);
					listCount--;
				}
			}
			Utility.entityTransaction.commit();
		}
	}
	
	public static void updateShipment(String trackingNumber) {
		Shipment shipment = findSingleShipment(trackingNumber);
		//To be continued
	}

}
