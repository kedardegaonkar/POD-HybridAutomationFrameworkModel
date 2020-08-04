package BaseandTestClasses;

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Test;
import PageObjectClasses.PageObjectLoginLogout;

public class TC1_LoginLogout1 extends BaseConnection
{
	@Test
	public void TC1LoginLogout1() throws IOException, InterruptedException
	{
		PageObjectLoginLogout pgll = new PageObjectLoginLogout(driver);
			
		pgll.setUserName(UName);
		System.out.println("Entered valid UserName: "+UName);
		logj.info("Entered valid UserName: "+UName);
		
		pgll.setPassword(PWord);
		System.out.println("Entered valid Password: "+PWord);
		logj.info("Entered valid Password: "+PWord);
		
		pgll.clickSubmit();
		Thread.sleep(10000);
		
		if(driver.getTitle().contains("GTPL Bank Manager"))
		{
			Assert.assertTrue(true);
			System.out.println("Login successful. Test Case Passed");
			logj.info("Login successful. Test Case Passed");
		}
		else
		{
			System.out.println("Login failed. Test Case Failed");
			logj.info("Login failed. Test Case Failed");
			Assert.assertTrue(false);
		}
	}
}