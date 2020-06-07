package helpers;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

public class BaseTests {

	private static Map<String, Object> testCache;
	private static RequestSpecification req;

	@BeforeSuite
	public void setCache() {
		testCache = new HashMap<>();
		req = requestSpecification();
	}

	public static Map<String, Object> getTestCache() {
		return testCache;
	}

	public RequestSpecification requestSpecification() {

		if (req == null) {
			PrintStream log;
			try {
				log = new PrintStream(new FileOutputStream("logging.txt"));
				req = new RequestSpecBuilder().addFilter(RequestLoggingFilter.logRequestTo(log))
						.addFilter(ResponseLoggingFilter.logResponseTo(log)).setContentType(ContentType.JSON).build();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return req;
		}
		return req;
	}

	public static RequestSpecification getRequestSpecification() {

		return req;
	}

}
