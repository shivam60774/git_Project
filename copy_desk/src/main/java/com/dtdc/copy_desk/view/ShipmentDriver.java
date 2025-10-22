package com.dtdc.copy_desk.view;

import java.util.Scanner;

import com.dtdc.copy_desk.controller.ShipmentController;
import com.dtdc.copy_desk.model.Shipment;
import com.dtdc.copy_desk.utility.Utility;

public class ShipmentDriver {
	
	{
		Utility.initUtility();
	}
	
	static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {
		int userChoice = -1;
		do {
			System.out.println("1. Insert Shipment");
			System.out.println("2. Find shipment");
			System.out.println("3. Delete Shipment");
			System.out.println("4. Update Shipment");
			System.out.println("0. Exit the menu");
			System.out.print("Enter the option number for operation : ");
			userChoice = sc.nextInt();
			sc.nextLine();
			switch (userChoice) {
			case 1:
				ShipmentController.insertSingleShipment(getData());
				break;
			
			case 2:
				displayShipmentData(ShipmentController.findSingleShipment("M25632563"));
				break;

			default:
				break;
			}
		} while (userChoice != 0);
		
	}
	
	public static Shipment getData() {
		Shipment s = new Shipment();
		String trackingNo, senderName, receiverName, destination;
		System.out.print("Enter unique tracking number : ");
		trackingNo = sc.nextLine();
		s.setTracking_no(trackingNo.trim());
		
		System.out.print("Enter sender name : ");
		senderName = sc.nextLine();
		s.setConsignor_name(senderName);
		
		System.out.print("Enter receiver name : ");
		receiverName = sc.nextLine();
		s.setConsignee_name(receiverName);
		
		System.out.print("Enter destination location : ");
		destination = sc.nextLine();
		s.setDestination_location(destination);
		
		s.setEntry_date(Utility.currentDate);
		s.setActual_weight("na");
		s.setCharged_weight("na");
		s.setConsignee_contact_number("na");
		s.setDeclared_value("na");
		s.setDestination_pincode("na");
		s.setMode_of_courier("na");
		s.setNo_of_pieces("na");
		s.setRisk_surcharge("na");
		s.setShipment_dimension("na");
		s.setShipment_type("na");
		s.setShipment_weight("na");
		s.setSource_location("na");
		
		return s;
	}
	
	public static void displayShipmentData(Shipment s) {
		if (s != null) {
			System.out.println("Date of Booking : "+s.getEntry_date());
			System.out.println("Tracking number : "+s.getTracking_no());
			System.out.println("Sender Name : "+s.getConsignor_name());
			System.out.println("Receiver Name : "+s.getConsignee_name());
			System.out.println("Destination : "+s.getDestination_location()+"\n\n");
		}
	}

}
