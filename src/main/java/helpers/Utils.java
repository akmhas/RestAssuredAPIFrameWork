package helpers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {

	static RequestSpecification spec;
	// static RequestSpecification spec =
	// RestAssured.given().spec(BaseTests.getRequestSpecification());

	public static String getBaseURL(String key) {

		Properties prop = new Properties();
		FileInputStream pf;
		try {
			pf = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\main\\java\\sourceFiles\\baseURL.properties");
			prop.load(pf);
		} catch (FileNotFoundException e) {
			System.out.println("File not Found Exception " + e);
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("IO Exception " + e);
			e.printStackTrace();
		}

		return prop.getProperty(key);

	}

	public static Response callPostEndpoint(String method, Object body, String endpoint) {

		spec = RestAssured.given().spec(BaseTests.getRequestSpecification());
		if (method.equalsIgnoreCase("POST")) {
			Response post = spec.header("Content-Type", "application/json").accept(ContentType.JSON)
					.baseUri(getBaseURL("HOST")).body(body).post(endpoint);
			return post;
		}
		return null;
	}

	public static Response callGetEndPoint(String method, String id, String endpoint) {

		spec = RestAssured.given().spec(BaseTests.getRequestSpecification());
		if (method.equalsIgnoreCase("GET")) {

			Response get = spec.header("Content-Type", "application/json").accept(ContentType.JSON)
					.baseUri(getBaseURL("HOST")).formParam("id", id).get(endpoint);
			return get;
		} else if (method.equalsIgnoreCase("DELETE")) {

			Response delete = spec.header("Content-Type", "application/json").accept(ContentType.JSON)
					.baseUri(getBaseURL("HOST")).queryParam("id", id).delete(endpoint);
			return delete;
		}

		return null;
	}

	public static ObjectMapper getObjectMapper() {
		ObjectMapper mapper = new ObjectMapper();
		return mapper;
	}

	public static String gettreevalue(Response response, String value)
			throws JsonMappingException, JsonProcessingException {
		JsonNode readTree = Utils.getObjectMapper().readTree(response.getBody().asString());
		JsonNode path = readTree.path(value);
		String asText = path.toString();
		return asText;
	}
	
	
	public static String getSpecificvalue(Response response, String value)
			throws JsonMappingException, JsonProcessingException {
		JsonNode readTree = Utils.getObjectMapper().readTree(response.getBody().asString());
		JsonNode path = readTree.path(value);
		String asText = path.asText();
		return asText;
	}

}
