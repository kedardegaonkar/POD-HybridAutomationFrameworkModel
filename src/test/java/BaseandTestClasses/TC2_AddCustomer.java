package BaseandTestClasses;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import PageObjectClasses.PageObjectAddCustomer;
import PageObjectClasses.PageObjectLoginLogout;
import UtilsClasses.ExtentReport;

public class TC2_AddCustomer extends BaseConnection
{
	ExtentReport er = new ExtentReport(); 
	
	@Test
	public void TC2AddCustomer() throws IOException, InterruptedException
	{
		PageObjectLoginLogout pgll = new PageObjectLoginLogout(driver);
		
		pgll.setUserName(UName);
		System.out.println("Entered UserName");
		logj.info("Entered UserName");
		
		pgll.setPassword(PWord);
		System.out.println("Entered Password");
		logj.info("Entered Password");
		
		pgll.clickSubmit();
		
		
		Thread.sleep(3000);
		
		
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
	
		boolean res = driver.getPageSource().contains("Customer Registered Successfully");
		
		if(res==true)
		{
			Assert.assertTrue(true);
			System.out.println("New Customer Added. Test Case Passed");
			logj.info("New Customer Added. Test Case Passed");
		}
		else
		{
			System.out.println("New Customer Not Added. Test Case Failed");
			logj.info("New Customer Not Added. Test Case Failed");
			Assert.assertTrue(false);
		}
	}
}