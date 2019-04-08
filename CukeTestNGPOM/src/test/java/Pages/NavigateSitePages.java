package Pages;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import Utility.BrowserUtility;

public class NavigateSitePages extends BasePage {
	
	@FindBy(how=How.XPATH,using="//span[contains(text(),'My Site')]")
	WebElement mySiteLink;
	
	
	@FindBy(xpath="//span[contains(text(),'Site Pages')]")
	WebElement sitePages;
	
	@FindBy(xpath="//span[contains(text(),'Published')]")
	WebElement publishedTab;
	
	@FindBy(xpath="//span[@class='section-nav-tab__text'][contains(text(),'Scheduled')]")
	WebElement scheduledTab;
	
	@FindBy(xpath= "//span[@class='section-nav-tab__text'][contains(text(),'Drafts')]")
	WebElement draftsTab;
	
	@FindBy(xpath="//span[contains(text(),'Trashed')]")
	WebElement trashTab;
	
	@FindBy(className="section-nav-tab__text")
	List <WebElement> tabWebElements;
	
	
	
	public NavigateSitePages() {
		PageFactory.initElements(driver, this);
	}
	
	public void verifyTabsInSitePages() throws InterruptedException {
		
		mySiteLink.click();
		Thread.sleep(2000);
		sitePages.click();
		Thread.sleep(2000);
		List <String> webElementLabels = BrowserUtility.getLabelsOFWebElements(tabWebElements);
		
				
		//define the required list to compare 
		
		List<String> expectedLabelsList = Arrays.asList("Published", "Scheduled", "Drafts", "Trashed");
		
		
		Collections.sort(webElementLabels);
		Collections.sort(expectedLabelsList);
		
		Assert.assertEquals(webElementLabels, expectedLabelsList);
		
		
	}
	
	

}
