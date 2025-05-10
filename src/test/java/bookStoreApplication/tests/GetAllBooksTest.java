package bookStoreApplication.tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import bookStoreApplication.pojo.Book;
import bookStoreApplication.pojo.GetAllBooksResponse;

public class GetAllBooksTest 
{
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		RestAssured.baseURI = "https://demoqa.com";
		Response res = given()
		.when().get("/BookStore/v1/Books")
		.then().statusCode(200).extract().response();
		
		GetAllBooksResponse allBooksResponse = res.as(GetAllBooksResponse.class);
		
		for(Book book:allBooksResponse.getBooks())
		{
			System.out.println("Title of Book: "+book.getTitle());
		}
	}

}
