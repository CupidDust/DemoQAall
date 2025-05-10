package bookStoreApplication.pojo;

import java.util.List;

public class GetAllBooksResponse 
{
	private List<Book> books;

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}
}
