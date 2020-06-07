package studentDetailsTest;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import domain.studentDetailsPost.StudentDetailsPostRequest;
import domain.studentDetailsPost.StudentDetailsPostResponse;
import helpers.APIResources;
import helpers.BaseTests;
import helpers.EndPoint;
import helpers.LoadData;
import helpers.ResponseBase;
import helpers.Utils;
import io.restassured.response.Response;


public class StudentDetailsPostTest extends BaseTests{
	
	LoadData loaddata = new LoadData();
	StudentDetailsPostResponse stDetailsrequest= new StudentDetailsPostResponse();
	ResponseBase responsebase=new ResponseBase();
	
	
	@Test
	public void studentDetailsPostverify() throws JsonMappingException, JsonProcessingException{
		
		
	 		
		Response response = Utils.callPostEndpoint("POST",
				loaddata.getstudentDetailsPostData("Post Test", "Post last", "Post middle", "02/7/2020"),
				APIResources.getValueFromEnum(APIResources.STUDENT_DETAILS_POST_API));//EndPoint.poststudentDetEndPoint()
				//APIResources.valueOf(StudentDetailsPostAPI).toString());
		
		Assert.assertEquals(response.getStatusCode(),201);
		
		//System.out.println(response.asString());
		
		StudentDetailsPostResponse studentdetailsresponse = response.getBody().as(StudentDetailsPostResponse.class);
		
		
		System.out.println(studentdetailsresponse.getLastName());
		
		Assert.assertEquals(studentdetailsresponse.getLastName(), "Post last");
		
		getTestCache().put("studentId", studentdetailsresponse.getId());
		
		String store = response.asString();
		String basefile=responsebase.studentDetailsResponseBase(getTestCache().get("studentId").toString());
		Assert.assertEquals(store, basefile);
		
		//String asText=Utils.getSpecificvalue(response,"last_name");
		
		//Assert.assertEquals(asText, "Post last");
		
		//loaddata.id=studentdetailsresponse.getId().toString();
		
		
				
	}

}



/*
 @Test
	public void createStudentTest() throws IOException {

		
		 //* Response response = RestAssured. given().log().ifValidationFails()
		 //* .header("Content-Type", "application/json") .accept(ContentType.JSON)
		 //* .baseUri(utils.getBaseURL("HOST"))
		 //* .body(loaddata.getstudentPostData("Test1", "Test last", "TestMiddle",
		 //* "02/2/2020")) .post(EndPoint.poststudentDetEndPoint());
		 
		Response response = Utils.callPostEndpoint("POST",
				loaddata.getstudentPostData("Test1", "Test last", "TestMiddle", "02/2/2020"),
				EndPoint.poststudentDetEndPoint());

		JsonNode readTree = Utils.getObjectMapper().readTree(response.getBody().asString());
		JsonNode path = readTree.path("last_name");
		String asText = path.asText();
		Assert.assertEquals(201, response.getStatusCode());

		StudentResponse studentResponse = response.getBody().as(StudentResponse.class);
		
		getTestCache().put("studentId", studentResponse.getId());
		System.out.println(studentResponse.getId());
	}

	@Test
	public void getStudentTest() throws IOException {
		
		
		//Response response = RestAssured.given().header("Content-Type", "application/json")
				//.accept(ContentType.JSON).baseUri(utils.getBaseURL("HOST"))
				//.formParam("id", getTestCache().get("studentId")).get(EndPoint.getStudentEndPoint());
		
		Response response=Utils.callGetEndPoint("GET", getTestCache().get("studentId").toString(), EndPoint.getStudentEndPoint());
		Assert.assertEquals(200, response.getStatusCode());

		StudentDetailsResponse1 studentDetailsResponse = response.getBody().as(StudentDetailsResponse1.class);

		String status = studentDetailsResponse.getStatus();
		String data = studentDetailsResponse.getData().getDateOfBirth();
		System.out.println(data);
		System.out.println(status);

	}*/
