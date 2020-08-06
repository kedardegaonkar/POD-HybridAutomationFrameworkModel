package BaseandTestClasses;

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Test;

import PageObjectClasses.PageObjectLoginLogout;

import UtilsClasses.ExtentReport;

public class TC1_LoginLogout1 extends BaseConnection
{	
	@Test
	public void TC1LoginLogout1() throws IOException, InterruptedException
	{
		Login ();
		
		if(driver.getTitle().contains("GTPL Bank Manager"))
		{
			Assert.assertTrue(true);
			System.out.println("Login successful");
			logj.info("Login successful");
			ExtentReport.logger.pass("Login successful");
			
			testcasecompleted();
		}
		else
		{
			System.out.println("Login failed");
			logj.info("Login failed");
			ExtentReport.logger.fail("Login failed");
		
			testcasecompleted();
			Assert.assertTrue(false);
		}
	}

	public void Login () throws InterruptedException
	{
		testcasestarted ();
	
		PageObjectLoginLogout pgll = new PageObjectLoginLogout(driver);
	
		pgll.setUserName(UName);
		System.out.println("Entered valid UserName: "+UName);
		logj.info("Entered valid UserName: "+UName);
		ExtentReport.logger.pass("Entered valid UserName: "+UName);
	
		pgll.setPassword(PWord);
		System.out.println("Entered valid Password: "+PWord);
		logj.info("Entered valid Password: "+PWord);
		ExtentReport.logger.pass("Entered valid Password: "+PWord);
	
		pgll.clickSubmit();
		System.out.println("Login clicked");
		logj.info("Login clicked");
		ExtentReport.logger.pass("Login clicked");
	
		Thread.sleep(5000);
	}
}