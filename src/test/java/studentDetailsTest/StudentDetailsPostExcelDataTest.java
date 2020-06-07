package studentDetailsTest;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import domain.studentDetailsPost.StudentDetailsPostResponse;
import helpers.APIResources;
import helpers.BaseTests;
import helpers.ExcelUtils;
import helpers.LoadData;
import helpers.Utils;
import io.restassured.response.Response;

public class StudentDetailsPostExcelDataTest extends BaseTests {
	
	LoadData loaddata = new LoadData();
	
	@Test
	public void stDetailsPostExcelData(){
		
		String path="./ExternalData/StudentDetailsData.xlsx";
		String Sheet="Sheet1";
		ExcelUtils excel=new ExcelUtils(path, Sheet);
				
		for(int i=1;i<=ExcelUtils.getRowCount()-1;i++){
			
			List val=new ArrayList();
			
			for(int j=0;j<=ExcelUtils.getCellCount(i)-1;j++){
				
				val.add(ExcelUtils.getCellData(i, j));
			}
			
		Response response = Utils.callPostEndpoint("POST",
				loaddata.getstudentDetailsPostData(val.get(0).toString(), val.get(1).toString(), val.get(2).toString(), val.get(3).toString()),
				APIResources.getValueFromEnum(APIResources.STUDENT_DETAILS_POST_API));
		
		Assert.assertEquals(response.getStatusCode(),201);
		StudentDetailsPostResponse studentdetailsresponse = response.getBody().as(StudentDetailsPostResponse.class);
		
		
		System.out.println(studentdetailsresponse.getLastName());
		
		Assert.assertEquals(studentdetailsresponse.getLastName(), val.get(1).toString());
		
		getTestCache().put("studentId", studentdetailsresponse.getId());
		
		val.clear();
		
		}		
	}

}
