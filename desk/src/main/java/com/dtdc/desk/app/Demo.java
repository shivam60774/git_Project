package com.dtdc.desk.app;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Demo {

	static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {
		String filePath = "D:\\DTDC\\TrackSheet\\DTDC-Desk\\desk\\data\\newDemoData.xlsx";
		int numberOfColumns = 8;
		
		try {
			Workbook book = new XSSFWorkbook(new FileInputStream(filePath));
			CellStyle cellStyle = book.createCellStyle();
			cellStyle.setAlignment(HorizontalAlignment.CENTER);
			int numberOfSheets = book.getNumberOfSheets();
//			System.out.println(numberOfSheets);
			
			Sheet sheet = book.getSheetAt(0);
//			int noOfRows = sheet.getPhysicalNumberOfRows();
//			System.out.println(noOfRows);
			
//			int lastRow = fetchLastAvailableRow(sheet);
//			System.out.println(lastRow);
			
			int lastRowNum = sheet.getPhysicalNumberOfRows();
			System.out.println(lastRowNum);
//			removeGivenRow(lastRowNum, sheet);
			
//			Row row = sheet.getRow(1);
//			System.out.println(row.getCell(1).getStringCellValue());
			
//			int enterMore = 0;
//			while (enterMore == 0) {
//				int rowNum = sheet.getPhysicalNumberOfRows();
//				
//				if (sheet.getRow(rowNum) == null) {
//					Row row = sheet.createRow(rowNum);
//					for (int i = 0; i < numberOfColumns; i++) {
//						
//						Cell cell = row.createCell(i);
//						System.out.print("Enter the data for this cell : ");
//						String value = sc.nextLine();
//						cell.setCellValue(value);
//						cell.setCellStyle(cellStyle);
//						
//						System.out.println();
//					}
//					rowNum++;
//				}
//				else {
//					rowNum++;
//				}
//				System.out.println("To enter more press 0 to escape other keys : ");
//				enterMore = sc.nextInt();
//				if (enterMore != 0) {
//					
//				}
//			}
//		
			FileOutputStream fos = new FileOutputStream(filePath);
			book.write(fos);
			fos.close();
			book.close();		
//			System.out.println("Data Saved sucessfully");
//		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}
	
	public static void enterData() {
		
	}
	
	
	public static void removeGivenRow(int rowNum,Sheet sheet) {
		
		Row row = sheet.getRow(rowNum);
		sheet.removeRow(row);
		System.out.println("Row deleted sucessfully");
	}

}
