package UtilsClasses;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import BaseandTestClasses.BaseConnection;

public class XLUtils extends BaseConnection
{
	public static int[] XLDataProvider (String sheetname) throws IOException
	{
		excel = new FileInputStream(TDPath);
		workbook = new XSSFWorkbook(excel);
		sheet = workbook.getSheet(sheetname);
		
		int rowcount = sheet.getLastRowNum();
		int colcount = sheet.getRow(0).getLastCellNum();
		
		return new int [] {rowcount, colcount};
	}
}

