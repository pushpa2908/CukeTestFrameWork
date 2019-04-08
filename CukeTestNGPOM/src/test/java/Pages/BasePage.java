package Pages;

import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;



public class BasePage {
	
	public static WebDriver driver;
	public static Properties prop;
	public static WebDriverWait wait1; //Explicit wait
	
	public BasePage() {
		try {
			prop=new Properties();
			FileInputStream fis=new FileInputStream("D:\\pushpa_wrkspace\\CukeTestNGPOM\\src\\test\\java\\Config\\testData.properties");
			prop.load(fis);
			
		} catch (Exception e) {
			e.getMessage();
		}
	}
	
	public static void initialization(String browser,String url) throws Exception {
		if(browser.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver","D:\\perAham\\\\studies\\Cucumber\\cukes-jars\\chromedriver_win32\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().pageLoadTimeout(Utility.BrowserUtility.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
			driver.get(url);
			wait1 = new WebDriverWait(driver, 100, 150);
		}


	}
	
	public void selectRadioOption(String attributeType, String attributeValue, String value) {
		
		switch(attributeType) {
		
		case "name":
		List<WebElement>radioButton = driver.findElements(By.name(attributeValue));
		int i = radioButton.size();
		try {
		for (int counter=0 ; counter <i; counter++) {
			if(radioButton.get(counter).getAttribute("value").equalsIgnoreCase(value)) {
				radioButton.get(counter).click();
			}//if
		}//for
	}
		catch(Exception e) {System.out.println(e);}
	
	}//switch
}//selectOption
	
	@DataProvider
	public void getData() {
		
		
	}

}
