package PageObjectClasses;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PageObjectLoginLogout
{
	WebDriver ldriver;
	
	public PageObjectLoginLogout (WebDriver rdriver)
	{
		ldriver=rdriver;
		PageFactory.initElements(rdriver, this);
	}
	
	
	@FindBy(name="uid")
	WebElement txtUserName;
	
	@FindBy(name="password")
	WebElement txtPassword;
	
	@FindBy(name="btnLogin")
	WebElement btnLogin;
	
	@FindBy(xpath="/html/body/div[3]/div/ul/li[10]/a")
	WebElement lnkLogout;
	
	@FindBy(xpath="/html/body/div[3]/div/ul/li[2]/a")
	WebElement lnkAddCustomer;
	
	public void setUserName(String uname)
	{
		txtUserName.sendKeys(uname);
	}
	
	public void setPassword(String pwd)
	{
		txtPassword.sendKeys(pwd);
	}
	
	public void clickSubmit()
	{
		btnLogin.click();
	}
	
	public void clickAddCustomer()
	{
        lnkAddCustomer.click();
	}
	
	public void clickLogout() throws InterruptedException
	{
		clickAddCustomer();
        Thread.sleep(3000);
       
        JavascriptExecutor js = (JavascriptExecutor) ldriver;
        js.executeScript("window.scrollTo(0,document.body.scrollHeight)"); //Scroll vertical till end of page
       
        lnkLogout.click();	
        
		/*Method 1:
		Point point1 = lnkLogout.getLocation();
		int lox = point1.getX(); 
		int loy = point1.getY();

		System.out.println("Logout - X: "+lox+" & Y: "+loy);
		Actions actions = new Actions(ldriver);
		actions.moveToElement(lnkLogout, lox, loy).click().build().perform();
		
		Method 2:
		
		int height = (lnkLogout.getSize().getHeight()/2) - (lnkLogout.getSize().getHeight());
		int width = (lnkLogout.getSize().getWidth()/2) - (lnkLogout.getSize().getWidth());
		//System.out.println("Logout - Height: "+height+" & Width: "+width);
		Actions actions = new Actions(ldriver);
		actions.moveToElement(lnkLogout, 20, 20).click().build().perform();
		*/
	}
	
}

