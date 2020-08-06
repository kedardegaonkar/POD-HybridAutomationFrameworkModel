package BaseandTestClasses;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import UtilsClasses.ReadConfig;	

import UtilsClasses.ExtentReport;

public class BaseConnection
{
	public static WebDriver driver;
	//protected ThreadLocal<WebDriver> threadDriver = null;
	
	public static FileInputStream excel;
	public static XSSFWorkbook workbook;
	public static XSSFSheet sheet;
	
	public static Logger logj;
	
	static ReadConfig readconfig = new ReadConfig();
	String BURL = readconfig.getbaseURL();
	String UName = readconfig.getUserName();
	String PWord = readconfig.getPassword();
	protected static String TDPath = readconfig.getTDPath();
	
	@Parameters("browser")
	
	@BeforeClass
	public void launchBrowser(String browser) throws IOException
	{
		logj = Logger.getLogger("Gtpl Bank");
		PropertyConfigurator.configure("./log4j.properties");
		
		if (browser.equalsIgnoreCase("firefox"))
		{
			System.out.println("Executing on FIREFOX");
			logj.info("Executing on FIREFOX");
			System.setProperty("webdriver.gecko.driver", readconfig.getffPath());
			System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE,"true");
			System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE,"./00BrowserDrivers\\Log.txt"); // this file gets created. if already exists then - gets over-written at every run
			driver = new FirefoxDriver();
			System.out.println("Firefox Opened");
			logj.info("FirexFox Opened");
		}
		else if (browser.equalsIgnoreCase("chrome"))
		{
			System.out.println("Executing on CHROME");
			logj.info("Executing on CHROME");
			System.setProperty("webdriver.chrome.driver", readconfig.getchromePath());
			System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY,"true"); /// so timeout warnings will not come in Console
			driver = new ChromeDriver();
			System.out.println("Chrome Opened");
			logj.info("Chrome Opened");
		}
		else if (browser.equalsIgnoreCase("ie"))
		{
			System.out.println("Executing on IE");
			logj.info("Executing on IE");
			System.setProperty("webdriver.ie.driver", readconfig.getiePath());
			InternetExplorerOptions options = new InternetExplorerOptions();
			options.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
			driver = new InternetExplorerDriver(options);
			System.out.println("IE Opened");
			logj.info("IE Opened");
		}
		else
		{
			throw new IllegalArgumentException("The Browser Type is Undefined");
		}
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(BURL);
	}
	
	@AfterClass
	public void tearDown()
	{
		System.out.println("Closing Browser Instance");
		logj.info("Closing Browser Instance");
		driver.quit();
	}
	
	//Common Methods:
	public void testcasestarted () 
	{
		ExtentReport.logger.info("Test Case Started");
		System.out.println("Test Case Started");
		logj.info("Test Case Started");
	}
	
	public void testcasecompleted () 
	{
		System.out.println("Test Case Completed");
		logj.info("Test Case Completed");
		ExtentReport.logger.info("Test Case Completed");
	}
	
	public String RandomString()
	{
		String ranstr = RandomStringUtils.randomAlphabetic(8);
		return ranstr;
	}
	
	public String RandomNumbers()
	{
		String rannum = RandomStringUtils.randomNumeric(4);
		return rannum;
	}
}
