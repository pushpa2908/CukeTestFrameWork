package Utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Properties;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;

public class PropertyFileHandler {
	
	//public String FILE_NAME = "D:\\pushpa_wrkspace\\CukeTestNGPOM\\src\\test\\java\\Config\\TestData.xlsx";
	public static String TESTDATA_SHEET_PATH = "D:\\pushpa_wrkspace\\CukeTestNGPOM\\src\\test\\java\\Config\\TestData.xlsx";

	static Workbook book;
	//static Sheet sheet;
	static JavascriptExecutor js;
	
	public static String filename = "src/config/testcases/TestData.xlsx";
    public  String path;
    public  FileInputStream fis = null;
    public  FileOutputStream fileOut =null;
    private XSSFWorkbook workbook = null;
    private XSSFSheet sheet = null;
    private XSSFRow row   =null;
    private XSSFCell cell = null;
    
    //constructor
    public PropertyFileHandler(String path) {

        this.path=path;
        try {
            fis = new FileInputStream(path);
            workbook = new XSSFWorkbook(fis);
            sheet = workbook.getSheetAt(0);
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

	
	//this method will return the value of the key sent - eg testData.preperties file
public String  getPropValues(String filePath,String propertyKey) throws IOException  {
		
		String value =null;
		try {
			File file = new File(filePath);
			FileInputStream fileInput = new FileInputStream(file);
			Properties properties = new Properties();
			properties.load(fileInput);
			fileInput.close();

			Enumeration enuKeys = properties.keys();
			while (enuKeys.hasMoreElements()) {
				String key = (String) enuKeys.nextElement();
				if (key.equalsIgnoreCase(propertyKey)) {
					 value = properties.getProperty(key);
					 break;
				}
				
							}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return value;
	}

public void getCellData1(String SheetName,String colHeader,String rowCount) {
	final String FILE_NAME = "D:\\pushpa_wrkspace\\CukeTestNGPOM\\src\\test\\java\\Config\\TestData.xlsx";
	System.out.println("Inside getCellData method ============>");
	ArrayList<Object[]> myData = new ArrayList<Object[]>();
	int columnNumber=0;
	
	try {

		FileInputStream excelFile = new FileInputStream(new File(FILE_NAME));
        Workbook workbook = new XSSFWorkbook(excelFile);
        Sheet dataSheet = workbook.getSheet(SheetName);
        DataFormatter dataFormatter = new DataFormatter();
        
        
        Iterator<Row> rowIterator = dataSheet.rowIterator();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
           
            // iterate over the columns of the current row
            Iterator<Cell> cellIterator = row.cellIterator();

            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                String cellValue = dataFormatter.formatCellValue(cell);
                System.out.println("CellValue :"+cellValue);
                if (cellValue.equals(colHeader)) {
                 columnNumber =	cell.getColumnIndex();
                 break;
                 }//if
                      
                if( cell.getColumnIndex()==columnNumber) {
                	String  result=  dataFormatter.formatCellValue(cell);
                }
               }//while cellIterator
            
        }//while rowIterator
        
        for (int i =2;i<dataSheet.getLastRowNum()-1;i++) {
        	System.out.println();
        }
		
	} catch (Exception e) {
		e.getMessage();
	}
	
}//method

public int getRowCount(String sheetName){
    int index = workbook.getSheetIndex(sheetName);
    if(index==-1)
        return 0;
    else{
        sheet = workbook.getSheetAt(index);
        int number=sheet.getLastRowNum()+1;
        return number;
    }

}

//returns the data from a cell
public String getCellData(String sheetName,String colName,int rowNum){
    try{
        if(rowNum <=0)
            return "";

        int index = workbook.getSheetIndex(sheetName);
        int col_Num=-1;
        if(index==-1)
            return "";

        sheet = workbook.getSheetAt(index);
        row=sheet.getRow(0);
        for(int i=0;i<row.getLastCellNum();i++){
            //System.out.println(row.getCell(i).getStringCellValue().trim());
            if(row.getCell(i).getStringCellValue().trim().equals(colName.trim()))
                col_Num=i;
        }
        if(col_Num==-1)
            return "";

        sheet = workbook.getSheetAt(index);
        row = sheet.getRow(rowNum-1);
        if(row==null)
            return "";
        cell = row.getCell(col_Num);

        if(cell==null)
            return "";
        //System.out.println(cell.getCellType());
        if(cell.getCellType()==Cell.CELL_TYPE_STRING)
            return cell.getStringCellValue();
        else if(cell.getCellType()==Cell.CELL_TYPE_NUMERIC || cell.getCellType()==Cell.CELL_TYPE_FORMULA ){

            String cellText  = String.valueOf(cell.getNumericCellValue());
            if (HSSFDateUtil.isCellDateFormatted(cell)) {
                // format in form of M/D/YY
                double d = cell.getNumericCellValue();

                Calendar cal =Calendar.getInstance();
                cal.setTime(HSSFDateUtil.getJavaDate(d));
                cellText =
                        (String.valueOf(cal.get(Calendar.YEAR))).substring(2);
                cellText = cal.get(Calendar.DAY_OF_MONTH) + "/" +
                        cal.get(Calendar.MONTH)+1 + "/" +
                        cellText;

                //System.out.println(cellText);

            }



            return cellText;
        }else if(cell.getCellType()==Cell.CELL_TYPE_BLANK)
            return "";
        else
            return String.valueOf(cell.getBooleanCellValue());

    }
    catch(Exception e){

        e.printStackTrace();
        return "row "+rowNum+" or column "+colName +" does not exist in xls";
    }
}

// returns the data from a cell
public String getCellData(String sheetName,int colNum,int rowNum){
    try{
        if(rowNum <=0)
            return "";

        int index = workbook.getSheetIndex(sheetName);

        if(index==-1)
            return "";


        sheet = workbook.getSheetAt(index);
        row = sheet.getRow(rowNum-1);
        if(row==null)
            return "";
        cell = row.getCell(colNum);
        if(cell==null)
            return "";

        if(cell.getCellType()==Cell.CELL_TYPE_STRING)
            return cell.getStringCellValue();
        else if(cell.getCellType()==Cell.CELL_TYPE_NUMERIC || cell.getCellType()==Cell.CELL_TYPE_FORMULA ){

            String cellText  = String.valueOf(cell.getNumericCellValue());
            if (HSSFDateUtil.isCellDateFormatted(cell)) {
                // format in form of M/D/YY
                double d = cell.getNumericCellValue();

                Calendar cal =Calendar.getInstance();
                cal.setTime(HSSFDateUtil.getJavaDate(d));
                cellText =
                        (String.valueOf(cal.get(Calendar.YEAR))).substring(2);
                cellText = cal.get(Calendar.MONTH)+1 + "/" +
                        cal.get(Calendar.DAY_OF_MONTH) + "/" +
                        cellText;

                // System.out.println(cellText);

            }



            return cellText;
        }else if(cell.getCellType()==Cell.CELL_TYPE_BLANK)
            return "";
        else
            return String.valueOf(cell.getBooleanCellValue());
    }
    catch(Exception e){

        e.printStackTrace();
        return "row "+rowNum+" or column "+colNum +" does not exist  in xls";
    }
}




}//class
