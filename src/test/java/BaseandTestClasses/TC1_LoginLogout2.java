package BaseandTestClasses;

import java.io.IOException;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import PageObjectClasses.PageObjectLoginLogout;
import UtilsClasses.XLUtils;

public class TC1_LoginLogout2 extends BaseConnection
{	
	@Test(dataProvider="datapro")
	public void TC1LoginLogout2(String user, String pswd) throws IOException, InterruptedException
	{
		String alerttext;
		
		PageObjectLoginLogout pgll = new PageObjectLoginLogout(driver);
		
		pgll.setUserName(user);
		System.out.println("Entered UserName");
		logj.info("Entered UserName");
		
		pgll.setPassword(pswd);
		System.out.println("Entered Password");
		logj.info("Entered Password");
		
		pgll.clickSubmit();
		Thread.sleep(3000);
		
		if(isAlertPresent()==true)
		{
			alerttext = driver.switchTo().alert().getText();
			System.out.println(alerttext);
			logj.info(alerttext);
			
			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();
			
			System.out.println("Login failed with UserName: "+user+" & Password: "+pswd+". Test Case Failed");
			logj.warn("Login failed with UserName: "+user+" & Password: "+pswd+". Test Case Failed");
			
			Assert.assertTrue(false);
		}
		else
		{		
			Assert.assertTrue(true);
			
			System.out.println("Login successful with UserName: "+user+" & Password: "+pswd);
			logj.info("Login successful with UserName: "+user+" & Password: "+pswd);
			
			pgll.clickLogout();
			Thread.sleep(3000);
			
			alerttext = driver.switchTo().alert().getText();
			System.out.println(alerttext);
			logj.info(alerttext);
			
			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();
			
			System.out.println("Logged out. Test Case Passed");
			logj.info("Logged out. Test Case Passed");	
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
