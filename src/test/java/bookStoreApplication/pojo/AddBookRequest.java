package bookStoreApplication.pojo;

import java.util.List;

public class AddBookRequest 
{
	private String userId;
	private List<Isbn> collectionOfIsbns;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public List<Isbn> getCollectionOfIsbns() {
		return collectionOfIsbns;
	}
	public void setCollectionOfIsbns(List<Isbn> collectionOfIsbns) {
		this.collectionOfIsbns = collectionOfIsbns;
	}
}
