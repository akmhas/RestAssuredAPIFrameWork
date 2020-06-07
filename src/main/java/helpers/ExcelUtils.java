package helpers;

import java.text.Format;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
	
	static XSSFWorkbook workbook;
	static XSSFSheet sheet;
	
	public ExcelUtils(String excelPath, String sheetName){
		
		try{
			workbook=new XSSFWorkbook(excelPath);
			sheet=workbook.getSheet(sheetName);
			
			}catch(Exception exp){
			
			System.out.println(exp.getMessage());
			System.out.println(exp.getCause());
			exp.printStackTrace();
			
			}
		
	}
	
	
	
	public static int getRowCount(){
		
		 int rowcount=sheet.getPhysicalNumberOfRows();
		 return rowcount;
		}
	
	public static int getCellCount(int rownum){
		
		int cellcount=sheet.getRow(rownum).getPhysicalNumberOfCells();
		return cellcount;
	}
	
	public static Object getCellData(int rownum,int cellnum){
		
		DataFormatter formattar=new DataFormatter();
		Object value = formattar.formatCellValue(sheet.getRow(rownum).getCell(cellnum));
		return value;
		
	}

}
