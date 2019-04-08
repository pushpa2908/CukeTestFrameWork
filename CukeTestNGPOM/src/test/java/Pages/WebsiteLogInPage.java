	package Pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

public class WebsiteLogInPage extends BasePage {
	

	
	@FindBy(how=How.XPATH, using="//a[@title='Log In']")
	WebElement signIn; 
	
	@FindBy(how=How.NAME, using="usernameOrEmail")
	WebElement username;

	@FindBy(how=How.XPATH, using="//button[@type='submit']")
	WebElement continueButton;
	
	@FindBy(how=How.NAME, using="password")
	WebElement password;
	
	@FindBy(how=How.XPATH, using="//button[@type='submit']")
	WebElement submitButton;
	
	@FindBy(how=How.LINK_TEXT,using="My Site")
	WebElement mySite;
	
	private WebDriverWait wait;

	
	public WebsiteLogInPage(){
		   //super(driver);
		   //this.driver=driver;
		  wait = new WebDriverWait(driver, 15, 50);
		  PageFactory.initElements(driver, this);
		}
	
	
	
	public void login(String usenm,String passwd) throws Exception {
		
		//driver = BrowserUtility.openBrowser(driver,"chrome",url);
		wait.until(ExpectedConditions.visibilityOf(signIn)).click();
		 
		wait.until(ExpectedConditions.visibilityOf(username)).sendKeys(usenm);
		
		
		wait.until(ExpectedConditions.visibilityOf(continueButton)).click();
		
		wait.until(ExpectedConditions.visibilityOf( password)).sendKeys(passwd);
		
		wait.until(ExpectedConditions.visibilityOf(submitButton)).click();
		
	}
	
	
	
	 public HomePage verifyLandingPage(String expectedText) throws InterruptedException {
		 //Thread.sleep(5000);
		 wait.until(ExpectedConditions.visibilityOf(mySite));
		 String actualText=mySite.getText();
		 System.out.println("actualText :"+actualText);
		 
		 //Using SoftAssert
		 SoftAssert softAssertion= new SoftAssert();
		 softAssertion.assertEquals(actualText, expectedText);
		 softAssertion.assertAll();
		 
		 /*Assert.assertEquals("True", expectedText, actualText);
		 Thread.sleep(5000);*/
		 
		 return new HomePage();
		 
	 }
	
} //class