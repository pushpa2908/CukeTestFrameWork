package Utility;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;

public class BrowserUtility {
	
	public static int PAGE_LOAD_TIMEOUT=1000;
	static int IMPLICIT_WAIT=200;

	
	public static List<String> getLabelsOFWebElements(List <WebElement>webElements) {
		
		 List<String> labelsList = new ArrayList<>();
		 System.out.println("Getting labels of web elements :");
		    //iterate through all the webElements
		    for (WebElement webElement : webElements) {
		        //add each webElements label to the labelsList
		        labelsList.add(webElement.getText());
		        System.out.println(webElement.getText());
		    }
		    //return all the label texts that are visible in the dropdown
		    return labelsList;
		}
		
	
}

