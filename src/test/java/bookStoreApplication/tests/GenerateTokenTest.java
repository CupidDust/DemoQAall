package bookStoreApplication.tests;

import bookStoreApplication.pojo.CreateUserRequest;
import bookStoreApplication.pojo.GenerateTokenResponse;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class GenerateTokenTest 
{
	public static void main(String args[])
	{
		RestAssured.baseURI = "https://demoqa.com";
		CreateUserRequest req = new CreateUserRequest();
		req.setUserName("1demousermay25");
		req.setPassword("DemoUser@123");
		Response response = given().header("Content-Type", "application/json").body(req)
		.when().post("/Account/v1/GenerateToken")
		.then().statusCode(200).extract().response();
		
		GenerateTokenResponse tokenResponse = response.as(GenerateTokenResponse.class);
		
		System.out.println(tokenResponse.getToken());
		System.out.println(tokenResponse.getStatus());
		System.out.println(tokenResponse.getExpires());
		System.out.println(tokenResponse.getResult());
	}
}
