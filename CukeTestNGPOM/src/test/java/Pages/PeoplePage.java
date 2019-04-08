package Pages;

import java.util.ArrayList;
import java.util.Iterator;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Utility.TestUtil;

public class PeoplePage extends BasePage{
	
	@FindBy(linkText="My Site")
	WebElement mySite;
	
	@FindBy(xpath="//span[contains(text(),'Site Pages')]")
	WebElement sitePagesLink;
	
	@FindBy(xpath="//li[@data-tip-target='side-menu-page']//a[@class='sidebar__button'][contains(text(),'Add')]")
	WebElement addSiteButton;
	
	@FindBy(xpath="//span[contains(text(),'People')]")
	WebElement peopleLink;
	
	@FindBy(xpath="//li[@data-tip-target='people']//a[@class='sidebar__button'][contains(text(),'Add')]")
	WebElement addPeople;
	
	@FindBy(xpath="//div[@class='token-field__input-container']")
	WebElement inviteUserName;
	
	@FindBy(how=How.NAME,using="[type=''radio'][value='follower']")
	WebElement selectRole;
	
	@FindBy(xpath="//textarea[@id='message']")
	WebElement message;
	
	@FindBy(xpath="//button[@type='submit']")
	WebElement sendInvitation;
	
	String testDataFilePath= "D:\\pushpa_wrkspace\\CukeTestNGPOM\\src\\test\\java\\Config\\TestData.xlsx";
	
	
	public PeoplePage() {
		PageFactory.initElements(driver, this);
	}
	
	/*@DataProvider
	public Iterator<Object[]> getTestData() throws Throwable {
		ArrayList<Object[]> testdata = TestUtil.getDataFromExcel(testDataFilePath, "PeoplePage");
		return testdata.iterator();
		
		
	}*/
	//@Test(dataProvider="getTestData")
	public void sendInvite(String personEmail,String roleValue,String messageToBeSent ) throws InterruptedException {
		
		
		wait1.until(ExpectedConditions.visibilityOf(mySite)).click();
		
		Thread.sleep(5000);
		wait1.until(ExpectedConditions.visibilityOf(sitePagesLink)).click();
		
		Thread.sleep(5000);
		wait1.until(ExpectedConditions.visibilityOf(peopleLink)).click();
		Thread.sleep(5000);

	    wait1.until(ExpectedConditions.visibilityOf(addPeople)).click();
		
		Thread.sleep(5000);
		wait1.until(ExpectedConditions.visibilityOf(inviteUserName)).sendKeys(personEmail);
		
		//select radio option 
		//call common method to select radio opiton from Base class
		
		selectRadioOption("name","role",roleValue);
		
		message.sendKeys(messageToBeSent);
		
		Thread.sleep(6000);
		
	}
	
	
	public void clickSubmit() throws InterruptedException {
		sendInvitation.click();
		Thread.sleep(6000);		
	}

}
