package TestRunner;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import Pages.BasePage;
import Utility.PropertyFileHandler;

//import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

//@RunWith(Cucumber.class)
@CucumberOptions(
		features="D:\\pushpa_wrkspace\\CukeTestNGPOM\\src\\test\\java\\Features\\WebSiteLogIn.feature"
		,glue= {"StepDefinitions"},
				tags={"@tag1"},
				plugin= {"pretty","html:target/site/cucumber-pretty","json:target/cucumber.json"},
				strict=true,
				dryRun=false,
				monochrome=true)
		
@Test		
public class TestRunner extends AbstractTestNGCucumberTests{
	
	ExtentHtmlReporter reporter;
	ExtentReports extent ;
	ExtentTest logger;
	
	@BeforeTest
	public void setup() throws IOException {
		reporter= new ExtentHtmlReporter("./Reports/WordPressNavigation.html");
		 extent = new ExtentReports();
		 extent.attachReporter(reporter);
		 
		
	}
	
	@BeforeMethod
	public void register(Method method) {
		String testName = method.getName();
		logger=extent.createTest(testName);
		
	}
	
	@AfterMethod
	public void captureStatus(ITestResult result) {
		if(result.getStatus()==ITestResult.SUCCESS) {
			logger.log(Status.PASS,"The test method named as "+result.getName()+"is passed");
	}
	else if(result.getStatus()==ITestResult.FAILURE) {
		logger.log(Status.FAIL, "The test method named as "+result.getName()+"is failed");	
		try{
			 // To create reference of TakesScreenshot
			 TakesScreenshot screenshot=(TakesScreenshot)BasePage.driver;
			 // Call method to capture screenshot
			 File src=screenshot.getScreenshotAs(OutputType.FILE);
			 // Copy files to specific location 
			 // result.getName() will return name of test case so that screenshot name will be same as test case name
			 FileUtils.copyFile(src, new File("D:\\"+result.getName()+".png"));
			 System.out.println("Successfully captured a screenshot");
			 }catch (Exception e){
			 System.out.println("Exception while taking screenshot "+e.getMessage());
			 } 
	}
	else if (result.getStatus()==ITestResult.SKIP){
		logger.log(Status.PASS,"The test method named as "+result.getName()+"is passed");																						
	}
	
	}//afterMethod	
	
	@AfterTest
	public void cleanup() {
		extent.flush();
	}
	

	
	}//class
