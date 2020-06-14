package studentDetailsTest;

import org.testng.Assert;
import org.testng.annotations.Test;

import domain.studentDetailsDelete.StudentDetailsAfterDeleteGetResponse;
import domain.studentDetailsDelete.StudentDetailsDeleteResponse;
import domain.studentDetailsGet.StudentDetailsGetResponse;
import domain.studentDetailsPost.StudentDetailsPostResponse;
import helpers.BaseTests;
import helpers.EndPoint;
import helpers.LoadData;
import helpers.Utils;
import io.restassured.response.Response;

public class StudentDetailsEndToEndTest extends BaseTests {
	
	LoadData loaddata = new LoadData();
	
	
	@Test
	public void studentdetailsEndToEndTest(){
		
		//Post Test
		
		Response response = Utils.callPostEndpoint("POST",
				loaddata.getstudentDetailsPostData("Post Test", "Post last", "Post middle", "02/7/2020"),
				EndPoint.poststudentDetEndPoint());
		Assert.assertEquals(response.getStatusCode(),201);
		StudentDetailsPostResponse studentdetailsresponse = response.getBody().as(StudentDetailsPostResponse.class);
		
		Assert.assertEquals(studentdetailsresponse.getLastName(), "Post last");
		
		getTestCache().put("studentId", studentdetailsresponse.getId());
		
		//Get Test
		
		
		Response getresponse=Utils.callGetEndPoint("GET",getTestCache().get("studentId").toString(), EndPoint.getStudentEndPoint());
		
		Assert.assertEquals(getresponse.getStatusCode(), 200);
		
		StudentDetailsGetResponse studentdetailsgetresponse = getresponse.getBody().as(StudentDetailsGetResponse.class);	
		
		Assert.assertEquals(studentdetailsgetresponse.getStatus(), "true");
		Assert.assertEquals(studentdetailsgetresponse.getData().getFirstName(), "Post Test");
		
		
		//Delete Test
		
		Response deleteresponse=Utils.callGetEndPoint("DELETE",getTestCache().get("studentId").toString(), EndPoint.deleteStudentDetailsEndPoint());
		
		Assert.assertEquals(deleteresponse.getStatusCode(), 200);
		
		StudentDetailsDeleteResponse studentdetailsdeleteresponse = deleteresponse.getBody().as(StudentDetailsDeleteResponse.class);	
		
		Assert.assertEquals(studentdetailsdeleteresponse.getStatus(), "true");
		Assert.assertEquals(studentdetailsdeleteresponse.getMsg(), "Delete  data success");
		
		
		//Get Test After Delete
		
		Response getAfterDeleteresponse=Utils.callGetEndPoint("GET",getTestCache().get("studentId").toString(), EndPoint.getStudentEndPoint());
		
		Assert.assertEquals(getAfterDeleteresponse.getStatusCode(), 200);
		
		StudentDetailsAfterDeleteGetResponse getresponseAfterDelete = getAfterDeleteresponse.getBody().as(StudentDetailsAfterDeleteGetResponse.class);
		
		Assert.assertEquals(getresponseAfterDelete.getStatus(), "false");
		Assert.assertEquals(getresponseAfterDelete.getMsg(), "No data Found");
		
		//Add new line
		//Add second line
		
	}
	
	

}
