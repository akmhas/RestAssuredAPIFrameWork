package studentDetailsTest;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import domain.studentDetailsGet.StudentDetailsGetResponse;
import helpers.BaseTests;
import helpers.EndPoint;
import helpers.LoadData;
import helpers.Utils;
import io.restassured.response.Response;

public class StudentDetailsGetTest extends BaseTests {
	
	LoadData loaddata = new LoadData();

	@Test
	public void studentDetailsGetVerify() throws JsonMappingException, JsonProcessingException{
		
		Response response=Utils.callGetEndPoint("GET",getTestCache().get("studentId").toString() , EndPoint.getStudentEndPoint());//176480  loaddata.id
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		StudentDetailsGetResponse studentdetailsgetresponse = response.getBody().as(StudentDetailsGetResponse.class);	
		
		//System.out.println(studentdetailsgetresponse.getStatus());
		Assert.assertEquals(studentdetailsgetresponse.getStatus(), "true");
		
		//System.out.println(studentdetailsgetresponse.getData().getFirstName());
		Assert.assertEquals(studentdetailsgetresponse.getData().getFirstName(), "Post Test");
		
		//Assert.assertEquals(studentdetailsgetresponse.getData().getId().toString(), "176480");
		//System.out.println(studentdetailsgetresponse.getData().toString());//Need help
		
		System.out.println(Utils.gettreevalue(response,"data"));//Need help 
		
	}
}










/* Response response = RestAssured.
given().log().ifValidationFails()
.header("Content-Type", "application/json")
.accept(ContentType.JSON)
.baseUri(utils.getBaseURL("HOST"))
.body(loaddata.getstudentPostData("Test1", "Test last", "TestMiddle", "02/2/2020"))
.post(EndPoint.poststudentDetEndPoint());

Assert.assertEquals(201, response.getStatusCode());

StudentResponse studentResponse = response.getBody().as(StudentResponse.class);
getTestCache().put("studentId", studentResponse.getId());

List<String> languages = new ArrayList<>();
languages.add("Java");

Response respskill = RestAssured.
given().log().ifValidationFails()
.header("Content-Type", "application/json")
.accept(ContentType.JSON)
.baseUri(utils.getBaseURL("HOST"))
.body(loaddata.getStudentTechnicalPostData( languages, "5 YEAR", "3 mONTH",getTestCache().get("studentId").toString() ))
.post(EndPoint.postStudentTechnicalSkils());

Assert.assertEquals(201, respskill.getStatusCode());*/