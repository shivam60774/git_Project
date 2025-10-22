package com.dtdc.demo_desk.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.google.gson.annotations.SerializedName;

@Entity
public class Shipment {
	@Id
	@SerializedName("cNote")
	private String tracking_number;
	@SerializedName("date")
	private String entry_date;
	@SerializedName("consignorName")
	private String consignor_name;
	@SerializedName("consignorMob")
	private long consignor_contact;
	@SerializedName("consigneeName")
	private String consignee_name;
	@SerializedName("consigneeMob")
	private long consignee_contact_number;
	@SerializedName("consigneeAddress")
	private String consignee_address;
	@SerializedName("destination")
	private String destination;
	@SerializedName("pincode")
	private int pincode;
	@SerializedName("shipmentType")
	private String shipment_type;
	@SerializedName("declaredValue")
	private double declared_value;
	@SerializedName("pieces")
	private int no_of_pieces;
	@SerializedName("dimensions")
	private String shipment_dimension;
	@SerializedName("actualWeight")
	private double actual_weight;
	@SerializedName("riskSurcharge")
	private String risk_surcharge;

	public String getTracking_no() {
		return tracking_number;
	}

	public void setTracking_no(String tracking_no) {
		this.tracking_number = tracking_no;
	}

	public String getEntry_date() {
		return entry_date;
	}

	public void setEntry_date(String current_date) {
		this.entry_date = current_date;
	}

	public String getConsignor_name() {
		return consignor_name;
	}

	public void setConsignor_name(String consignor_name) {
		this.consignor_name = consignor_name;
	}

	public String getConsignee_name() {
		return consignee_name;
	}

	public void setConsignee_name(String consignee_name) {
		this.consignee_name = consignee_name;
	}

	public long getConsignee_contact_number() {
		return consignee_contact_number;
	}

	public void setConsignee_contact_number(long consignee_contact_number) {
		this.consignee_contact_number = consignee_contact_number;
	}

	public String getShipment_type() {
		return shipment_type;
	}

	public void setShipment_type(String shipment_type) {
		this.shipment_type = shipment_type;
	}

	public double getDeclared_value() {
		return declared_value;
	}

	public void setDeclared_value(double declared_value) {
		this.declared_value = declared_value;
	}

	public int getNo_of_pieces() {
		return no_of_pieces;
	}

	public void setNo_of_pieces(int no_of_pieces) {
		this.no_of_pieces = no_of_pieces;
	}

	public String getShipment_dimension() {
		return shipment_dimension;
	}

	public void setShipment_dimension(String shipment_dimension) {
		this.shipment_dimension = shipment_dimension;
	}

	public double getActual_weight() {
		return actual_weight;
	}

	public void setActual_weight(double actual_weight) {
		this.actual_weight = actual_weight;
	}

	public String getRisk_surcharge() {
		return risk_surcharge;
	}

	public void setRisk_surcharge(String risk_surcharge) {
		this.risk_surcharge = risk_surcharge;
	}

	public String getTracking_number() {
		return tracking_number;
	}

	public void setTracking_number(String tracking_number) {
		this.tracking_number = tracking_number;
	}

	public long getConsignor_contact() {
		return consignor_contact;
	}

	public void setConsignor_contact(long consignor_contact) {
		this.consignor_contact = consignor_contact;
	}
	
	public String getConsignee_address() {
		return consignee_address;
	}

	public void setConsignee_address(String consignee_address) {
		this.consignee_address = consignee_address;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public int getPincode() {
		return pincode;
	}

	public void setPincode(int pincode) {
		this.pincode = pincode;
	}
}
