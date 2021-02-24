package examples.pubhub.model;

public class BandT extends Book {
	private Book myBook;			// individual Book
	private String myTag;           // all Tags associate to this book in one cell
	// setters
	public void setMyBook(Book myBook) {
		this.myBook = myBook;
	}
	public void setMyTag(String myTag) {
		this.myTag = myTag;
	}
	//getters
	public Book getMyBook() {
		return myBook;
	}
	public String getMyTag() {
		return myTag;
	}
}
