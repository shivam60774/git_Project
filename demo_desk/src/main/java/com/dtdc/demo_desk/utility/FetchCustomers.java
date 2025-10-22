package com.dtdc.demo_desk.utility;

import java.util.ArrayList;
import java.util.List;

import com.dtdc.demo_desk.model.Customers;

public class FetchCustomers {
	public static List<String> getCustomerList() {
		List<Customers> customerList = Utility.entityManager.createQuery("SELECT c FROM Customers c", Customers.class).getResultList();
		List<String> stringCustomerList = new ArrayList<String>();
		if (customerList != null) {
			for (Customers c : customerList) {
				stringCustomerList.add(c.getName());
			}
		}
		return stringCustomerList;
	}
}
