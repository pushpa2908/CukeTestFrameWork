package Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class AddContentToSitePage extends BasePage{
	
	/*@FindBy(xpath="//div[@class='components-popover__content']//p[contains(text(),'Welcome to the wonderful world of blocks']]")
	WebElement popOver;
	
	@FindBy(xpath="//button[@class='components-button components-icon-button nux-dot-tip__disable']//svg[class='dashicon dashicons-no-alt' ]")
	WebElement popOverBtn;*/
	
	/*@FindBy(xpath="//textarea[@id='post-title-0']//*[@class='editor-post-title__input'])")
	WebElement postTitle;
*/
	public AddContentToSitePage() {
		PageFactory.initElements(driver, this);
	}
	
	public void locateFrame() throws InterruptedException {
		Thread.sleep(2000);
		String frameText="fr";
		String buttonText="fr";
		int size = driver.findElements(By.tagName("iframe")).size();
		System.out.println("Number of iframes :"+size);
		System.out.println("Before alert dismiss");
		try {
			System.out.println("Inside try......");
			//driver.switchTo().frame(0);
			//driver.navigate().to("https://wordpress.com/block-editor/page/pushwebsite.home.blog");
			//List <WebElement> allElems = driver.findElements(By.xpath("//div[@class='components-popover__content']")).
			driver.switchTo().frame(driver.findElement(By.xpath("//div[@class='components-popover__content']")));
			//driver.switchTo().window(nameOrHandle)
			
			System.out.println("switched to frame!");
			Thread.sleep(2000);
	
			}
		catch(Exception e)
		{
			e.getMessage();
		}
		//System.out.println("Text of first frame :"+frameText);
		//Thread.sleep(2000);
	}
	
	public void navigateBack() {
		
		driver.navigate().back();
	}
		
	public void addContent() throws InterruptedException {
		
		
		/*
		wait1.until(ExpectedConditions.visibilityOf(popOver));
		popOverBtn.click();
		Thread.sleep(5000);*/
	}
	
}
