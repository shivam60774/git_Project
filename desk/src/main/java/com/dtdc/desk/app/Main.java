package com.dtdc.desk.app;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Main {

	static Scanner sc = new Scanner(System.in);
	static Sheet sheet;
	static CellStyle cellStyle;
	static String date;
	static String trackingNo;
	static String senderName;
	static String receiversName;
	static String destination;
	static int pinCode;
	static String shipmentType;

	public static void main(String[] args) {
		String filePath = "D:\\DTDC\\TrackSheet\\DTDC-Desk\\desk\\data\\demoSheet.xlsx";

		try {
			Workbook book = new XSSFWorkbook(new FileInputStream(filePath));
			cellStyle = book.createCellStyle();
			cellStyle.setAlignment(HorizontalAlignment.CENTER);

			sheet = book.getSheetAt(1);
//			System.out.println("I came here");
			int validLastRow = getValidLastRow(sheet);
			System.out.println("Valid Last Row Number: " + validLastRow);
//			Row row = sheet.createRow(validLastRow + 1);
//            getShipmentDetails(row);
//            removeGivenRow(validLastRow);

			// Optional: Save file after cleanup
			FileOutputStream fos = new FileOutputStream(filePath);
//			book.write(fos);
//			System.out.println("Data Saved Sucessfully");
			fos.close();
			book.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Enter the data into the cells
	public static void getShipmentDetails(Row row) {
		List data = new ArrayList();
		int indexToInsert = 0;
//		Cell cell = row.createCell(indexToInsert++);
		System.out.print("Enter shipment booking date : ");
		date = sc.nextLine();
		data.add(date);
//		cell.setCellValue(date);
//		cell = row.createCell(indexToInsert++);
		

		System.out.print("Enter shipment tracking no : ");
		trackingNo = sc.nextLine();
		data.add(trackingNo);
//		cell.setCellValue(trackingNo);
//		cell = row.createCell(indexToInsert++);

		System.out.print("Enter shipment sender's name : ");
		senderName = sc.nextLine();
		data.add(senderName);
//		cell.setCellValue(senderName);
//		cell = row.createCell(indexToInsert++);

		System.out.print("Enter receiver's name : ");
		receiversName = sc.nextLine();
		data.add(receiversName);
//		cell.setCellValue(receiversName);
//		cell = row.createCell(indexToInsert++);

		System.out.print("Enter destination location : ");
		destination = sc.nextLine();
		data.add(destination);
//		cell.setCellValue(destination);
//		cell = row.createCell(indexToInsert++);

		System.out.print("Enter destination pincode : ");
		pinCode = sc.nextInt();
		data.add(pinCode);
//		cell.setCellValue(pinCode);
//		cell = row.createCell(indexToInsert++);
		sc.nextLine();

		System.out.print("Enter shipment type : ");
		shipmentType = sc.nextLine();
		data.add(shipmentType);
//		cell.setCellValue(shipmentType);
//		cell = row.createCell(indexToInsert++);

//		System.out.println("Data entered sucessfully");
		
		System.out.println(data);

	}

	// Finds the last non-empty row and deletes all trailing empty rows
	public static int getValidLastRow(Sheet sheet) {
//		System.out.println("I came here");
		int lastRow = sheet.getLastRowNum();

		for (int i = lastRow; i >= 0; i--) {
			Row row = sheet.getRow(i);
			if (isRowEmpty(row)) {
				sheet.removeRow(row); // Clear the blank row
			} else {
				return i; // Found the last row with actual data
			}
		}
		return -1; // All rows empty
	}

	// Check if row is completely empty
	public static boolean isRowEmpty(Row row) {
		if (row == null)
			return true;

		for (int c = row.getFirstCellNum(); c < row.getLastCellNum(); c++) {
			Cell cell = row.getCell(c);
			if (cell != null && cell.getCellType() != CellType.BLANK
					&& !(cell.getCellType() == CellType.STRING && cell.getStringCellValue().trim().isEmpty())) {
				return false;
			}
		}
		return true;
	}

	public static void removeGivenRow(int rowNum) {
		Row row = sheet.getRow(rowNum);
		if (row != null) {
			sheet.removeRow(sheet.getRow(rowNum));
			System.out.println("Row Removed sucessfully");
		} else {
			System.out.println("Row is already empty");
		}

	}
	
	public static void insertIntoExcel(Row row) {
		
	}

}
