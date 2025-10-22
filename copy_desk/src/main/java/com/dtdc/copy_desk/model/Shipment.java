package com.dtdc.copy_desk.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.dtdc.copy_desk.utility.Utility;
@Entity
public class Shipment {
	@Id
	private String tracking_number;
	private String entry_date ;
	private String consignor_name;
	private String consignee_name;
	private String source_location;
	private String destination_location;
	private String destination_pincode;
	private String consignee_contact_number;
	private String shipment_weight;
	private String mode_of_courier;
	private String shipment_type;
	private String declared_value;
	private String no_of_pieces;
	private String shipment_dimension;
	private String actual_weight;
	private String charged_weight;
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

	public String getSource_location() {
		return source_location;
	}

	public void setSource_location(String source_location) {
		this.source_location = source_location;
	}

	public String getDestination_location() {
		return destination_location;
	}

	public void setDestination_location(String destination_location) {
		this.destination_location = destination_location;
	}

	public String getDestination_pincode() {
		return destination_pincode;
	}

	public void setDestination_pincode(String destination_pincode) {
		this.destination_pincode = destination_pincode;
	}

	public String getConsignee_contact_number() {
		return consignee_contact_number;
	}

	public void setConsignee_contact_number(String consignee_contact_number) {
		this.consignee_contact_number = consignee_contact_number;
	}

	public String getShipment_weight() {
		return shipment_weight;
	}

	public void setShipment_weight(String shipment_weight) {
		this.shipment_weight = shipment_weight;
	}

	public String getMode_of_courier() {
		return mode_of_courier;
	}

	public void setMode_of_courier(String mode_of_courier) {
		this.mode_of_courier = mode_of_courier;
	}

	public String getShipment_type() {
		return shipment_type;
	}

	public void setShipment_type(String shipment_type) {
		this.shipment_type = shipment_type;
	}

	public String getDeclared_value() {
		return declared_value;
	}

	public void setDeclared_value(String declared_value) {
		this.declared_value = declared_value;
	}

	public String getNo_of_pieces() {
		return no_of_pieces;
	}

	public void setNo_of_pieces(String no_of_pieces) {
		this.no_of_pieces = no_of_pieces;
	}

	public String getShipment_dimension() {
		return shipment_dimension;
	}

	public void setShipment_dimension(String shipment_dimension) {
		this.shipment_dimension = shipment_dimension;
	}

	public String getActual_weight() {
		return actual_weight;
	}

	public void setActual_weight(String actual_weight) {
		this.actual_weight = actual_weight;
	}

	public String getCharged_weight() {
		return charged_weight;
	}

	public void setCharged_weight(String charged_weight) {
		this.charged_weight = charged_weight;
	}

	public String getRisk_surcharge() {
		return risk_surcharge;
	}

	public void setRisk_surcharge(String risk_surcharge) {
		this.risk_surcharge = risk_surcharge;
	}

}
