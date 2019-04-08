package Pages;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage{
	
	
	
	@FindBy(xpath="//span[contains(text(),'Reader')]")
	WebElement readerTab;
	
	
	//@FindBy(id="search-component-20")
	@FindBy(className="search__input")
	WebElement searchTextField;
	
	@FindBy(how=How.XPATH,using="//*[@class='gridicon gridicons-search search__open-icon']")
	WebElement serachButton;
	
	@FindBy(how=How.XPATH, using="//div[contains(text(),'Google Cloud Platform for Systems Operations Profe')]")
	WebElement searchResult;
	
	@FindBy(how=How.XPATH, using="//a[@href='http://www.kqzyfj.com/click-8526345-13008675']")
	WebElement webResult;     
	
	@FindBy(how=How.XPATH,using="//span[@class='masterbar__item-content']//img[@class='gravatar']")
	WebElement userProfile;
	
	@FindBy(how=How.XPATH,using="//button[@title='Log out of WordPress.com']")
	WebElement logoutButton;
	
	public HomePage() {	
		
		PageFactory.initElements(driver, this);
		 
	}
	
	public void searchContent(String content) throws InterruptedException
	{
		wait1.until(ExpectedConditions.visibilityOf(readerTab)).click();
		//readerTab.click();
		//Thread.sleep(9000);
		wait1.until(ExpectedConditions.visibilityOf(searchTextField)).sendKeys(content);
		//Thread.sleep(2000);
		wait1.until(ExpectedConditions.visibilityOf(serachButton)).click();		
		//Thread.sleep(2000);		
	}
	
	public void verifySearchResult(String expectedResult)
	{
		wait1.until(ExpectedConditions.visibilityOf(searchResult));
		String actualResult=searchResult.getText();
		System.out.println("searchResult :"+actualResult);
		//Assert.assertEquals(expectedResult,actualResult,"Result did not match!");
		Assert.assertEquals(expectedResult, actualResult);
	}
	
	public void navigateSearchResult(String res) throws InterruptedException {
		
		//This course will introduce you to the implementation
		
		searchResult.click();
		//Thread.sleep(5000);	
		wait1.until(ExpectedConditions.visibilityOf(webResult));
		Boolean actualText=webResult.getText().contains(res);
		System.out.println("Web Result :"+webResult.getText());
		Assert.assertTrue(actualText);
	}
	
	public void logout() throws InterruptedException {
		userProfile.click();
		wait1.until(ExpectedConditions.visibilityOf(logoutButton)).click();
		//logoutButton.click();
		driver.quit();
	}
	
	
}
