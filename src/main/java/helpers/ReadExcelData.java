package helpers;

public class ReadExcelData {

	public static void main(String[] args) {

		String path="./ExternalData/StudentDetailsData.xlsx";
		String Sheet="Sheet1";
		ExcelUtils excel=new ExcelUtils(path, Sheet);
				
		for(int i=1;i<=ExcelUtils.getRowCount()-1;i++){
			
			for(int j=0;j<=ExcelUtils.getCellCount(i)-1;j++){
				
				System.out.println(ExcelUtils.getCellData(i, j));
			}
			
		}

	}

}
