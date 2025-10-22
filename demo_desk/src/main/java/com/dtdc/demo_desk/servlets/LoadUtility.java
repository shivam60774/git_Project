package com.dtdc.demo_desk.servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(urlPatterns = "/loadUtility", loadOnStartup = 1)
public class LoadUtility extends HttpServlet {
	static {
		try {
		    Class.forName("com.dtdc.demo_desk.utility.Utility");
//		    System.out.println("JPA found!");
		} catch (ClassNotFoundException e) {
		    e.printStackTrace();
		}
	}
}
