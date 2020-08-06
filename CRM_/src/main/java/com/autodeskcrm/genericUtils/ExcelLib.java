package com.autodeskcrm.genericUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * 
 * @author indudhara
 *
 */

public class ExcelLib {
	
	String filepath = "./testData/testScriptData.xlsx"; 
	
	/**
	 * To get data from Excel file by passing
	 * @param sheet
	 * @param row
	 * @param cell
	 * @return
	 * @throws Throwable
	 */
	public String readExcelData(String sheet, int row, int cell) throws Throwable
	{
		Workbook wb = WorkbookFactory.create(new FileInputStream(filepath));
		String data = wb.getSheet(sheet).getRow(row).getCell(cell).toString();
		wb.close();
		return data;
	}
	
	/**
	 * To set/write data into Excel file by passing
	 * @param sheet
	 * @param row
	 * @param cell
	 * @param data
	 * @throws Throwable
	 */
	public void writeExcelData(String sheet, int row, int cell, String data) throws Throwable
	{
		Workbook wb = WorkbookFactory.create(new FileInputStream(filepath));
		wb.getSheet(sheet).getRow(row).getCell(cell).setCellValue(data);
		FileOutputStream fos = new FileOutputStream(filepath);
		wb.write(fos);
		wb.close();
	}
	
	/**
	 * To count the used no of row count in a Excel sheet
	 * @param sheet
	 * @return
	 * @throws Throwable
	 */
	public int getRowCount(String sheet) throws Throwable
	{
		Workbook wb = WorkbookFactory.create(new FileInputStream(filepath));
		int num = wb.getSheet(sheet).getLastRowNum();
		wb.close();
		return num ;
	}
}
