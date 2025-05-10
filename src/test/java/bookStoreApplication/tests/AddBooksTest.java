package bookStoreApplication.tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import bookStoreApplication.pojo.AddBookRequest;
import bookStoreApplication.pojo.Book;
import bookStoreApplication.pojo.GetUserResponse;
import bookStoreApplication.pojo.Isbn;

public class AddBooksTest 
{
	public static void main(String[] args) 
	{
		String authCode = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyTmFtZSI6IjFkZW1vdXNlcm1heTI1IiwicGFzc3dvcmQiOiJEZW1vVXNlckAxMjMiLCJpYXQiOjE3NDY4ODMxODZ9.pjsH9fNM46m-NJYrTCAv7RxOcmbdPqena3W3LR5AOEQ";
		String userID = "9517c25a-336f-4222-b1e8-7cdb1e2114d9";
		String isbn = "9781449325862";
		// TODO Auto-generated method stub
		RestAssured.baseURI = "https://demoqa.com";
		
//		setting individual isbn
		Isbn book1 = new Isbn();
		book1.setIsbn(isbn);
		
//		now carrying the same to list
		List<Isbn> isbnList = Arrays.asList(book1);
		
		AddBookRequest addBookRequest = new AddBookRequest();
		addBookRequest.setUserId(userID);
		addBookRequest.setCollectionOfIsbns(isbnList);
		
		Response req = given().header("Authorization","Bearer "+authCode).header("Content-Type","application/json").body(addBookRequest)
		.when().post("/BookStore/v1/Books")
		.then().statusCode(201).extract().response();
		
		Response req1 = given().header("Authorization","Bearer "+authCode).pathParam("userId",userID)
		.when().get("/Account/v1/User/{userId}")
		.then().statusCode(200).extract().response();
		
		GetUserResponse addedBooks = req1.as(GetUserResponse.class);
		
		for(Book b : addedBooks.getBooks())
		{
			if(b.getIsbn().equals(isbn))
			{
				System.out.println("Book Found");
				break;
			}
			else
			{
				System.out.println("Book not found");
			}
		}
		
	}

}
