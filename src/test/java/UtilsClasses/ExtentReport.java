package UtilsClasses;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

import BaseandTestClasses.BaseConnection;

public class ExtentReport extends TestListenerAdapter
{
	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest logger;
	
	public void onTestSkipped(ITestResult tr) //@TestMethodSkipped
	{
		logger=extent.createTest(tr.getName());
		logger.log(Status.SKIP, MarkupHelper.createLabel(tr.getName(), ExtentColor.ORANGE));
	}
	
	public void onFinish (ITestContext testContext) //onFinish is Test tag finish in XML
	{
		extent.flush();
	}

	public void onStart(ITestContext testContext) //Test tag start in XML
	{
		String timeStamp= new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		String repName="Test-Report-"+timeStamp+".html";
		htmlReporter=new ExtentHtmlReporter(System.getProperty("user.dir")+ "/test-output/"+repName);
		htmlReporter.loadXMLConfig(System.getProperty("user.dir")+ "/extent-config.xml");
		
		extent= new ExtentReports();
	
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host name","localhost");
		extent.setSystemInfo("Environment","QA");
		extent.setSystemInfo("user","kedar");
	

		htmlReporter.config().setDocumentTitle("Gtpl Bank");
		htmlReporter.config().setReportName("Automation Test Report");
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		htmlReporter.config().setTheme(Theme.DARK);	
	}

	public void onTestFailure(ITestResult tr) //@TestMethodFailed
	{
		logger=extent.createTest(tr.getName());
		logger.log(Status.FAIL, MarkupHelper.createLabel(tr.getName(), ExtentColor.RED));
		
		//Below is code for Screen-Capture to be done from here, with timestamp
		String timeStamp= new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		
		TakesScreenshot ts = (TakesScreenshot) BaseConnection.driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String screenshotPath = System.getProperty("user.dir")+"/00Screenshots/"+tr.getName()+" - "+timeStamp+".png";
		File target = new File(screenshotPath);
		try {
			FileUtils.copyFile(source, target);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		File f = new File(screenshotPath);
		
		if(f.exists())
		{
			try {
				logger.fail("screenshot is below: "+logger.addScreenCaptureFromPath(screenshotPath));
			}
			catch (IOException e)
			{
			e.printStackTrace();
			}
		}
	}

	public void onTestSuccess(ITestResult tr) //@TestMethodPassed
	{
		logger=extent.createTest(tr.getName());
		logger.log(Status.PASS, MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN));
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub
	}

	public void onTestStart(ITestResult arg0) {
		// TODO Auto-generated method stub
	}
}

