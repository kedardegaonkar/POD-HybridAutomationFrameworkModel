package BaseandTestClasses;

import java.io.IOException;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import UtilsClasses.XLUtils;

import PageObjectClasses.PageObjectLoginLogout;

import UtilsClasses.ExtentReport;

public class TC1_LoginLogout2 extends BaseConnection
{	
	@Test(dataProvider="datapro")
	public void TC1LoginLogout2(String user, String pswd) throws IOException, InterruptedException
	{
		testcasestarted ();
		
		String alerttext;
		
		PageObjectLoginLogout pgll = new PageObjectLoginLogout(driver);
		
		pgll.setUserName(user);
		System.out.println("Entered UserName: "+user);
		logj.info("Entered UserName: "+user);
		ExtentReport.logger.pass("Entered UserName: "+user);
		
		pgll.setPassword(pswd);
		System.out.println("Entered Password: "+pswd);
		logj.info("Entered Password: "+pswd);
		ExtentReport.logger.pass("Entered UserName: "+pswd);
		
		pgll.clickSubmit();
		System.out.println("Login clicked");
		logj.info("Login clicked");
		ExtentReport.logger.pass("Login clicked");
		
		Thread.sleep(3000);
		
		if(isAlertPresent()==true)
		{
			alerttext = driver.switchTo().alert().getText();
			System.out.println("Alert message received: "+alerttext);
			logj.info("Alert message received: "+alerttext);
			ExtentReport.logger.pass("Alert message received: "+alerttext);
			
			driver.switchTo().alert().accept();
			System.out.println("Alert message accepted");
			logj.info("Alert message accepted");
			ExtentReport.logger.pass("Alert message accepted");
			driver.switchTo().defaultContent();
			
			System.out.println("Login failed with UserName: "+user+" & Password: "+pswd);
			logj.warn("Login failed with UserName: "+user+" & Password: "+pswd);
			ExtentReport.logger.fail("Login failed with UserName: "+user+" & Password: "+pswd);
			
			testcasecompleted();
			Assert.assertTrue(false);
		}
		else
		{		
			Assert.assertTrue(true);
			
			System.out.println("Login successful with UserName: "+user+" & Password: "+pswd);
			logj.info("Login successful with UserName: "+user+" & Password: "+pswd);
			ExtentReport.logger.pass("Login successful with UserName: "+user+" & Password: "+pswd);
			
			pgll.clickLogout();
			System.out.println("Logout clicked");
			logj.info("Logout clicked");
			ExtentReport.logger.pass("Logout clicked");
			
			
			Thread.sleep(3000);
			
			alerttext = driver.switchTo().alert().getText();
			System.out.println("Alert message received: "+alerttext);
			logj.info("Alert message received: "+alerttext);
			ExtentReport.logger.pass("Alert message received: "+alerttext);
			
			driver.switchTo().alert().accept();
			System.out.println("Alert message accepted");
			logj.info("Alert message accepted");
			ExtentReport.logger.pass("Alert message accepted");
			driver.switchTo().defaultContent();
			
			System.out.println("Logout successful");
			logj.info("Logout successful");	
			ExtentReport.logger.pass("Logout successful");
			
			testcasecompleted();
		}
	}
	
	public boolean isAlertPresent() throws IOException
	{
		try
		{
			driver.switchTo().alert();
			return true;
		}
		catch (NoAlertPresentException e)
		{
			return false;
		}
	}
	
	@DataProvider(name="datapro")
	String[][] getData() throws IOException
	{		
		int rccount[] = XLUtils.XLDataProvider("TC1_LoginLogout2");
		int rcnt = rccount[0]; 
		int ccnt = rccount[1];
		System.out.println("Row count in Login Table is "+ rcnt);
		System.out.println("Column count in Login Table is "+ ccnt);
		String[][] exldata = new String[rcnt][ccnt];
		
		for (int i=1 ; i<=rcnt ; i++)
		{
			XSSFRow currentrow = sheet.getRow(i);
			
			for (int j=0 ; j<ccnt ; j++)
			{
				System.out.print(" " + currentrow.getCell(j).toString());
				exldata[i-1][j] = currentrow.getCell(j).toString();
			}
			System.out.println();
		}
		
		return exldata;
	}
}
