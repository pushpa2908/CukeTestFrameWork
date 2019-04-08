package Utility;

public class TestConcepts {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		System.out.println(System.getProperty("user.dir"));
		//output : D:\pushpa_wrkspace\CukeTestNGPOM
		System.out.println(System.getProperty("user.dir")+"\\src\\test\\java");
		
		//iMPLICIT WAIT
		/*WebDriver driver;
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);*/
		
		//PropertyFileHandler ph = new PropertyFileHandler();
		
		//ph.getCellData("LoginPageInput");
		//PropertyFileHandler.getTestData("LoginPageInput");
		
	}

}
