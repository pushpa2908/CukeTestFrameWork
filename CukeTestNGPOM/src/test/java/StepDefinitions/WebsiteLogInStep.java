package StepDefinitions;

import java.util.ArrayList;
import java.util.Iterator;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Pages.AddContentToSitePage;
import Pages.AddSitePage;
import Pages.BasePage;
import Pages.HomePage;
import Pages.NavigateSitePages;
import Pages.PeoplePage;
import Pages.WebsiteLogInPage;
import Utility.TestUtil;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class WebsiteLogInStep extends BasePage{

	WebsiteLogInPage website;
	HomePage home  = new HomePage();
	NavigateSitePages sitePages= new NavigateSitePages();
	AddSitePage sitePage=new AddSitePage();
	AddContentToSitePage addContent = new AddContentToSitePage();
	PeoplePage ppage = new PeoplePage();	
	String testDataFilePath= "D:\\pushpa_wrkspace\\CukeTestNGPOM\\src\\test\\java\\Config\\TestData.xlsx";
	//PropertyFileHandler testDataFromFile=new PropertyFileHandler(testDataFilePath);
	

	@Given("^I login to website \"([^\"]*)\" with \"([^\"]*)\" and \"([^\"]*)\"$")
	public void i_login_to_website(String url,String username,String password) throws Throwable {
		BasePage.initialization("chrome", url);
		website = new WebsiteLogInPage();		
		website.login(username,password);
	}

	@Then("^home page \"([^\"]*)\" should open$")
	public void home_page_should_open(String siteTitle) throws Throwable {
		
		home = website.verifyLandingPage(siteTitle);
	}

	@Given("^user searches \"([^\"]*)\"$")
	public void user_searches(String content) throws Throwable {
		home.searchContent(content);
		
		}

	@Then("^user should get the content \"([^\"]*)\"$")
	public void user_should_get_the_content(String expectedResult) throws Throwable {
		
		home.verifySearchResult(expectedResult);
		
}
	@And("^user on clicking  webresult should get \"([^\"]*)\"$")
			public void verifyWebResult(String webResult) throws InterruptedException {
		home.navigateSearchResult(webResult);
		
		}
	
	@Given("^user should be able to navigate Site Pages$")
		public void navigateSitePages() throws InterruptedException {
		sitePages.verifyTabsInSitePages();
		
		
	}
	
	
	
	@When("^user clicks on logout button he should be able to logout$")
	public void logout() throws InterruptedException{
	home.logout();
		}
	
	@When("^user clicks to add site page$")
	public void addsitePage() throws InterruptedException {
		sitePage.addSitePageAndPreview();
		
		//sitePage.addContent();
		//sitePage.navigateBack();
	}
	
	@Then("^user should be able to add site content$")
	public void addSiteContent() throws InterruptedException {
		
		//addContent.addContent();
		//addContent.locateFrame();
		System.out.println("End");
	}
	
	//Below method uses dataTables to fetch people data and send invitation 
	/*@When("^user enters User details$")
	public void user_enters_details(DataTable personData) throws Throwable {
		//This method uses dataTale to get the test data as input
		List<List<String>> personDetails = personData.raw();		
		ppage.sendInvite(personDetails.get(0).get(0), personDetails.get(0).get(1), personDetails.get(0).get(2));
	
		ppage.clickSubmit();
		
		
	}*/
	
	//Below method uses dataprovider to fetch data from excel sheet and then this dataprovider will be used by next test
	@DataProvider
	public Iterator<Object[]> getTestData() throws Throwable {
		ArrayList<Object[]> testdata = TestUtil.getDataFromExcel(testDataFilePath, "PeoplePage");
		return testdata.iterator();
		
		
	}
	
	@Test(dataProvider="getTestData")
	@When("^user enters User details \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" $")
	public void user_enters_details(String email,String role,String message) throws Throwable {
		String dummy1=null;
		String dummy2=null;
		String dummy3=null;
		ppage.sendInvite( dummy1,  dummy2,  dummy3);
		
		
	}

	@And("^sends invitation$")
	public void sends_invitation() throws Throwable {
		//ppage.clickSubmit();
	}
}//class
