package Utility;

import java.util.ArrayList;

public class TestUtil {
	
	static PropertyFileHandler reader;
	
	public static ArrayList<Object[]> getDataFromExcel(String excelFilePath,String sheetName){
		
		
		ArrayList mydata = new ArrayList<Object[]>();
		
		try {
			reader = new PropertyFileHandler(excelFilePath);
			//reader = new PropertyFileHandler("D:\\pushpa_wrkspace\\CukeTestNGPOM\\src\\test\\java\\Config\\TestData.xlsx");
		} catch (Exception e) {
			e.getMessage();
		}//catch
		
		for (int rowNum=2; rowNum<=reader.getRowCount(sheetName); rowNum++) {
			String emailAdd = reader.getCellData(sheetName, "emailID", rowNum);
			String role = reader.getCellData(sheetName, "role", rowNum);
			String message = reader.getCellData(sheetName, "messsage", rowNum);
			
			Object ob[] = {emailAdd,role,message};
			
			mydata.add(ob);
			
		}
		return mydata;
	}//method

}
