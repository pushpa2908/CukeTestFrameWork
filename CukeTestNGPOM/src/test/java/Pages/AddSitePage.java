package Pages;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AddSitePage extends BasePage{
	
	String parentHandle;
	
	@FindBy(linkText="My Site")
	WebElement mySite;
	
	@FindBy(xpath="//li[@data-tip-target='side-menu-page']//a[@class='sidebar__button'][contains(text(),'Add')]")
	WebElement addSitePage;
	
	@FindBy(xpath="//iframe[contains(@src,'https://pushwebsitehome.wordpress.com/wp-admin/post-new.php?post_type=page')]")
	WebElement frameElemnt;
	
	@FindBy(xpath="//div[contains(@class, 'components-popover')]")
	WebElement tipsPopOver;
	
	@FindBy(xpath = "//button[contains(@class, 'components-button components-icon-button nux-dot-tip__disable')]")
	WebElement tipsButton;
	
	@FindBy(xpath="//textarea[contains(@class, 'editor-post-title__input')]")
	WebElement textAreaTitle;
	
	@FindBy(xpath="//textarea[contains(@class, 'editor-default-block-appender__content')]")
	WebElement blockApender;
	
	@FindBy(xpath="//p[contains(@class, 'wp-block-paragraph editor-rich-text__tinymce mce-content-body')]")
	WebElement textAreaContent;
	
	@FindBy(linkText="Preview")
	WebElement preview;
	
	public AddSitePage() {
		
		PageFactory.initElements(driver, this);
	}
	
	public void addSitePageAndPreview() throws InterruptedException {
		Actions action = new Actions(driver);
		
		wait1.until(ExpectedConditions.visibilityOf(mySite)).click();
		
		wait1.until(ExpectedConditions.visibilityOf(addSitePage));
		Thread.sleep(2000);
		
		addSitePage.click();
		
		wait1.until(ExpectedConditions.visibilityOf(frameElemnt));
		driver.switchTo().frame(frameElemnt);
		
		if (wait1.until(ExpectedConditions.visibilityOf(tipsPopOver)).isDisplayed()) {
		
			wait1.until(ExpectedConditions.visibilityOf(tipsButton));
					
			action.click(tipsButton).build().perform();
		}
	
		int randomNum=new Random().nextInt(1000); 
		wait1.until(ExpectedConditions.visibilityOf(textAreaTitle)).sendKeys("Test Title"+randomNum);
		
		wait1.until(ExpectedConditions.visibilityOf(blockApender));
		action.doubleClick(blockApender).build().perform();
		textAreaContent.sendKeys("In the heart of a seed\nBurried deep so deep\nA dear little plant\nLay fast asleep");
		
				
		Thread.sleep(6000);
		System.out.println("Reached end.................");
		
 
	}//method 
		
		public void addContent() throws InterruptedException {
			/*WebElement WebElementReturned = addSitePageAndPreview();
			System.out.println("Element returned :"+WebElementReturned);
			WebElementReturned.click();*/
		}//method 
		
		public void navigateBack() {
			
			driver.navigate().back();
		}
	}//class


