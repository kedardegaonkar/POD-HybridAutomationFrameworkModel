package PageObjectClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class PageObjectAddCustomer
{
	WebDriver ldriver;
	
	public PageObjectAddCustomer (WebDriver rdriver)
	{
		ldriver=rdriver;
		PageFactory.initElements(rdriver, this);
	}
	

	@FindBy(how = How.NAME, using="name")
	WebElement txtName;

	@FindBy(how = How.NAME, using="rad1")
	WebElement rdGender;
	
	@FindBy(how = How.ID_OR_NAME, using="dob")
	WebElement txtDoB;

	@FindBy(how = How.NAME, using="addr")
	WebElement txtAddress;

	@FindBy(how = How.NAME, using="city")
	WebElement txtCity;

	@FindBy(how = How.NAME, using="state")
	WebElement txtState;
	
	@FindBy(how = How.NAME, using="pinno")
	WebElement txtPINNo;

	@FindBy(how = How.NAME, using="telephoneno")
	WebElement txtTelePhoneNo;
	
	@FindBy(how = How.NAME, using="emailid")
	WebElement txtEMailID;
	
	@FindBy(how = How.NAME, using="sub")
	WebElement btnSubmit;
	
	
	public void setCustomerName(String cname)
	{
		txtName.sendKeys(cname);
	}
	
	public void setCustomerGender(String cgender)
	{
		rdGender.click();
	}	
	
	public void setCustomerDOB(String mm, String dd, String yy)
	{
		txtDoB.sendKeys(mm);
		txtDoB.sendKeys(dd);
		txtDoB.sendKeys(yy);
	}	
	
	public void setCustomerAddress(String caddress)
	{
		txtAddress.sendKeys(caddress);
	}
	
	public void setCustomerCity(String ccity)
	{
		txtCity.sendKeys(ccity);
	}
	
	public void setCustomerState(String cstate)
	{
		txtState.sendKeys(cstate);
	}
	
	public void setCustomerPINNo(int cpinno)
	{
		txtPINNo.sendKeys(String.valueOf(cpinno)); //valueOf is used -- so int can be covered into String
	}
	
	public void setCustomerTelephone(String ctelephone)
	{
		txtTelePhoneNo.sendKeys(ctelephone);
	}

	public void setCustomerEMailID(String cemail)
	{
		txtEMailID.sendKeys(cemail);
	}
	
	public void clickSubmitCustomer()
	{
		btnSubmit.click();
	}
}

