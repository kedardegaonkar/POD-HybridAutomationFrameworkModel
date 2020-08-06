package BaseandTestClasses;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import PageObjectClasses.PageObjectAddCustomer;
import PageObjectClasses.PageObjectLoginLogout;

import UtilsClasses.ExtentReport;

public class TC2_AddCustomer extends BaseConnection
{
	@Test
	public void TC2AddCustomer() throws IOException, InterruptedException
	{
		TC1_LoginLogout1 obj = new TC1_LoginLogout1();
		obj.Login ();
		System.out.println("Login successful");
		logj.info("Login successful");
		ExtentReport.logger.pass("Login successful");
		
		PageObjectLoginLogout pgll = new PageObjectLoginLogout(driver);
		
		PageObjectAddCustomer pgad = new PageObjectAddCustomer(driver);
		
		pgll.clickAddCustomer();
		pgad.setCustomerName("Kedar");
		pgad.setCustomerGender("male");
		pgad.setCustomerDOB("09", "23", "1980");
		Thread.sleep(3000);
		pgad.setCustomerAddress("INDIA");
		pgad.setCustomerCity("Pune");
		pgad.setCustomerState("MH");
		pgad.setCustomerPINNo(411038);
		pgad.setCustomerTelephone("9370697342");
		pgad.setCustomerEMailID(RandomString()+"@gmail.com");
		
		Thread.sleep(3000);
		
		pgad.clickSubmitCustomer();
		System.out.println("New Customer Form Filled & Submitted");
		logj.info("New Customer Form Filled & Submitted");
		ExtentReport.logger.pass("New Customer Form Filled & Submitted");
	
		boolean res = driver.getPageSource().contains("Customer Registered Successfully");
		
		if(res==true)
		{
			Assert.assertTrue(true);
			System.out.println("New Customer Added");
			logj.info("New Customer Added");
			ExtentReport.logger.pass("New Customer Added");
		}
		else
		{
			System.out.println("New Customer Not Added");
			logj.info("New Customer Not Added");
			ExtentReport.logger.fail("New Customer Not Added");
			Assert.assertTrue(false);
		}
	}
}