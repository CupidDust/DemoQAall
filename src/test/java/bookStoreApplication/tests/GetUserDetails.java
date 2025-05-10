package bookStoreApplication.tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

import bookStoreApplication.pojo.GetUserResponse;

public class GetUserDetails 
{
	public static void main(String args[])
	{
		String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyTmFtZSI6IjFkZW1vdXNlcm1heTI1IiwicGFzc3dvcmQiOiJEZW1vVXNlckAxMjMiLCJpYXQiOjE3NDY4MTIwMDV9.N403LPVJ8KdyTFzGOtmrzGgN1RmEz6IZy1CTesxfKS0";
		String userId = "9517c25a-336f-4222-b1e8-7cdb1e2114d9";
		RestAssured.baseURI = "https://demoqa.com";
		Response res = given().header("Authorization","Bearer "+token)
				.when().get("/Account/v1/User/"+userId)
		.then().statusCode(200).extract().response();
		
		GetUserResponse userResponse = res.as(GetUserResponse.class);
		System.out.println(userResponse.getUserId());
		System.out.println(userResponse.getUsername());
		System.out.println(userResponse.getBooks());


	}

}
