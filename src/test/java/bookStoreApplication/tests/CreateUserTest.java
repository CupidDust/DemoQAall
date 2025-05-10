package bookStoreApplication.tests;

import bookStoreApplication.pojo.CreateUserRequest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class CreateUserTest 
{
	public static void main(String args[])
	{
//		Set Base URI
		RestAssured.baseURI = "https://demoqa.com";
		
//		Create POJO and set Data
		CreateUserRequest req = new CreateUserRequest();
		req.setUserName("1demousermay25");
		req.setPassword("DemoUser@123");
		
//		Send POST request with POJO body
		Response response = given().header("Content-Type", "application/json")
				.body(req).when().post("/Account/v1/User")
				.then().statusCode(201).extract().response();
		
		System.out.println("Response: "+response.asString());		
	}

}
